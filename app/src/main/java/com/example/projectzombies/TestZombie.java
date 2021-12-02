package com.example.projectzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class TestZombie {
    private Bitmap image;

    private int x;
    private int y;


    public TestZombie(Bitmap bmp) {
        image = bmp;
    }

    public void update() {
        x += 2;

        /* Some reference code

        private int xVelocity = 10;
        private int yVelocity = 5;
        private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


        x += xVelocity;
        y += yVelocity;
        if ((x & gt; screenWidth - image.getWidth()) || (x & lt; 0)) {
            xVelocity = xVelocity * -1;
        }
        if ((y & gt; screenHeight - image.getHeight()) || (y & lt; 0)) {
            yVelocity = yVelocity * -1;
        }
         */


    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incrementXY(int x, int y) {
        this.x += x;
        this.y += y;
    }

}
