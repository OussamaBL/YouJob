package com.youjob.youjob.web.vm.mapper.annonce;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.web.vm.annonce.AnnonceCreateVM;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.mapper.helper.AnnonceCreateMapperHelper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {AnnonceCreateMapperHelper.class})
public interface AnnonceCreateMapper {
    @Mapping(source = "user_id", target = "createdBy", qualifiedByName = "mapUser")
    Annonce toAnnonce(AnnonceCreateVM annonceCreateVM);
    ResponseHistoryAnnnonceVM toResponseHistoryVM(Annonce annonce);
}
