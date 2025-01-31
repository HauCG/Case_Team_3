// Create file: src/main/java/com/example/case_team_3/model/RoomType.java
package com.example.case_team_3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "room_types")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomType_id;

    @Column(length = 100, nullable = false)
    private String roomType_name;
}