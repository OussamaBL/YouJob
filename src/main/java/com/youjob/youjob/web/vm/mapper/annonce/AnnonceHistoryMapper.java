package com.youjob.youjob.web.vm.mapper.annonce;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Business;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.user.ProfileVM;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AnnonceHistoryMapper {
    ResponseHistoryAnnnonceVM toResponseAnnonce(Annonce annonce);
}
