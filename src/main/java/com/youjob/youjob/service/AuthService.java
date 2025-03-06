package com.youjob.youjob.service;

import com.youjob.youjob.domain.Enum.UserRole;
import com.youjob.youjob.domain.User;
import org.springframework.data.domain.Page;

public interface AuthService {
    User Register(User user);
    User Login(String email,String password);
    User Profile(User user);
    Page<User> getUserBricoleur(int page,int size);
    Page<User> getUserRole(UserRole userRole, int page, int size);
    User fetchUser(String username);
}
