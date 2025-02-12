package com.youjob.youjob.service;

import com.youjob.youjob.domain.Annonce;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AnnonceService {
    Page<Annonce> getHistory_Annonce(UUID id,int page,int size);

}
