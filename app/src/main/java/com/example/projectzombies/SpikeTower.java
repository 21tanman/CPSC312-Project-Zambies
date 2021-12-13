package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SpikeTower extends Tower {
    public SpikeTower(double x, double y, Resources resources, GameView view) {
        super(x, y, view);
        fullLife = 2000;
        life = fullLife;

        image = BitmapFactory.decodeResource(resources,R.drawable.tower2);
        image = Bitmap.createScaledBitmap(image,200,200,true);
        bulletSpeed = 30;
        attackRate = 10;
    }

    @Override
    public void fire() {
        for (Zombie zombie : view.zombies) {
            if (Collider.imageWithImage(x,y,image,zombie.getX(),zombie.getY(),zombie.getImage())) {
                zombie.getSlowed();
                zombie.subtractLife(1);
            }
        }
    }



}