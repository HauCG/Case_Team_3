package com.example.case_team_3.repository;

import com.example.case_team_3.model.VerificationEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationEmailRepository extends JpaRepository<VerificationEmail, Long> {
    Optional<VerificationEmail> findFirstByEmailOrderByCreatedTimeDesc(String email);
    List<VerificationEmail> findAllByOrderByCreatedTimeDesc();
}
