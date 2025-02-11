package com.youjob.youjob.service.ForgetPassword;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendPasswordResetEmail(String email, String token, String name) throws MessagingException {
        String resetUrl = "https://localhost:8443/api/auth/password/reset-password?token=" + token;
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        String emailContent = String.format("""
            Hello %s,
                        
            You have requested to reset your password. Click the link below to reset it:
            %s
                        
            This link will expire in 24 hours.
                        
            If you didn't request this, please ignore this email.
                        
            Best regards,
            Your Application Team
            """, name, resetUrl);
        helper.setTo(email);
        helper.setSubject("Password Reset Request");
        helper.setText(emailContent);
        helper.setFrom("Support@Youjob.com");

        mailSender.send(message);
    }
}
