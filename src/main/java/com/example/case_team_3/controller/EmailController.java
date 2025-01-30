package com.example.case_team_3.controller;

import com.example.case_team_3.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-verification")
    public ResponseEntity<String> sendVerificationEmail(@RequestParam String recipientEmail) {
        try {
            // Kiểm tra định dạng email
            if (!recipientEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

                return ResponseEntity.badRequest()
                    .body("Email không hợp lệ");
            }

            String verificationCode = emailService.generateNumericCode(6);
            System.out.println("Mã xác nhận: " + verificationCode);

            String subject = "Mã xác nhận của bạn";
            String content = """
                    <html>
                    <head>
                        <style>
                            body {
                                font-family: 'Arial', sans-serif;
                                line-height: 1.6;
                                color: #333;
                                background-color: #f4f4f4;
                                padding: 20px;
                            }
                            .container {
                                max-width: 600px;
                                margin: 20px auto;
                                padding: 20px;
                                border: 1px solid #ddd;
                                border-radius: 10px;
                                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                                background-color: #fff;
                            }
                            .code {
                                font-size: 24px;
                                font-weight: bold;
                                color: #007bff;
                                text-align: center;
                                padding: 10px;
                                margin: 20px 0;
                                border: 2px dashed #007bff;
                                border-radius: 5px;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                            <h2>Xác nhận email của bạn</h2>
                            <p>Xin chào,</p>
                            <p>Đây là mã xác nhận của bạn:</p>
                            <div class="code">%s</div>
                            <p>Mã này sẽ hết hạn sau 5 phút.</p>
                            <p>Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này.</p>
                            <p>Trân trọng,<br>Team Support</p>
                        </div>
                    </body>
                    </html>
                    """.formatted(verificationCode);

            emailService.sendEmailWithConfirmCode(recipientEmail, subject, content);
            return ResponseEntity.ok("Mã xác nhận đã được gửi thành công!");
        } catch (MessagingException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("550") || errorMessage.contains("Invalid Addresses")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email không tồn tại hoặc không thể gửi đến địa chỉ này");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi gửi email: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Có lỗi xảy ra: " + e.getMessage());
        }
    }
}