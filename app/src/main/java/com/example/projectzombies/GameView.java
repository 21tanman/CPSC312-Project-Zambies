package com.example.projectzombies;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainGameThread thread;

    private NormalZombie testZombie;

    public static final String TAG = "MYTAG";


    public static final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;


    private TouchHandler touchHandler;


    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainGameThread(getHolder(), this);
        setFocusable(true);

        testZombie = new NormalZombie(1080,400,getResources());


        touchHandler = new TouchHandler(getResources(),context);

        this.setOnTouchListener(touchHandler);


    }

    public void update() {
        testZombie.update();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.GRAY);

            /*
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            canvas.drawRect(100, 100, 400, 200, paint);
             */


            testZombie.draw(canvas);

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
