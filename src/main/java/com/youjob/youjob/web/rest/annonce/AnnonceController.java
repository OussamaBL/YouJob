package com.youjob.youjob.web.rest.annonce;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.AnnonceStatus;
import com.youjob.youjob.repository.impl.AnnonceRepositoryImpl;
import com.youjob.youjob.service.AnnonceService;
import com.youjob.youjob.web.vm.annonce.AnnonceCreateVM;
import com.youjob.youjob.web.vm.annonce.AnnonceUpdateVM;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.annonce.SearchDTO;
import com.youjob.youjob.web.vm.mapper.annonce.AnnonceCreateMapper;
import com.youjob.youjob.web.vm.mapper.annonce.AnnonceUpdateMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/annonce")
public class AnnonceController {
    private final AnnonceCreateMapper annonceCreateMapper;
    private final AnnonceUpdateMapper annonceUpdateMapper;
    private final AnnonceService annonceService;
    private final AnnonceRepositoryImpl annonceRepositoryImpl;
    public AnnonceController(AnnonceCreateMapper annonceCreateMapper,AnnonceUpdateMapper annonceUpdateMapper,AnnonceService annonceService,AnnonceRepositoryImpl annonceRepositoryImpl){
        this.annonceCreateMapper=annonceCreateMapper;
        this.annonceUpdateMapper=annonceUpdateMapper;
        this.annonceService=annonceService;
        this.annonceRepositoryImpl=annonceRepositoryImpl;
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
    @GetMapping("/filter")
    public ResponseEntity<Page<ResponseHistoryAnnnonceVM>> filterAnnonceStatus(@RequestParam AnnonceStatus status,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Annonce> annonces=annonceService.filterAnnonceStatus(status,page,size);
        Page<ResponseHistoryAnnnonceVM> responseAnnonce=annonces.map((annonce ->  annonceCreateMapper.toResponseHistoryVM(annonce) ));
        return new ResponseEntity<>(responseAnnonce,HttpStatus.OK);
    }
    @GetMapping("/AnnonceUser")
    public ResponseEntity<Page<ResponseHistoryAnnnonceVM>> AnnonceUser(@RequestParam UUID id,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Annonce> annonces=annonceService.getAnnonceUser(id,page,size);
        Page<ResponseHistoryAnnnonceVM> responseAnnonce=annonces.map((annonce ->  annonceCreateMapper.toResponseHistoryVM(annonce) ));
        return new ResponseEntity<>(responseAnnonce,HttpStatus.OK);
    }
    @GetMapping("/dispo")
    public ResponseEntity<Page<ResponseHistoryAnnnonceVM>> disponibleAnnonce(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Annonce> annonces=annonceService.disponibleAnnonce(page,size);
        Page<ResponseHistoryAnnnonceVM> responseAnnonce=annonces.map((annonce ->  annonceCreateMapper.toResponseHistoryVM(annonce) ));
        return new ResponseEntity<>(responseAnnonce,HttpStatus.OK);
    }

    @PostMapping("/filterbyCategoryLocation")
    public ResponseEntity<Page<ResponseHistoryAnnnonceVM>> filterbyCategoryLocation(@RequestBody @Valid SearchDTO searchDTO,
                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                    @RequestParam(defaultValue = "10") int size){
        Page<Annonce> annonces=annonceService.filterbyCategoryAndLocation(searchDTO,page,size);
        Page<ResponseHistoryAnnnonceVM> responseAnnonce=annonces.map((annonce ->  annonceCreateMapper.toResponseHistoryVM(annonce) ));
        return new ResponseEntity<>(responseAnnonce,HttpStatus.OK);
    }
}
