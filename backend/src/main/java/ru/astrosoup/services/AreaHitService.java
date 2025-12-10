package ru.astrosoup.services;

import ru.astrosoup.DTOs.AreaHitRequest;

public class AreaHitService {



    public boolean isAreaHit(AreaHitRequest request) {
        int r = request.getR();
        float x = request.getX();
        float y = request.getY();

        if (x > 0 && y < 0) return false;
        if (x >= 0 && y >= 0) return Math.sqrt(x * x + y * y) <= r;
        if (x < 0 && y >= 0) return x >= -r/2f && y <= r;
        if (x <= 0 && y < 0) return y >= x - r/2f;
        return false;
    }
}
