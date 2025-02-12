package com.youjob.youjob.service.impl;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import com.youjob.youjob.exception.NullVarException;
import com.youjob.youjob.exception.auth.UserNotExistException;
import com.youjob.youjob.repository.AnnonceRepository;
import com.youjob.youjob.repository.UserRepository;
import com.youjob.youjob.service.AnnonceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnnonceServiceImpl implements AnnonceService {
    private final AnnonceRepository annonceRepository;
    private final UserRepository userRepository;
    public AnnonceServiceImpl(AnnonceRepository annonceRepository,UserRepository userRepository){
        this.annonceRepository=annonceRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Page<Annonce> getHistory_Annonce(UUID id, int page, int size) {
        if(id==null || id.equals("")) throw new NullVarException("id is null");
        userRepository.findById(id).orElseThrow(()->new UserNotExistException("user not found"));
        Pageable pageable= PageRequest.of(page,size);
        return annonceRepository.getAnnoncesByCreatedByIdAndStatus(id, AnnonceStatus.ARCHIVED,pageable);
    }
}
