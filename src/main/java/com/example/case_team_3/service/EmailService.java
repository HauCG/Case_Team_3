package com.example.case_team_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    // Tạo mã xác nhận 6 chữ số
    public String generateNumericCode(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    public void sendEmailWithConfirmCode(String recipientEmail, String subject, String content) throws MessagingException {
        // Validate input parameters
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
            helper.setText(content, true); // true indicates this is HTML content
            
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MessagingException("Lỗi khi gửi email: " + e.getMessage(), e);
        }
    }
}
