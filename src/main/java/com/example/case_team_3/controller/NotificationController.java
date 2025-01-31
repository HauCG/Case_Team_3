package com.example.case_team_3.controller;

import com.example.case_team_3.service.NotificationService;
import com.example.case_team_3.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody Notification notification) {
        notificationService.sendNotification(notification.getUserRole(), notification.getUserId(), notification.getNotificationMessage());
        System.out.println("Notification sent: " + notification.getNotificationMessage() + " to " + notification.getUserRole() + " with id " + notification.getUserId());
        return new ResponseEntity<>("Notification sent successfully", HttpStatus.CREATED);
    }


    @Bean
    public CommandLineRunner commandLineRunner(@Autowired NotificationService notificationService) {
        return args -> {
            notificationService.sendNotification("admin", "1", "ngu nè");
        };
    };
}