package com.youjob.youjob.web.rest.auth;

import com.youjob.youjob.service.ForgetPassword.ForgotPasswordService;
import com.youjob.youjob.web.vm.auth.ForgotPasswordRequest;
import com.youjob.youjob.web.vm.auth.ResetPasswordRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/password")
public class ForgetPassController{
    private final ForgotPasswordService forgotPasswordService;

    public ForgetPassController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        try {
            forgotPasswordService.createPasswordResetTokenForUser(request.getEmail());
            return ResponseEntity.ok().body("Password reset email sent");
        } catch (Exception e) {
            return ResponseEntity.ok().body("If an account exists with that email, a password reset link will be sent: Error "+ e.getMessage());
        }
    }
    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        try {
            forgotPasswordService.resetPassword(request.getToken(), request.getNewPassword());
            return ResponseEntity.ok().body("Password has been reset successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
