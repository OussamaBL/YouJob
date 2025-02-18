package com.youjob.youjob.web.vm.mapper.consultation;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Consultation;
import com.youjob.youjob.web.vm.annonce.AnnonceCreateVM;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.consultation.ConsultationCreateVM;
import com.youjob.youjob.web.vm.consultation.ResponseConsultationVM;
import com.youjob.youjob.web.vm.mapper.helper.AnnonceCreateMapperHelper;
import com.youjob.youjob.web.vm.mapper.helper.ConsultationMapperHelper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {ConsultationMapperHelper.class})
public interface ConsultationMapper {
    @Mapping(source = "user_id", target = "responder", qualifiedByName = "mapUser")
    @Mapping(source = "annonce_id", target = "annonce", qualifiedByName = "mapAnnonce")
    Consultation toConsultation(ConsultationCreateVM consultationCreateVM);


   /* @Mapping(source = "responseDate", target = "responseDate")
    @Mapping(source = "message", target = "message")
    @Mapping(source = "accepted", target = "accepted")*/
    ResponseConsultationVM toResponseConsultation(Consultation consultation);
}
