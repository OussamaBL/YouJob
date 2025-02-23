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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @PostMapping("/approve/{id}")
    public ResponseEntity<Map<String,Object>> approveConsultation(@PathVariable UUID id){
        Consultation consultation=consultationService.approveConsultation(id);
        ResponseConsultationVM responseConsultationVM=consultationMapper.toResponseConsultation(consultation);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Consulation approved and project created successfully");
        response.put("data", responseConsultationVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/reject/{id}")
    public ResponseEntity<Map<String,Object>> rejectConsultation(@PathVariable UUID id){
        Consultation consultation=consultationService.rejectConsultation(id);
        ResponseConsultationVM responseConsultationVM=consultationMapper.toResponseConsultation(consultation);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Consulation rejected successfully");
        response.put("data", responseConsultationVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteConsultation(@PathVariable UUID id){
        consultationService.deleteConsultation(id);
        return new ResponseEntity<>("Consulation deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/annonceResponder/{uuid}")
    public ResponseEntity<Page<ResponseConsultationVM>> getAnnonceConsultationUser(@RequestParam UUID uuid, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Consultation> consultations=consultationService.getAnnonceConsultationUser(uuid,page,size);
        Page<ResponseConsultationVM> responseConsultationVMS=consultations.map((consultation ->  consultationMapper.toResponseConsultation(consultation) ));
        return new ResponseEntity<>(responseConsultationVMS,HttpStatus.OK);
    }
    @GetMapping("/ResponderAnnonce/{uuid}")
    public ResponseEntity<Page<ResponseConsultationVM>> getResponderAnnonce(@RequestParam UUID uuid, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Consultation> consultations=consultationService.getResponderAnnonce(uuid,page,size);
        Page<ResponseConsultationVM> responseConsultationVMS=consultations.map((consultation ->  consultationMapper.toResponseConsultation(consultation) ));
        return new ResponseEntity<>(responseConsultationVMS,HttpStatus.OK);
    }




}
