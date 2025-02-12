package com.youjob.youjob.service.impl;

import com.youjob.youjob.domain.Business;
import com.youjob.youjob.domain.Handyman;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.exception.NullVarException;
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

    @Override
    public User Profile(User user) {
        if(user.getId()==null) throw new NullVarException("id is null");
        Optional<User> us=userRepository.findById(user.getId());
        us.orElseThrow( () -> new UserNotExistException("User ot found"));
        User userExist=us.get();
        if (user.getUsername() != null && !user.getUsername().equals(userExist.getUsername())) {
            userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
                throw new UserAlreadyExistException("Username already exists");
            });
            userExist.setUsername(user.getUsername());
        }
        if (user.getName() != null) userExist.setName(user.getName());
        if (user.getPhoneNumber() != null) userExist.setPhoneNumber(user.getPhoneNumber());
        if (user.getAddress() != null) userExist.setAddress(user.getAddress());
        if (user instanceof Business) {
            Business businessUser = (Business) userExist;
            Business inputBusiness = (Business) user;
            if (inputBusiness.getVatNumber() != null)
                businessUser.setVatNumber(inputBusiness.getVatNumber());
        }
        if (user instanceof Handyman) {
            Handyman handymanUser = (Handyman) userExist;
            Handyman inputHandyman = (Handyman) user;
            if (inputHandyman.getSkills() != null && !inputHandyman.getSkills().isEmpty())
                handymanUser.setSkills(inputHandyman.getSkills());
            if (inputHandyman.getRating() != null)
                handymanUser.setRating(inputHandyman.getRating());
        }
        return userRepository.save(userExist);
    }
}
