package com.example.robot_io.domain;

public enum BazookaType {
    NORMAL(1, 1),          // Normal Bazooka: 1 shot per fire, blast power 1
    DOUBLE(2, 1),          // Double Bazooka: 2 shots per fire, same blast power
    BIGGER(1, 2);          // Bigger Bazooka: 1 shot per fire, higher blast power

    private final int shotsPerFire;
    private final int blastPowerMultiplier;

    BazookaType(int shotsPerFire, int blastPowerMultiplier) {
        this.shotsPerFire = shotsPerFire;
        this.blastPowerMultiplier = blastPowerMultiplier;
    }

    public int getShotsPerFire() {
        return shotsPerFire;
    }

    public int getBlastPowerMultiplier() {
        return blastPowerMultiplier;
    }
}
