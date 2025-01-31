package com.example.case_team_3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "verification_emails")
public class VerificationEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private boolean isUsed;

    public VerificationEmail() {
        this.createdTime = LocalDateTime.now();
        this.isUsed = false;
    }

    public VerificationEmail(String email, String code) {
        this();
        this.email = email;
        this.code = code;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(createdTime.plusMinutes(2));
    }

    public long getRemainingSeconds() {
        LocalDateTime expiryTime = createdTime.plusMinutes(2);
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(expiryTime)) {
            return 0;
        }
        return java.time.Duration.between(now, expiryTime).getSeconds();
    }
}
