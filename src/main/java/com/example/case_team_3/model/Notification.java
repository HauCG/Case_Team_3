package com.example.case_team_3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "user_role", nullable = false)
    private String userRole;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "notification_message", nullable = false, length = 500)
    private String notificationMessage;

    @Column(name = "notification_created_time", nullable = false)
    private LocalDateTime notificationCreatedTime;

    public Notification(String userRole, String userId, String notificationMessage) {
        this.userRole = userRole;
        this.userId = userId;
        this.notificationMessage = notificationMessage;
        this.notificationCreatedTime = LocalDateTime.now();
    }

    public Notification() {
    }
}

