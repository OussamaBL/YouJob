package com.youjob.youjob.web.rest.user;

import com.youjob.youjob.domain.User;
import com.youjob.youjob.service.AuthService;
import com.youjob.youjob.web.vm.mapper.user.UserMapper;
import com.youjob.youjob.web.vm.user.ProfileVM;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserMapper userMapper;
    private final AuthService authService;
    public UserController(UserMapper userMapper,AuthService authService){
        this.userMapper=userMapper;
        this.authService=authService;
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
}
