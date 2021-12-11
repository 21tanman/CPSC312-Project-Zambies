package com.example.projectzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Tower {
    protected Bitmap image;

    protected double x;
    protected double y;

    protected int life;

    protected GameView view;

    protected int counter = 0;
    protected int attackRate;

    protected double bulletSpeed;

    public Tower(double x, double y, GameView view) {
        this.x = x;
        this.y = y;
        this.view = view;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, (int)x, (int)y, null);
    }

    public void update() {
        counter++;
        if (counter >= attackRate) {
            fire();
            counter = 0;
        }
    }

    public abstract void fire();





}
