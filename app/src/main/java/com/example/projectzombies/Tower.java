package com.example.projectzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Tower {
    protected Bitmap image;

    protected int x;
    protected int y;

    protected int life;


    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }


}
