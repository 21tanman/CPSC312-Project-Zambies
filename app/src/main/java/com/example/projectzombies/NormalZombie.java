package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NormalZombie extends Zombie {

    public NormalZombie(double x, double y, Resources resources, double lifeMult, GameView view) {

        super(x, y, view);
        fullLife = (int)(100*lifeMult);
        life = fullLife;
        xVeloc = -2;
        image = BitmapFactory.decodeResource(resources,R.drawable.zombie1);
        image = Bitmap.createScaledBitmap(image,150,150,true);
        moneyValue = 20;

    }


}
