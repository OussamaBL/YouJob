package com.youjob.youjob.web.rest.annonce;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.web.vm.annonce.AnnonceCreateVM;
import com.youjob.youjob.web.vm.mapper.annonce.AnnonceCreateMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/annonce")
public class AnnonceController {
    private final AnnonceCreateMapper annonceCreateMapper;
    public AnnonceController(AnnonceCreateMapper annonceCreateMapper){
        this.annonceCreateMapper=annonceCreateMapper;
    }
    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> CreateAnnonce(@RequestParam @Valid AnnonceCreateVM annonceCreateVM){
        Annonce annonce=
    }
}
