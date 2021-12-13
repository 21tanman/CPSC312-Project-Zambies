package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ExplosiveBullet extends Bullet {

    public ExplosiveBullet(double x, double y, double xVeloc, double yVeloc, Resources resources, GameView view) {
        super(x, y, xVeloc, yVeloc, view);
        image = BitmapFactory.decodeResource(resources,R.drawable.bullet1);
        image = Bitmap.createScaledBitmap(image,20,20,true);
        damage = 0;
    }
    
    @Override
    protected void die() {
        view.addExplosion((float)x,(float)y);
        view.destroyBullet(this);
    }


}
