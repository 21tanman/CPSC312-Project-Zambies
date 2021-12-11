package com.example.projectzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public abstract class Bullet {
    protected Bitmap image;

    protected double x;
    protected double y;

    protected double xVeloc;
    protected double yVeloc;

    protected GameView view;

    protected double damage;

    public Bullet(double x, double y, double xVeloc, double yVeloc, GameView view) {
        this.x = x;
        this.y = y;
        this.xVeloc = xVeloc;
        this.yVeloc = yVeloc;
        this.view = view;
    }


    public void update() {
        updatePositionBySpeed();
        checkHitsWithZombie();
        checkEdges();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, (int)x, (int)y, null);

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void incrementXY(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void updatePositionBySpeed() {
        x += xVeloc;
        y += yVeloc;
    }

    public void checkHitsWithZombie() {
        for (Zombie zombie : view.zombies) {
            //Log.d(GameView.TAG,"zombX: " + zombie.getX());
            //Log.d(GameView.TAG,"zombY: " + zombie.getY());
            //Log.d(GameView.TAG,"PointX: " + x);
            //Log.d(GameView.TAG,"PointY: " + y);
            if (Collider.pointWithImage(x,y,zombie.getImage(),zombie.getX(),zombie.getY())) {
                die();
                zombie.subtractLife(damage);
            }
        }
    }
    public void checkEdges() {
        if (x < -200) {
            die();
        }
        if (x > GameView.screenWidth + 200) {
            die();
        }
        if (y < -200) {
            die();
        }
        if (y > GameView.screenHeight + 200) {
            die();
        }

    }

    protected abstract void die();


}
