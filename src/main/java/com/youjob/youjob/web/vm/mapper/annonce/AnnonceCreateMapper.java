package com.youjob.youjob.web.vm.mapper.annonce;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.web.vm.annonce.AnnonceCreateVM;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AnnonceCreateMapper {
    Annonce toAnnonce(AnnonceCreateVM annonceCreateVM);
}
