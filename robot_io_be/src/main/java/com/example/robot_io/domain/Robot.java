package com.example.robot_io.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Robot {
    private int id;
    private User user;  // The user who owns the robot
 //   private String name;  // Robot name
    private int blastPower;  // The actual blast power
    private int speed;
    private BazookaType bazooka;  // Enum for bazooka type
    private String color;  // Team color
    private int level = 1;
    private int hp = 100;

    public Robot(int i, User testUser, String blitzBot, int i1, int i2, BazookaType bazookaType, String red, int i3, int i4) {
        this.id = i;
        this.user = testUser;
        this.blastPower = i1;
        this.speed = i2;
        this.bazooka = bazookaType;
        this.color = red;
        this.level = i3;
        this.hp = i4;
    }

    public int getEffectiveBlastPower() {
        return blastPower * bazooka.getBlastPowerMultiplier();
    }

    public int getShotsPerFire() {
        return bazooka.getShotsPerFire();
    }
}
