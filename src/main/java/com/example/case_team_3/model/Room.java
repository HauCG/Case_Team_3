// Create file: src/main/java/com/example/case_team_3/model/Room.java
package com.example.case_team_3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer room_id;

    @Column(length = 100)
    private String room_img;

    @ManyToOne
    @JoinColumn(name = "roomType_id", nullable = false)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus room_status;

    @Column(nullable = false)
    private Float room_price;

    @Column(columnDefinition = "TEXT")
    private String room_description;

    public enum RoomStatus {
        available, booked, cleaning
    }
}