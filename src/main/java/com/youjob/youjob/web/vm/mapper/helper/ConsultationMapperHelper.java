package com.youjob.youjob.web.vm.mapper.helper;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.exception.annonce.AnnonceNotExistException;
import com.youjob.youjob.exception.auth.UserNotExistException;
import com.youjob.youjob.repository.AnnonceRepository;
import com.youjob.youjob.repository.UserRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConsultationMapperHelper {
    private final UserRepository userRepository;
    private final AnnonceRepository annonceRepository;

    public ConsultationMapperHelper(UserRepository userRepository,AnnonceRepository annonceRepository) {
        this.userRepository = userRepository;
        this.annonceRepository = annonceRepository;
    }

    @Named("mapUser")
    public User mapUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("User not found with ID: " + userId));
    }
    @Named("mapAnnonce")
    public Annonce mapAnnonce(UUID annonceId) {
        return annonceRepository.findById(annonceId)
                .orElseThrow(() -> new AnnonceNotExistException("Annonce not found with ID: " + annonceId));
    }
}
