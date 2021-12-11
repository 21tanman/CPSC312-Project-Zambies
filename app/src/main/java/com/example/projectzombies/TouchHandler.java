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


    private final int regTowerPositionX = 0;
    private final int regTowerPositionY= 0;

    private GameView gameView;

    private boolean dragging = false;

    private int draggedTowerType = -1;


    private int draggableX;
    private int draggableY;


    private final int BAR_TOWER_SIZE = 200;
    public static final int TOWER_SIZE = 150;


    Paint barColor;
    public TouchHandler(Resources resources, GameView gameView) {


        this.gameView = gameView;

        regBarTower = BitmapFactory.decodeResource(resources,R.drawable.tower0);
        regBarTower = Bitmap.createScaledBitmap(regBarTower,BAR_TOWER_SIZE,BAR_TOWER_SIZE,true);

        regTower = BitmapFactory.decodeResource(resources,R.drawable.tower0);
        regTower = Bitmap.createScaledBitmap(regTower,TOWER_SIZE,TOWER_SIZE,true);


        barColor = new Paint();
        barColor.setColor(Color.rgb(0, 0, 0));
    }

    private void drawBar(Canvas canvas) {

        canvas.drawRect(0, 0, 210, screenHeight, barColor);

        canvas.drawBitmap(regBarTower, regTowerPositionX, regTowerPositionY, null);

    }


    public void draw(Canvas canvas) {

        drawBar(canvas);

        drawDraggable(canvas);
    }


    private boolean pointOnBar(int x, int y) {
        if (Collider.pointWithImage(x,y,regBarTower,regTowerPositionX,regTowerPositionY)) {
            draggedTowerType = 0;
            return true;
        }


        return false;
    }

    private void drawDraggable(Canvas canvas) {

        if (draggedTowerType == 0) {
            canvas.drawBitmap(regTower, draggableX, draggableY, null);
        }


    }

    private void updateDraggable(int x, int y) {
        int imageSize = 0;
        if (draggedTowerType == 0) {
            imageSize = regTower.getWidth();
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
