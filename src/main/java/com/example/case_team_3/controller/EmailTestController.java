package com.example.case_team_3.controller;

import com.example.case_team_3.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EmailTestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/email")
    public String showEmailTestPage(Model model) {
        model.addAttribute("verificationEmails", emailService.getAllVerificationEmails());
        return "email-test";
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String email, Model model) {
        try {
            emailService.sendVerificationCode(email);
            model.addAttribute("message", "Mã xác nhận đã được gửi đến email của bạn!");
            model.addAttribute("success", true);
        } catch (MessagingException e) {
            model.addAttribute("message", "Lỗi khi gửi email: " + e.getMessage());
            model.addAttribute("success", false);
        }
        model.addAttribute("verificationEmails", emailService.getAllVerificationEmails());
        return "email-test";
    }

    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam String email, @RequestParam String code, Model model) {
        boolean isValid = emailService.verifyCode(email, code);
        if (isValid) {
            model.addAttribute("message", "Mã xác nhận hợp lệ!");
            model.addAttribute("success", true);
        } else {
            model.addAttribute("message", "Mã xác nhận không hợp lệ hoặc đã hết hạn!");
            model.addAttribute("success", false);
        }
        model.addAttribute("verificationEmails", emailService.getAllVerificationEmails());
        return "email-test";
    }
}
