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

    public static boolean imageWithImage(double x, double y, Bitmap image, double x2, double y2, Bitmap image2) {
        if (image == null) {return false;}
        if (image2 == null) {return false;}

        if (x < x2 + image2.getWidth() && x + image.getWidth() > x2) {
            if (y < y2 + image2.getHeight() && y + image.getHeight() > y2) {
                return true;
            }
        }


        return false;
    }

    public static boolean imageWithRadius(double x, double y, double radius, Bitmap image, double imageX, double imageY) {
        if (image == null) {return false;}

        double realImageX = imageX + image.getWidth()/2;
        double realImageY = imageY + image.getHeight()/2;

        double dx = realImageX - x;
        double dy = realImageY - y;

        double dist = Math.sqrt(dx*dx+dy*dy);
        if (dist < radius) {
            return true;
        }

        return false;
    }




}
