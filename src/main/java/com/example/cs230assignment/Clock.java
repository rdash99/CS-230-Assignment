package com.example.cs230assignment;

public class Clock extends Entity {
    private int time;

    public Clock(int x, int y, int time) {
        super("Clock", x, y);
        this.time = time;
    }

    public int gettime() {
        return time;
    }

    public void settime(int time) {
        this.time = time;
    }

    public void tick() {
        time--;
    }
}