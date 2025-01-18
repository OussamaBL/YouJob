package com.youjob.youjob.service;

import com.youjob.youjob.domain.User;

public interface AuthService {
    User Register(User user);
    User Login(String email,String password);
}
