package com.youjob.youjob.web.vm.mapper.project;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Consultation;
import com.youjob.youjob.domain.Project;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.consultation.ResponseConsultationVM;
import com.youjob.youjob.web.vm.mapper.helper.ConsultationMapperHelper;
import com.youjob.youjob.web.vm.project.ResponseProjectVM;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProjectMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "annonce", target = "annonce", qualifiedByName = "mapAnnonce")
    @Mapping(source = "confirmedDate", target = "confirmedDate")
    @Mapping(source = "progress", target = "progress")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "accepted", target = "accepted")
    @Mapping(source = "dateComplete", target = "dateComplete")
    ResponseProjectVM toResponseProjectVM(Project project);

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
}
