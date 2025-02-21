package com.youjob.youjob.service.impl;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Consultation;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import com.youjob.youjob.domain.Enum.ProjectProgress;
import com.youjob.youjob.domain.Enum.UserRole;
import com.youjob.youjob.domain.Project;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.exception.NullVarException;
import com.youjob.youjob.exception.annonce.AnnonceNotExistException;
import com.youjob.youjob.exception.annonce.InvalidAnnonceException;
import com.youjob.youjob.exception.auth.StatusInvalidException;
import com.youjob.youjob.exception.auth.UserNotExistException;
import com.youjob.youjob.exception.consultation.ConsultationInvalidException;
import com.youjob.youjob.repository.AnnonceRepository;
import com.youjob.youjob.repository.ConsultationRepository;
import com.youjob.youjob.repository.ProjectRepository;
import com.youjob.youjob.repository.UserRepository;
import com.youjob.youjob.service.ConsultationService;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final UserRepository userRepository;
    private final AnnonceRepository annonceRepository;
    private final ProjectRepository projectRepository;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository, UserRepository userRepository, AnnonceRepository annonceRepository,ProjectRepository projectRepository) {
        this.consultationRepository = consultationRepository;
        this.userRepository = userRepository;
        this.annonceRepository = annonceRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Consultation CreateConsultation(Consultation consultation) {
        Optional<User> user = userRepository.findById(consultation.getResponder().getId());
        user.orElseThrow(() -> new UserNotExistException("User not found"));
        Optional<Annonce> annonce = annonceRepository.findById(consultation.getAnnonce().getId());
        annonce.orElseThrow(() -> new AnnonceNotExistException("Annonce not found"));

        if (user.get().getRole() != UserRole.HANDYMAN)
            throw new StatusInvalidException("only handyman can consult to annonce");
        if (annonce.get().getStatus() == AnnonceStatus.ARCHIVED)
            throw new InvalidAnnonceException("status should be active");

        Optional<Consultation> consultation1 = consultationRepository.findByResponderIdAndAnnonceId(user.get().getId(), annonce.get().getId());
        if (consultation1.isPresent()) throw new ConsultationInvalidException("User already consult for this annonce");

        Optional<Consultation> consultation2 = consultationRepository.findByAnnonceIdAndAccepted(annonce.get().getId(), true);
        if (consultation2.isPresent()) throw new ConsultationInvalidException("Other user accepted in the annonce");

        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation approveConsultation(UUID uuid) {
        if(uuid==null || uuid.equals("")) throw new NullVarException("uuid null");
        Optional<Consultation> consultation=consultationRepository.findById(uuid);
        consultation.orElseThrow(()-> new ConsultationInvalidException("consultation not found"));
        Consultation consultation1=consultation.get();

        if (consultation1.getAnnonce().getStatus() == AnnonceStatus.ARCHIVED)
            throw new InvalidAnnonceException("status should be active");
        Optional<Consultation> consultation2 = consultationRepository.findByAnnonceIdAndAccepted(consultation1.getAnnonce().getId(), true);
        if (consultation2.isPresent()) throw new ConsultationInvalidException("Other user accepted in the annonce");

        Project project=new Project();
        project.setAnnonce(consultation1.getAnnonce());
        project.setProgress(ProjectProgress.PENDING);
        project.setConfirmedDate(null);
        project.setAccepted(null);
        project.setDateComplete(null);
        project.setPrice(consultation1.getAnnonce().getPrice());
        projectRepository.save(project);

        consultation1.setAccepted(true);
        return consultationRepository.save(consultation1);
    }

    @Override
    public Consultation rejectConsultation(UUID uuid) {
        if(uuid==null || uuid.equals("")) throw new NullVarException("uuid null");
        Optional<Consultation> consultation=consultationRepository.findById(uuid);
        consultation.orElseThrow(()-> new ConsultationInvalidException("consultation not found"));
        Consultation consultation1=consultation.get();

        consultation1.setAccepted(false);
        return consultationRepository.save(consultation1);
    }

    @Override
    public void deleteConsultation(UUID uuid) {
        if(uuid==null || uuid.equals("")) throw new NullVarException("uuid null");
        Optional<Consultation> consultation=consultationRepository.findById(uuid);
        consultation.orElseThrow(()-> new ConsultationInvalidException("consultation not found"));
        Consultation consultation1=consultation.get();
        consultationRepository.delete(consultation1);
    }
}