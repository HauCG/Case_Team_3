package com.example.case_team_3.service;

import com.example.case_team_3.model.Notification;
import com.example.case_team_3.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(String userRole, String userId, String notificationMessage) {
        Notification notification = new Notification(userRole, userId, notificationMessage);
        notificationRepository.save(notification);
        System.out.println("Notification sent: " + notificationMessage + " to " + userRole + " with id " + userId);
    }

    public Notification addNotification(String userRole, String userId, String notificationMessage) {
        Notification notification = new Notification(userRole, userId, notificationMessage);
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    public void deleteAllNotificationsByUserId(String userId) {
        notificationRepository.deleteByUserId(userId);
    }

}