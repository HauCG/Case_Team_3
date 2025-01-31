// Create file: src/main/java/com/example/case_team_3/repository/RoomRepository.java
package com.example.case_team_3.repository;

import com.example.case_team_3.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByRoomType_RoomType_id(Integer roomTypeId);
    List<Room> findByRoom_status(Room.RoomStatus status);
}