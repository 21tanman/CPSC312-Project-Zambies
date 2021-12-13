package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FastZombie extends Zombie {

    public FastZombie(double x, double y, Resources resources, double lifeMult, GameView view) {

        super(x, y, view);
        fullLife = (int)(40*lifeMult);
        life = fullLife;
        xVeloc = -4;
        image = BitmapFactory.decodeResource(resources,R.drawable.zombie2);
        image = Bitmap.createScaledBitmap(image,100,100,true);
        moneyValue = 10;

    }


}
