package com.example.projectzombies;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class TouchHandler implements View.OnTouchListener{

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    protected Bitmap regTower;
    protected Bitmap regBarTower;

    protected Bitmap flameTower;
    protected Bitmap flameBarTower;

    protected Bitmap spikeTower;
    protected Bitmap spikeBarTower;


    private final int regTowerPositionX = 0;
    private final int regTowerPositionY = 0;


    private final int flameTowerPositionX = 0;
    private final int flameTowerPositionY = 250;

    private final int spikeTowerPositionX = 0;
    private final int spikeTowerPositionY = 500;

    private final int MAX_WARNING_TIME  = 100;
    private int warningTime=-1;


    private GameView gameView;

    private boolean dragging = false;

    private int draggedTowerType = -1;


    private int draggableX;
    private int draggableY;


    private static final int BAR_TOWER_SIZE = 200;
    public static final int TOWER_SIZE = 150;

    Paint textPaint;

    Paint barColor;
    public TouchHandler(Resources resources, GameView gameView) {


        this.gameView = gameView;

        regBarTower = BitmapFactory.decodeResource(resources,R.drawable.tower0);
        regBarTower = Bitmap.createScaledBitmap(regBarTower,BAR_TOWER_SIZE,BAR_TOWER_SIZE,true);

        regTower = BitmapFactory.decodeResource(resources,R.drawable.tower0);
        regTower = Bitmap.createScaledBitmap(regTower,TOWER_SIZE,TOWER_SIZE,true);

        flameBarTower = BitmapFactory.decodeResource(resources,R.drawable.tower1);
        flameBarTower = Bitmap.createScaledBitmap(flameBarTower,BAR_TOWER_SIZE,BAR_TOWER_SIZE,true);

        flameTower = BitmapFactory.decodeResource(resources,R.drawable.tower1);
        flameTower = Bitmap.createScaledBitmap(flameTower,TOWER_SIZE,TOWER_SIZE,true);

        spikeBarTower = BitmapFactory.decodeResource(resources,R.drawable.tower2);
        spikeBarTower = Bitmap.createScaledBitmap(spikeBarTower,BAR_TOWER_SIZE,BAR_TOWER_SIZE,true);

        spikeTower = BitmapFactory.decodeResource(resources,R.drawable.tower2);
        spikeTower = Bitmap.createScaledBitmap(spikeTower,BAR_TOWER_SIZE,BAR_TOWER_SIZE,true);



        barColor = new Paint();
        barColor.setColor(Color.rgb(0, 0, 0));

        textPaint = new Paint();
        textPaint.setColor(Color.YELLOW);
        textPaint.setTextSize(75);

    }

    private void drawBar(Canvas canvas) {

        canvas.drawRect(0, 0, 210, screenHeight, barColor);

        canvas.drawBitmap(regBarTower, regTowerPositionX, regTowerPositionY, null);
        canvas.drawText("$100", regTowerPositionX, regTowerPositionY + 200, textPaint);


        canvas.drawBitmap(flameBarTower, flameTowerPositionX, flameTowerPositionY, null);
        canvas.drawText("$150", flameTowerPositionX, flameTowerPositionY + 200, textPaint);


        canvas.drawBitmap(spikeBarTower, spikeTowerPositionX, spikeTowerPositionY, null);
        canvas.drawText("$50", spikeTowerPositionX, spikeTowerPositionY + 200, textPaint);

        if (warningTime <= 0) {
            canvas.drawText("Money: $" + gameView.getMoney(), screenWidth*2/3,  100, textPaint);
        }
        else {
            canvas.drawText("Not enough money!", screenWidth*2/3,  100, textPaint);
        }


    }




    public void draw(Canvas canvas) {
        warningTime--;
        drawBar(canvas);
        drawDraggable(canvas);
    }

    public void notEnoughMoney() {
        warningTime = MAX_WARNING_TIME;
    }


    private boolean pointOnBar(int x, int y) {
        if (Collider.pointWithImage(x,y,regBarTower,regTowerPositionX,regTowerPositionY)) {
            draggedTowerType = 0;
            return true;
        }

        if (Collider.pointWithImage(x,y,flameBarTower,flameTowerPositionX,flameTowerPositionY)) {
            draggedTowerType = 1;
            return true;
        }
        if (Collider.pointWithImage(x,y,spikeBarTower,spikeTowerPositionX,spikeTowerPositionY)) {
            draggedTowerType = 2;
            return true;
        }


        return false;
    }

    private void drawDraggable(Canvas canvas) {

        if (draggedTowerType == 0) {
            canvas.drawBitmap(regTower, draggableX, draggableY, null);
        }

        if (draggedTowerType == 1) {
            canvas.drawBitmap(flameTower, draggableX, draggableY, null);
        }

        if (draggedTowerType == 2) {
            canvas.drawBitmap(spikeTower, draggableX, draggableY, null);
        }


    }

    private void updateDraggable(int x, int y) {
        int imageSize = 0;
        if (draggedTowerType == 0) {
            imageSize = regTower.getWidth();
        }
        if (draggedTowerType == 1) {
            imageSize = flameTower.getWidth();
        }
        if (draggedTowerType == 2) {
            imageSize = spikeTower.getWidth();
        }

        draggableX = x - imageSize/2;
        draggableY = y - imageSize/2;

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {

        view.performClick();

        int x = (int)event.getX();
        int y = (int)event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (pointOnBar(x,y)) {
                    dragging = true;
                    updateDraggable(x,y);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (dragging) {

                    updateDraggable(x,y);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (dragging) {
                    gameView.getTowerPlacement(draggedTowerType, draggableX, draggableY);
                    draggedTowerType = -1;
                    dragging = false;
                }
                break;
        }
        return false;
    }






}
