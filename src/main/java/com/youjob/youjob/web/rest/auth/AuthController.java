package com.youjob.youjob.web.rest.auth;

import com.youjob.youjob.domain.User;
import com.youjob.youjob.service.AuthService;
import com.youjob.youjob.utils.JwtUtil;
import com.youjob.youjob.web.vm.auth.LoginVM;
import com.youjob.youjob.web.vm.auth.RegisterVM;
import com.youjob.youjob.web.vm.mapper.auth.LoginMapper;
import com.youjob.youjob.web.vm.mapper.auth.RegisterMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final LoginMapper loginMapper;
    private final RegisterMapper registerMapper;
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    public AuthController(LoginMapper loginMapper,RegisterMapper registerMapper,AuthService authService,JwtUtil jwtUtil){
        this.loginMapper=loginMapper;
        this.registerMapper=registerMapper;
        this.authService=authService;
        this.jwtUtil=jwtUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestBody @Valid RegisterVM registerVM) {
        User user=registerMapper.mapToSpecificUser(registerVM);
        User userAdded=authService.Register(user);
        String token= jwtUtil.generateToken(userAdded.getEmail(),userAdded.getUsername(),userAdded.getRole().name());
        try{
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginVM loginVM){
        User user=loginMapper.toUser(loginVM);
        User us=authService.Login(user.getEmail(),user.getPassword());
        String token= jwtUtil.generateToken(us.getEmail(),us.getUsername(),us.getRole().name());
        try{
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
