package com.example.projectzombies;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NormalTower extends Tower {
    public NormalTower(int x, int y, Resources resources) {
        super(x, y);
        life = 100;
        image = BitmapFactory.decodeResource(resources,R.drawable.tower0);
        image = Bitmap.createScaledBitmap(image,200,200,true);

    }


}
