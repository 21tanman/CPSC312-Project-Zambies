package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GiantZombie extends Zombie {

    public GiantZombie(double x, double y, Resources resources, double lifeMult, GameView view) {

        super(x, y, view);
        fullLife = (int)(500*lifeMult);
        life = fullLife;
        xVeloc = -0.5;
        image = BitmapFactory.decodeResource(resources,R.drawable.zombie3);
        image = Bitmap.createScaledBitmap(image,250,250,true);
        moneyValue = 200;

    }


}