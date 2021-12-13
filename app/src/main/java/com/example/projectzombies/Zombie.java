package com.example.projectzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public abstract class Zombie {

    protected Bitmap image;

    protected double x;
    protected double y;

    protected double xVeloc;
    protected double yVeloc;

    protected int life;
    protected int fullLife;

    protected GameView view;

    protected int fullSlowedTimer = 10;
    protected int slowedTimer = 0;

    protected int moneyValue;


    Paint barColor;

    public Zombie(double x, double y, GameView view) {
        this.x = x;
        this.y = y;
        this.view = view;
        barColor = new Paint();
        barColor.setColor(Color.rgb(0, 250, 0));
    }


    public void update() {
        updatePositionBySpeed();
        if (life <= 0) {
            die();
        }
        slowedTimer--;
    }

    private void die() {
        view.addMoney(moneyValue);
        view.destroyZombie(this);
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, (int)x, (int)y, null);



        int lifeBarSize = (int)((float)image.getWidth() * ((float)life/(float)fullLife));

        canvas.drawRect((int)x, (int)y, (int)(x + lifeBarSize), (int)(y + 10), barColor);

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

    public void subtractLife(double life) {
        this.life -= life;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void incrementXY(double x, double y) {
        this.x += x;
        this.y += y;
    }
    public void updatePositionBySpeed() {

        if (slowedTimer > 0) {
            x += xVeloc/2;
            y += yVeloc/2;
        }
        else {
            x += xVeloc;
            y += yVeloc;
        }

    }

    public void getSlowed() {
        slowedTimer = fullSlowedTimer;
    }

}
