// Create file: src/main/java/com/example/case_team_3/repository/RoomTypeRepository.java
package com.example.case_team_3.repository;

import com.example.case_team_3.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
}