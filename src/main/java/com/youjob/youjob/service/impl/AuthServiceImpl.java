package com.youjob.youjob.service.impl;

import com.youjob.youjob.domain.User;
import com.youjob.youjob.exception.auth.UserAlreadyExistException;
import com.youjob.youjob.exception.auth.UserNotExistException;
import com.youjob.youjob.repository.UserRepository;
import com.youjob.youjob.service.AuthService;
import com.youjob.youjob.utils.JwtUtil;
import com.youjob.youjob.utils.PasswordEncoderUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,PasswordEncoderUtil passwordEncoderUtil,JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.passwordEncoderUtil=passwordEncoderUtil;
        this.jwtUtil=jwtUtil;
    }

    @Override
    public User Register(User user) {
        Optional<User> us=userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        us.ifPresent(u -> { throw new UserAlreadyExistException("Username or Email already exist"); });
        user.setPassword(passwordEncoderUtil.encodePassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User Login(String email, String password) {
        Optional<User> us=userRepository.findByEmail(email);
        us.orElseThrow( () -> new UserNotExistException("Email not exist") );
        if(!passwordEncoderUtil.matches(password,us.get().getPassword()))
            throw new UserNotExistException("Password incorrect");
        return us.get();
    }
}
