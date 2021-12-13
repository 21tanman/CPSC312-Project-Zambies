package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FlameTower extends Tower {
    public FlameTower(double x, double y, Resources resources, GameView view) {
        super(x, y, view);
        fullLife = 200;
        life = fullLife;
        image = BitmapFactory.decodeResource(resources,R.drawable.tower1);
        image = Bitmap.createScaledBitmap(image,TouchHandler.TOWER_SIZE,TouchHandler.TOWER_SIZE,true);
        bulletSpeed = 30;
        attackRate = 150;
    }

    @Override
    public void fire() {
        if (view.getFirstZombie() == null) {return;}
        Zombie z = view.getFirstZombie();
        Double xDist = z.getX() - x;
        Double yDist = z.getY() - y;

        double normalizer = Math.sqrt(xDist*xDist + yDist*yDist);
        double xVec = xDist/normalizer;
        double yVec = yDist/normalizer;

        xVec *= bulletSpeed;
        yVec *= bulletSpeed;

        double xpos = x + image.getWidth()/2;
        double ypos = y + image.getHeight()/2;


        view.addBullet(1,xpos,ypos,xVec,yVec);
    }



}