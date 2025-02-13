package com.youjob.youjob.web.vm.mapper.helper;

import com.youjob.youjob.domain.User;
import com.youjob.youjob.exception.auth.UserNotExistException;
import com.youjob.youjob.repository.UserRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnnonceCreateMapperHelper {
    private final UserRepository userRepository;

    public AnnonceCreateMapperHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Named("mapUser")
    public User mapUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("User not found with ID: " + userId));
    }
}
