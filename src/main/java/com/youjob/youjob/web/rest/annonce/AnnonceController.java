package com.youjob.youjob.web.rest.annonce;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.service.AnnonceService;
import com.youjob.youjob.web.vm.annonce.AnnonceCreateVM;
import com.youjob.youjob.web.vm.annonce.AnnonceUpdateVM;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.mapper.annonce.AnnonceCreateMapper;
import com.youjob.youjob.web.vm.mapper.annonce.AnnonceUpdateMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/annonce")
public class AnnonceController {
    private final AnnonceCreateMapper annonceCreateMapper;
    private final AnnonceUpdateMapper annonceUpdateMapper;
    private final AnnonceService annonceService;
    public AnnonceController(AnnonceCreateMapper annonceCreateMapper,AnnonceUpdateMapper annonceUpdateMapper,AnnonceService annonceService){
        this.annonceCreateMapper=annonceCreateMapper;
        this.annonceUpdateMapper=annonceUpdateMapper;
        this.annonceService=annonceService;
    }
    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> CreateAnnonce(@RequestBody @Valid AnnonceCreateVM annonceCreateVM){
        Annonce annonce=annonceCreateMapper.toAnnonce(annonceCreateVM);
        Annonce annonceCreated=annonceService.CreateAnnonce(annonce);
        ResponseHistoryAnnnonceVM responseHistoryAnnnonceVM=annonceCreateMapper.toResponseHistoryVM(annonceCreated);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Annonce added successfully");
        response.put("data", responseHistoryAnnnonceVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeteteAnnonce(@PathVariable UUID id){
        annonceService.DeleteAnnonce(id);
        return new ResponseEntity<>("Annonce deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String,Object>> edit(@RequestBody @Valid AnnonceUpdateVM annonceUpdateVM, @PathVariable UUID id){
        Annonce annonce=annonceUpdateMapper.toAnnonce(annonceUpdateVM);
        annonce.setId(id);
        Annonce annonce1=annonceService.updateAnnonce(annonce);
        ResponseHistoryAnnnonceVM responseAnnnonceVM=annonceUpdateMapper.toResponseHistoryVM(annonce1);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Annonce updated successfully");
        response.put("data", responseAnnnonceVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
