package com.youjob.youjob.service.impl;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import com.youjob.youjob.domain.Enum.UserRole;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.exception.NullVarException;
import com.youjob.youjob.exception.annonce.AnnonceNotExistException;
import com.youjob.youjob.exception.annonce.InvalidAnnonceException;
import com.youjob.youjob.exception.auth.UserNotExistException;
import com.youjob.youjob.repository.AnnonceRepository;
import com.youjob.youjob.repository.UserRepository;
import com.youjob.youjob.repository.impl.AnnonceRepositoryImpl;
import com.youjob.youjob.service.AnnonceService;
import com.youjob.youjob.web.vm.annonce.SearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnnonceServiceImpl implements AnnonceService {
    private final AnnonceRepository annonceRepository;
    private final AnnonceRepositoryImpl annonceRepositoryImpl;
    private final UserRepository userRepository;
    public AnnonceServiceImpl(AnnonceRepository annonceRepository,UserRepository userRepository,AnnonceRepositoryImpl annonceRepositoryImpl){
        this.annonceRepository=annonceRepository;
        this.userRepository=userRepository;
        this.annonceRepositoryImpl=annonceRepositoryImpl;
    }

    @Override
    public Page<Annonce> getHistory_Annonce(UUID id, int page, int size) {
        if(id==null || id.equals("")) throw new NullVarException("id is null");
        userRepository.findById(id).orElseThrow(()->new UserNotExistException("user not found"));
        Pageable pageable= PageRequest.of(page,size);
        return annonceRepository.getAllByCreatedByIdAndStatus(id, AnnonceStatus.ARCHIVED,pageable);
    }

    @Override
    public Annonce CreateAnnonce(Annonce annonce) {
        Optional<User> user= userRepository.findById(annonce.getCreatedBy().getId());
        user.orElseThrow(()->new UserNotExistException("User not found"));
        if(user.get().getRole().equals(UserRole.HANDYMAN)) throw new InvalidAnnonceException("Handyman connot posted a annonce");
        return annonceRepository.save(annonce);
    }

    @Override
    public void DeleteAnnonce(UUID id) {
        Optional<Annonce> annonce=annonceRepository.findById(id);
        annonce.orElseThrow(()->new AnnonceNotExistException("annonce not found"));
        Annonce annonce1=annonce.get();
        if(annonce1.getStatus()==AnnonceStatus.ACTIVE)
            annonceRepository.delete(annonce1);
    }

    @Override
    public Annonce updateAnnonce(Annonce annonce) {
        if(annonce.getId()==null) throw new NullVarException("id is null");
        Optional<Annonce> annonce1=annonceRepository.findById(annonce.getId());
        annonce1.orElseThrow(() -> new AnnonceNotExistException("Annonce id not exist"));
        Annonce existingAnnonce=annonce1.get();
        if(existingAnnonce.getStatus()==AnnonceStatus.ARCHIVED) throw new InvalidAnnonceException("cannot update annonce archived");

        if (annonce.getTitle() != null && !annonce.getTitle().equals(existingAnnonce.getTitle()))
            existingAnnonce.setTitle(annonce.getTitle());
        if (annonce.getCategory() != null) existingAnnonce.setCategory(annonce.getCategory());
        if (annonce.getDescription() != null) existingAnnonce.setDescription(annonce.getDescription());
        if (annonce.getPrice() != null) existingAnnonce.setPrice(annonce.getPrice());
        if (annonce.getLocation() != null) existingAnnonce.setLocation(annonce.getLocation());

        return annonceRepository.save(existingAnnonce);
    }

    @Override
    public Page<Annonce> filterAnnonceStatus(AnnonceStatus annonceStatus, int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return annonceRepository.getAllByStatus(annonceStatus,pageable);
    }

    @Override
    public Page<Annonce> disponibleAnnonce(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return annonceRepository.getAllByStatus(AnnonceStatus.ACTIVE,pageable);
    }

    @Override
    public Page<Annonce> filterbyCategoryAndLocation(SearchDTO searchDTO, int page, int size) {
        return annonceRepositoryImpl.findByCriteria(searchDTO,page,size);
    }

}
