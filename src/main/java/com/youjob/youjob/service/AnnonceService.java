package com.youjob.youjob.service;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import com.youjob.youjob.web.vm.annonce.SearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;

import java.util.UUID;

public interface AnnonceService {
    Page<Annonce> getHistory_Annonce(UUID id,int page,int size);
    Annonce CreateAnnonce(Annonce annonce);
    void DeleteAnnonce(UUID id);
    Annonce updateAnnonce(Annonce annonce);
    Page<Annonce> filterAnnonceStatus(AnnonceStatus annonceStatus,int page,int size);
    Page<Annonce> disponibleAnnonce(int page,int size);

    Page<Annonce> filterbyCategoryAndLocation(SearchDTO searchDTO, int page, int size);
    Page<Annonce> getAnnonceUser(UUID uuid,int page, int size);
}
