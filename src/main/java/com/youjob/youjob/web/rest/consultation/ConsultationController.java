package com.youjob.youjob.web.rest.consultation;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Consultation;
import com.youjob.youjob.service.ConsultationService;
import com.youjob.youjob.web.vm.annonce.AnnonceCreateVM;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.consultation.ConsultationCreateVM;
import com.youjob.youjob.web.vm.consultation.ResponseConsultationVM;
import com.youjob.youjob.web.vm.mapper.consultation.ConsultationMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/consultation")
public class ConsultationController {
    private final ConsultationService consultationService;
    private final ConsultationMapper consultationMapper;
    public ConsultationController(ConsultationService consultationService,ConsultationMapper consultationMapper){
        this.consultationService=consultationService;
        this.consultationMapper=consultationMapper;
    }
    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> CreateConsultation(@RequestBody @Valid ConsultationCreateVM consultationCreateVM){
        Consultation consultation=consultationMapper.toConsultation(consultationCreateVM);
        Consultation consultationCreated=consultationService.CreateConsultation(consultation);
        ResponseConsultationVM responseConsultationVM=consultationMapper.toResponseConsultation(consultationCreated);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Consulation added successfully");
        response.put("data", responseConsultationVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
