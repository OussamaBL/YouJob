package com.youjob.youjob.service.ForgetPassword;

import com.youjob.youjob.domain.PasswordResetToken;
import com.youjob.youjob.domain.User;
import com.youjob.youjob.exception.ForgetPass.TokenInvalidException;
import com.youjob.youjob.exception.auth.UserNotExistException;
import com.youjob.youjob.repository.PasswordResetTokenRepository;
import com.youjob.youjob.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ForgotPasswordService {
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordService(UserRepository userRepository,
                                 PasswordResetTokenRepository tokenRepository,
                                 EmailService emailService,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createPasswordResetTokenForUser(String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotExistException("User not found"));

        tokenRepository.deleteByUser_Email(email);
        PasswordResetToken token = new PasswordResetToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusHours(24));

        tokenRepository.save(token);

        // Send email
        emailService.sendPasswordResetEmail(user.getEmail(), token.getToken(), user.getName());
    }

    @Transactional
    public void resetPassword(String token, String newPassword) throws Exception {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenInvalidException("Invalid token"));

        if (resetToken.isExpired()) {
            tokenRepository.delete(resetToken);
            throw new TokenInvalidException("Token has expired");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(resetToken);
    }
}
