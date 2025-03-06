package com.youjob.youjob.web.rest.auth;

import com.youjob.youjob.service.ForgetPassword.ForgotPasswordService;
import com.youjob.youjob.web.vm.auth.ForgotPasswordRequest;
import com.youjob.youjob.web.vm.auth.ResetPasswordRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/password")
public class ForgetPassController{
    private final ForgotPasswordService forgotPasswordService;

    public ForgetPassController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        try {
            forgotPasswordService.createPasswordResetTokenForUser(request.getEmail());
            return new ResponseEntity<>("Password reset email sent", HttpStatus.OK);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        try {
            forgotPasswordService.resetPassword(request.getToken(), request.getNewPassword());
            return new ResponseEntity<>("Password has been reset successfully",HttpStatus.CREATED);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
