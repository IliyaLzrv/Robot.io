package com.example.robot_io.domain;

import lombok.*;

@Setter
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lobby {
    private int id;
    private User user;
    private boolean robotSelected;

    public Lobby(int id, User user) {
        this.id = id;
        this.user = user;
        this.robotSelected = false;  // Initially, no robot is selected
    }
}
