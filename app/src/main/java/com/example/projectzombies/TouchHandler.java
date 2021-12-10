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

    private Context context;

    Paint barColor;
    public TouchHandler(Resources resources, Context context) {



        regTower = BitmapFactory.decodeResource(resources,R.drawable.tower0);
        regTower = Bitmap.createScaledBitmap(regTower,200,200,true);



        barColor = new Paint();
        barColor.setColor(Color.rgb(0, 0, 0));
    }

    private void drawBar(Canvas canvas) {

        canvas.drawRect(0, 0, 210, screenHeight, barColor);

        canvas.drawBitmap(regTower, 0, 0, null);

    }


    public void draw(Canvas canvas) {

        drawBar(canvas);
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        Log.d(GameView.TAG,"event action: " + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(GameView.TAG, "touched down");
                break;
            case MotionEvent.ACTION_MOVE:
                Toast.makeText(context, "Moving..",Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_UP:
                Toast.makeText(context, "TouchUp",Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }



}
