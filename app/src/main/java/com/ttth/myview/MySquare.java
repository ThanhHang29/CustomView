package com.ttth.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Thanh Hang on 17/01/17.
 */

public class MySquare extends View {
    private static final float NUMBER_SCALE = 50;
    private Paint mPaint, circlePaint;
    private int xLeft,yTop, xRight,yBottom,  radius;
    private float xLeftChange, yTopChange, xRightChange, yBottomChange, xChange, yChange, raidusChange;
    private float startX, startY;
    private Rect rect = getRect();
    private int x,y;
    private int width = 200;


    public MySquare(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
//        initShape();
        x = 300; y = 300;
        xLeft = x; yTop = y; xRight = x + width; yBottom = y + width;
        rect = getRect();

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(10);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(Color.RED);
        initCircle();
    }

    private void initCircle() {
        x = 500; y = 700; radius = 200;
        xChange = x; yChange = y; raidusChange = radius;
    }


    public Rect getRect() {
//        xLeft = x; yTop = y; xRight = x + width; yBottom = y + width;
        return new Rect(xLeft, yTop, xRight, yBottom);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    float getYBottom(){
        return yBottom = yTop + xRight - xLeft;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                int check = checkPoint(startX, startY);
                if (check == 1 || check == 2 || check == 3 || check == 4){
                    Log.e("DOWN", "ggg");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float dx, dXC, dYC, dRadiusM;
                float xMove = event.getX();
                float yMove = event.getY();
                dx = xMove - startX;
                sizeChange(dx,startX, startY,xMove, yMove);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    void sizeChange(float dx, float touchX, float touchY, float xMove, float yMove){
        int check = checkPoint(touchX, touchY);
        switch (check){
            case 4:
                width = width + (int)dx;
                yBottom = x + width;
                xRight = x + width;
                break;
            case 3:
                xLeft = (int)xMove;
                yBottom = (int)touchY - (int)dx ;
                break;
            case 2:
                xRight = xRight +(int)dx;
                yTop = yTop - (int)dx;
                break;
            case 1:
                xLeft = (int) xMove;
                yTop = (int)xMove;

                break;
        }
        rect.set(xLeft, yTop,xRight, yBottom);
        invalidate();
    }
//    float getRadiusTouch(float dx, float dy){
//        return (float) Math.sqrt(dx*dx + dy*dy);
//    }
//
//    boolean checkCircle(float xTouch,float yTouch){
//        float dx = Math.abs(xChange - xTouch);
//        float dy = Math.abs(yChange - yTouch);
//        float radiusTouch = getRadiusTouch(dx, dy);
//        float dRadius = Math.abs(raidusChange - radiusTouch);
//        if (dRadius <= NUMBER_SCALE){
//            return true;
//        }
//        return false;
//    }

    int checkPoint(float touchX, float touchY ){
        float dYTop = Math.abs(yTop - touchY);
        float dYBottom = Math.abs(yBottom - touchY);
        float dXLeft = Math.abs(xLeft - touchX);
        float dXRight = Math.abs(xRight - touchX);
        if (dYTop <= NUMBER_SCALE ){
            if (dXLeft <= NUMBER_SCALE){
                return 1;
            }
            else if (dXRight <= NUMBER_SCALE){
                return 2;
            }
        }else if (dYBottom <= NUMBER_SCALE){
            if (dXLeft <= NUMBER_SCALE){
                return 3;
            }else if (dXRight <= NUMBER_SCALE){
                return 4;
            }
        }
        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        canvas.drawRect(xLeftChange, yTopChange, xRightChange, yBottomChange, mPaint);
//        canvas.drawCircle(xChange, yChange, raidusChange, circlePaint);

        canvas.drawRect(rect, mPaint);
        canvas.restore();
    }

}

