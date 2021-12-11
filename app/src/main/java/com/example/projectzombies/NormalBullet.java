package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NormalBullet extends Bullet {

    public NormalBullet(double x, double y, double xVeloc, double yVeloc, Resources resources, GameView view) {
        super(x, y, xVeloc, yVeloc, view);
        image = BitmapFactory.decodeResource(resources,R.drawable.bullet0);
        image = Bitmap.createScaledBitmap(image,20,20,true);
        damage = 20;
    }

    @Override
    protected void die() {
        view.destroyBullet(this);
    }


}
