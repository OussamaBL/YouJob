package com.youjob.youjob.web.rest.annonce;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.service.AnnonceService;
import com.youjob.youjob.web.vm.annonce.AnnonceCreateVM;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.mapper.annonce.AnnonceCreateMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/annonce")
public class AnnonceController {
    private final AnnonceCreateMapper annonceCreateMapper;
    private final AnnonceService annonceService;
    public AnnonceController(AnnonceCreateMapper annonceCreateMapper,AnnonceService annonceService){
        this.annonceCreateMapper=annonceCreateMapper;
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
}
