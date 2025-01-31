// Create file: src/main/java/com/example/case_team_3/service/RoomService.java
package com.example.case_team_3.service;

import com.example.case_team_3.model.Room;
import com.example.case_team_3.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Integer id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getRoomsByType(Integer roomTypeId) {
        return roomRepository.findByRoomType_RoomType_id(roomTypeId);
    }

    public List<Room> getRoomsByStatus(Room.RoomStatus status) {
        return roomRepository.findByRoom_status(status);
    }
}