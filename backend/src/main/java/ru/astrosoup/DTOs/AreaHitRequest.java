package ru.astrosoup.DTOs;

import java.util.Arrays;

public class AreaHitRequest {
    private int r;
    private float x;
    private float y;

    public boolean validate() {
        return Arrays.asList(1, 2, 3, 4, 5).contains(r) &&
                Arrays.asList(-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2).contains(x) &&
                y > -5 && y < 3;
    }

    public int getR() {
        return r;
    }
    public void setR(int r) {
        this.r = r;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
}
