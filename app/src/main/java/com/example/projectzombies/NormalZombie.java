package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NormalZombie extends Zombie {

    public NormalZombie(int x, int y, Resources resources) {
        super(x, y);
        life = 100;
        xVeloc = -2;
        image = BitmapFactory.decodeResource(resources,R.drawable.zombie1);
        image = Bitmap.createScaledBitmap(image,150,150,true);

    }


}
