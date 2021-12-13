package com.example.projectzombies;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainGameThread thread;

    public static final String TAG = "MYTAG";
    public static final int screenHeight = 1080;
    public static final int screenWidth = 1977;

    private TouchHandler touchHandler;

    //Beginning of lists
    private List<Tower> towers;
    public List<Zombie> zombies;
    private List<Bullet> bullets;

    private List<Explosion> explosions;

    private List<Bullet> bulletsToDestroy;
    private List<Zombie> zombiesToDestroy;
    private List<Explosion> explosionsToDestroy;

    private int money;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainGameThread(getHolder(), this);
        setFocusable(true);


        money = 150;

        touchHandler = new TouchHandler(getResources(),this);




        this.setOnTouchListener(touchHandler);
        initializeStuff();

        spawnZombie();



    }
    private void initializeStuff() {
        towers = new ArrayList<>();
        zombies = new ArrayList<>();
        bullets = new ArrayList<>();
        explosions = new ArrayList<>();

        bulletsToDestroy = new ArrayList<>();
        zombiesToDestroy = new ArrayList<>();
        explosionsToDestroy = new ArrayList<>();

    }

    public void destroyBullet(Bullet b) {
        bulletsToDestroy.add(b);
        b.setY(-1000);
    }
    private void destroyBullets() {
        if (bulletsToDestroy.size() == 0) {
            return;
        }
        for (int i = bulletsToDestroy.size()-1; i > -1; i -= 1) {
            bullets.remove(bulletsToDestroy.get(i));
            bulletsToDestroy.remove(i);
        }
    }

    public void destroyZombie(Zombie z) {
        zombiesToDestroy.add(z);
        z.setY(-1000);
    }
    private void destroyZombies() {
        if (zombiesToDestroy.size() == 0) {
            return;
        }
        for (int i = zombiesToDestroy.size()-1; i > -1; i -= 1) {
            zombies.remove(zombiesToDestroy.get(i));
            zombiesToDestroy.remove(i);
        }
    }
    public void destroyExplosion(Explosion e) {
        explosionsToDestroy.add(e);
        e.setY(-1000);
    }
    private void destroyExplosions() {
        if (explosionsToDestroy.size() == 0) {
            return;
        }
        for (int i = explosionsToDestroy.size()-1; i > -1; i -= 1) {
            explosions.remove(explosionsToDestroy.get(i));
            explosionsToDestroy.remove(i);
        }
    }

    public int getMoney() {
        return money;
    }

    public void getTowerPlacement(int towerType, int x, int y) {

        if (x < 0 || x > screenWidth)  {
            return;
        }
        if (y < 0 || y > screenHeight)  {
            return;
        }

        int moneyAmount = 0;


        Tower newTower = null;
        if (towerType == 0) {
            moneyAmount = 100;
            newTower = new NormalTower(x,y,getResources(), this);
        }
        if (towerType == 1) {
            moneyAmount = 150;
            newTower = new FlameTower(x,y,getResources(), this);
        }
        if (towerType == 2) {
            moneyAmount = 50;
            newTower = new SpikeTower(x,y,getResources(), this);
        }

        if (moneyAmount > money) {
            touchHandler.notEnoughMoney();
            return;
        }
        else {
            money -= moneyAmount;
        }

        towers.add(newTower);

    }


    public void addMoney(int money) {
        this.money += money;
    }



    public void addBullet(double bulletType, double x, double y, double xVeloc, double yVeloc) {
        Bullet newBullet = null;
        if (bulletType == 0) {
            newBullet = new NormalBullet(x, y, xVeloc, yVeloc, getResources(), this) {
            };
        }
        if (bulletType == 1) {
            newBullet = new ExplosiveBullet(x, y, xVeloc, yVeloc, getResources(), this) {
            };
        }
        bullets.add(newBullet);

    }
    public void addExplosion(float x, float y) {

        Explosion explosion = new Explosion(x,y,this);

        explosions.add(explosion);

    }


    private void spawnZombie() {
        int spawnRangeY = new Random().nextInt(screenHeight - 200);
        int spawnRangeX = screenWidth + 200;

        Zombie newZombie = new NormalZombie(spawnRangeX,spawnRangeY,getResources(),1, this);

        zombies.add(newZombie);
    }


    public void update() {

        for (int i = 0; i < zombies.size(); i++) {
            zombies.get(i).update();
        }
        for (int i = 0; i < towers.size(); i++) {
            towers.get(i).update();
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
        }
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
        }


        destroyBullets();
        destroyZombies();
        destroyExplosions();

    }


    public Zombie getFirstZombie() {
        Zombie firstZombie = null;
        double closestX = screenWidth*2;
        for (Zombie z : zombies) {
            if (z.getX() < closestX) {
                closestX = z.getX();
                firstZombie = z;
            }
        }
        return firstZombie;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {



            canvas.drawColor(Color.GRAY);

            for (int i = 0; i < towers.size(); i++) {
                towers.get(i).draw(canvas);
            }

            for (int i = 0; i < zombies.size(); i++) {
                zombies.get(i).draw(canvas);
            }
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).draw(canvas);
            }
            for (int i = 0; i < explosions.size(); i++) {
                explosions.get(i).draw(canvas);
            }

            touchHandler.draw(canvas);



        }
    }





    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
}
