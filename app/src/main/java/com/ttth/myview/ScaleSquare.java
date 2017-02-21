package com.ttth.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Thanh Hang on 19/01/17.
 */

public class ScaleSquare extends View {
    private static final int SCALE_TOUCH = 50;
    private Paint mPaint;
    private Rect rect;
    private Point point;
    private int widthSquare = 200;
    private int startX, startY , lastX, lastY;
    public ScaleSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        point = new Point(400, 400);
        rect = getRect();

    }

    public Rect getRect() {
        int left = point.x, top = point.y, right = point.x + widthSquare, bottom = point.y + widthSquare;
        return new Rect(left, top, right, bottom);
    }
    int checkTouchRect(float touchX, float touchY){
        Point pointTouch = new Point((int)touchX, (int)touchY);
        float dxLeft = Math.abs(pointTouch.x - point.x);
        float dyTop = Math.abs(pointTouch.y- point.y);
        float dxRight = Math.abs(pointTouch.x - point.x - widthSquare);
        float dyBottom = Math.abs(pointTouch.y - point.y - widthSquare);
        if (dxLeft <= SCALE_TOUCH){
            if (dyTop < SCALE_TOUCH){
                return 1;
            }else if (dyBottom <= SCALE_TOUCH){
                return 2;
            }
        }else if (dxRight <= SCALE_TOUCH){
            if (dyTop < SCALE_TOUCH){
                return 3;
            }else if (dyBottom <= SCALE_TOUCH){
                return 4;
            }
        }
        return 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                float dX =  moveX - startX;
                float dy = moveY - startY;
                sizeChange((int)dX, (int)dy, startX, startY);
//                int check = checkTouchRect(startX, startY);
//                if (check==1 || check ==2 || check == 3 || check == 4){
//                    Log.e("DOWN"," --------");
//                    invalidate();
//                }
//                getRect();
                break;
        }
        return true;
    }
    void sizeChange(int dx, int dy,float touchX, float touchY){
        int check = checkTouchRect(touchX, touchY);
        switch (check){
            case 1:
                point.set(point.x - dx, point.y - dy);
                break;
            case 2:
                point.set(point.x - dx, point.y + dy);
                break;
            case 3:
                point.set(point.x + dx, point.y - dy);
                break;
            case 4:
                point.set(point.x + dx, point.y + dy);
                break;
        }
        getRect();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawRect(rect, mPaint);
        canvas.restore();
    }
}
