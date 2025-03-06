package com.youjob.youjob.web.rest.user;

import com.youjob.youjob.domain.Annonce;
import com.youjob.youjob.domain.Enum.UserRole;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.service.AnnonceService;
import com.youjob.youjob.service.AuthService;
import com.youjob.youjob.web.vm.annonce.ResponseHistoryAnnnonceVM;
import com.youjob.youjob.web.vm.auth.ResponseUserAuthVM;
import com.youjob.youjob.web.vm.mapper.annonce.AnnonceCreateMapper;
import com.youjob.youjob.web.vm.mapper.user.UserMapper;
import com.youjob.youjob.web.vm.user.ProfileVM;
import com.youjob.youjob.web.vm.user.ResponseUserVM;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserMapper userMapper;
    private final AuthService authService;
    private final AnnonceService annonceService;
    private final AnnonceCreateMapper annonceCreateMapper;
    public UserController(UserMapper userMapper,AuthService authService,AnnonceService annonceService,AnnonceCreateMapper annonceCreateMapper){
        this.userMapper=userMapper;
        this.authService=authService;
        this.annonceService=annonceService;
        this.annonceCreateMapper=annonceCreateMapper;
    }
    @Transactional
    @PutMapping("/profile/{id}")
    /*@PreAuthorize("authentication.principal.id == #id")*/
    public ResponseEntity<String> Profile(@RequestBody @Valid ProfileVM profileVM, @PathVariable UUID id){
        User user=userMapper.mapToSpecificUser(profileVM);
        user.setId(id);
        User userUpdated=authService.Profile(user);
        /*ResponseUserVM responseUserVM=profileMapper.toResponseUserVM(userUpdated);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User updated successfully");
        response.put("data", responseUserVM);*/
        return new ResponseEntity<>("User ("+userUpdated.getName()+") updated successfully", HttpStatus.OK);
    }
    @GetMapping("/annonce/history/{id}")
    public ResponseEntity<Page<ResponseHistoryAnnnonceVM>> History_annonce(@PathVariable UUID id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Annonce> annonces=annonceService.getHistory_Annonce(id, page, size);
        Page<ResponseHistoryAnnnonceVM> responseHistoryAnnnonceVMS=annonces.map(annonce -> annonceCreateMapper.toResponseHistoryVM(annonce));
        return new ResponseEntity<>(responseHistoryAnnnonceVMS,HttpStatus.OK);
    }
    @GetMapping("/users/bricoleur")
    public ResponseEntity<Page<ResponseUserVM>> UsersBricoleur(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<User> users=authService.getUserBricoleur( page, size);
        Page<ResponseUserVM> responseUserVM=users.map(user -> userMapper.toResponseUserVM(user));
        return new ResponseEntity<>(responseUserVM,HttpStatus.OK);
    }
    @GetMapping("/users/{userRole}")
    public ResponseEntity<Page<ResponseUserVM>> UsersRole(@RequestParam UserRole userRole,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<User> users=authService.getUserRole(userRole, page, size);
        Page<ResponseUserVM> responseUserVM=users.map(user -> userMapper.toResponseUserVM(user));
        return new ResponseEntity<>(responseUserVM,HttpStatus.OK);
    }
    @GetMapping("/fetch/{username}")
    public ResponseEntity<ResponseUserAuthVM> fetchUser(@PathVariable String username){
        User user=authService.fetchUser(username);
        ResponseUserAuthVM responseUserAuthVM=userMapper.toResponseUserAuthVM(user);
        return new ResponseEntity<>(responseUserAuthVM,HttpStatus.OK);
    }
}
