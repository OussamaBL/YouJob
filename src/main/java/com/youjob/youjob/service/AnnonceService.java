package com.youjob.youjob.service;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AnnonceService {
    Page<Annonce> getHistory_Annonce(UUID id,int page,int size);
    Annonce CreateAnnonce(Annonce annonce);
    void DeleteAnnonce(UUID id);
    Annonce updateAnnonce(Annonce annonce);
    Page<Annonce> filterAnnonceStatus(AnnonceStatus annonceStatus,int page,int size);
}
