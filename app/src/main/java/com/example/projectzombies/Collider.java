package com.example.projectzombies;

import android.graphics.Bitmap;

public class Collider {
    public static boolean pointWithImage(double x, double y, Bitmap image, double imageX, double imageY) {
        if (image == null) {return false;}

        if (x < image.getWidth() + imageX && x > imageX) {
            if (y < image.getHeight() + imageY && y > imageY) {
                return true;
            }
        }
        return false;
    }





}
