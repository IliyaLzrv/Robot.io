package com.example.robot_io.domain;

import lombok.*;

@Setter
@Getter
@Builder
@Data
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private int level = 1;
    private Role role = Role.PLAYER;  // Default role is PLAYER
    private boolean isSuspended = false;


    public User(int id, String username, String email, String password, int level, Role role, boolean isSuspended) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.level = level;
        this.role = role;  
        this.isSuspended = isSuspended;
    }

    public User(int id, String username, String email, String password, int level) {
        this(id, username, email, password, level, Role.PLAYER, false);  // Default to PLAYER and not suspended
    }

}
