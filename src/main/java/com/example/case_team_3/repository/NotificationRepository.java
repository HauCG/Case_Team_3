package com.example.case_team_3.repository;

import com.example.case_team_3.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Transactional
    void deleteByUserId(String userId);
}
