package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NormalTower extends Tower {
    public NormalTower(double x, double y, Resources resources, GameView view) {
        super(x, y, view);
        life = 100;
        image = BitmapFactory.decodeResource(resources,R.drawable.tower0);
        image = Bitmap.createScaledBitmap(image,TouchHandler.TOWER_SIZE,TouchHandler.TOWER_SIZE,true);
        bulletSpeed = 30;
        attackRate = 100;
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


        view.addBullet(0,xpos,ypos,xVec,yVec);
    }



}
