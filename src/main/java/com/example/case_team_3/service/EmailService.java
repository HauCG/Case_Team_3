package com.example.case_team_3.service;

import com.example.case_team_3.model.VerificationEmail;
import com.example.case_team_3.repository.VerificationEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;
import java.util.List;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VerificationEmailRepository verificationEmailRepository;

    public String generateNumericCode(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    public String sendVerificationCode(String email) throws MessagingException {
        String code = generateNumericCode(6);
        VerificationEmail verificationEmail = new VerificationEmail(email, code);
        verificationEmailRepository.save(verificationEmail);
        
        String subject = "Mã xác nhận của bạn";
        String content = String.format(
            "<div style='font-family: Arial, sans-serif; padding: 20px;'>" +
            "<h2>Mã xác nhận của bạn</h2>" +
            "<p>Mã xác nhận của bạn là: <strong>%s</strong></p>" +
            "<p>Mã này sẽ hết hạn sau 2 phút.</p>" +
            "</div>",
            code
        );
        
        sendEmailWithConfirmCode(email, subject, content);
        return code;
    }

    public boolean verifyCode(String email, String code) {
        return verificationEmailRepository.findFirstByEmailOrderByCreatedTimeDesc(email)
            .map(verificationEmail -> {
                if (verificationEmail.isUsed()) {
                    return false;
                }
                if (verificationEmail.isExpired()) {
                    return false;
                }
                if (!verificationEmail.getCode().equals(code)) {
                    return false;
                }
                verificationEmail.setUsed(true);
                verificationEmailRepository.save(verificationEmail);
                return true;
            })
            .orElse(false);
    }

    public List<VerificationEmail> getAllVerificationEmails() {
        return verificationEmailRepository.findAllByOrderByCreatedTimeDesc();
    }

    public void sendEmailWithConfirmCode(String recipientEmail, String subject, String content) throws MessagingException {
        if (recipientEmail == null || recipientEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Email người nhận không được để trống");
        }
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề email không được để trống");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Nội dung email không được để trống");
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(recipientEmail.trim());
            helper.setSubject(subject);
            helper.setText(content, true);
            
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MessagingException("Lỗi khi gửi email: " + e.getMessage(), e);
        }
    }
}
