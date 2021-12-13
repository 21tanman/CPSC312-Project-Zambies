package com.example.projectzombies;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Explosion {
    protected float radius = 0;
    protected float x;
    protected float y;
    protected GameView view;
    private Paint circlePaint;
    private static final int damage = 10;
    private static final int finalRadius = 400;

    public Explosion(float x, float y, GameView view) {
        this.x = x;
        this.y = y;
        this.view = view;
        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(20f);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x,y,radius,circlePaint);

    }

    public void update() {
        radius += 50;
        if (radius > finalRadius) {
            for (Zombie zombie : view.zombies) {
                if (Collider.imageWithRadius(x,y,radius,zombie.getImage(),zombie.getX(),zombie.getY())) {
                    zombie.subtractLife(damage);
                }
            }
            view.destroyExplosion(this);
        }
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
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

    public void setView(GameView view) {
        this.view = view;
    }
}
