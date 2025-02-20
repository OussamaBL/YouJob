package com.youjob.youjob.web.vm.mapper.consultation;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Consultation;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.web.vm.annonce.AnnonceCreateVM;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.consultation.ConsultationCreateVM;
import com.youjob.youjob.web.vm.consultation.ResponseConsultationVM;
import com.youjob.youjob.web.vm.mapper.helper.AnnonceCreateMapperHelper;
import com.youjob.youjob.web.vm.mapper.helper.ConsultationMapperHelper;
import com.youjob.youjob.web.vm.user.ResponseUserVM;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
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

    @Named("mapAnnonce")
    default ResponseHistoryAnnnonceVM mapAnnonce(Annonce annonce) {
        if (annonce == null) return null;
        ResponseHistoryAnnnonceVM dto = new ResponseHistoryAnnnonceVM();
        dto.setId(annonce.getId());
        dto.setTitle(annonce.getTitle());
        dto.setDescription(annonce.getDescription());
        dto.setCategory(annonce.getCategory());
        dto.setPrice(annonce.getPrice());
        dto.setLocation(annonce.getLocation());
        dto.setCreatedDate(annonce.getCreatedDate());
        dto.setStatus(annonce.getStatus());
        return dto;
    }

    @Named("mapUser")
    default ResponseUserVM mapUser(User user) {
        if (user == null) return null;
        ResponseUserVM dto = new ResponseUserVM();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }
}
