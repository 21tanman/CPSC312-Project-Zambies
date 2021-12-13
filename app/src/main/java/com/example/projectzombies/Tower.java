package com.example.projectzombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Tower {
    protected Bitmap image;

    protected double x;
    protected double y;

    protected int life;

    protected GameView view;

    protected int counter = 0;
    protected int attackRate;

    protected int fullLife;

    protected double bulletSpeed;

    private final int FULL_CHECK_TIME = 20;
    private int checkTime;

    Paint barColor;

    public Tower(double x, double y, GameView view) {
        this.x = x;
        this.y = y;
        this.view = view;

        barColor = new Paint();
        barColor.setColor(Color.rgb(0, 250, 0));
    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(image, (int)x, (int)y, null);

        int lifeBarSize = (int)((float)image.getWidth() * ((float)life/(float)fullLife));

        canvas.drawRect((int)x, (int)y, (int)(x + lifeBarSize), (int)(y + 10), barColor);

    }

    public void update() {

        checkTime--;
        if (checkTime <= 0) {
            checkTime = FULL_CHECK_TIME;
            for (Zombie zombie : view.zombies) {
                if (Collider.imageWithImage(x,y,image,zombie.getX(),zombie.getY(),zombie.getImage())) {
                    life -= FULL_CHECK_TIME;
                }
            }
        }


        counter++;
        if (counter >= attackRate) {
            fire();
            counter = 0;
        }

        if (life <= 0) {
            die();
        }
    }

    private void die() {
        view.destroyTower(this);
    }

    public abstract void fire();


    public void setY(double y) {
        this.y = y;
    }
}
