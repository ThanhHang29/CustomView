package com.ttth.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ttth.example.R;

/**
 * Created by Thanh Hang on 20/01/17.
 */

public class ViewSquare extends View {
    private static final int WIDTH_DEFAULT = 200;
    private static final int HEIGHT_DEFAULT = 100;
    private static final int X_DEFAULT = 100;
    private static final int Y_DEFAULT = 100;


    private Paint mPaint;
    private Point mPoint;
    private Rect rect;
    private int width = 200, space = 50;
    private int mStatus = -1;

    public ViewSquare(Context context) {
        super(context);
        init(context, null);
        init();
    }

    public ViewSquare(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        init();
    }

    public ViewSquare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        init();
    }

    public ViewSquare(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
        init();
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ViewSquare,
                0, 0);
        try {
            final int width = a.getDimensionPixelSize(R.styleable.ViewSquare_mWidth, WIDTH_DEFAULT);
            final int height = a.getDimensionPixelSize(R.styleable.ViewSquare_mWidth, HEIGHT_DEFAULT);
            final int x = a.getDimensionPixelSize(R.styleable.ViewSquare_mX, X_DEFAULT);
            final int y = a.getDimensionPixelSize(R.styleable.ViewSquare_mY, Y_DEFAULT);
            rect = getRect(x, y, x + width, y + height);
        } finally {
            a.recycle();
        }
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPoint = new Point();
    }


    public Rect getRect(int l, int t, int r, int b) {
        return new Rect(l, t, r, b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                mPoint.set(x, y);
                mStatus = checkPoint(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mStatus == -1) return false;

                sizeChange((int) event.getX(), (int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


    void sizeChange(int x, int y) {
        switch (mStatus) {
            // top left
            case 0:
                rect.set(x , y, rect.right, rect.bottom);
                break;
            case 1:
                rect.set(rect.left, y ,  x, rect.bottom);
                break;
            case 2:
                rect.set(x, rect.top, rect.right, y);
                break;
            case 3:
                rect.set(rect.left, rect.top, x, y );

                break;
        }
        invalidate();
    }

    int checkPoint(int touchX, int touchY) {
        // top left
        if (Math.abs(touchX - mPoint.x) <= space && Math.abs(touchY - mPoint.y) <= space) {
            return 0;
        }
        // Top right
        if (Math.abs(touchX - (mPoint.x + rect.width())) <= space && Math.abs(touchY - mPoint.y) <= space) {
            return 1;
        }
        // bottom left
        if (Math.abs(touchX - mPoint.x) <= space && Math.abs(touchY - mPoint.y - rect.height()) <= space) {
            return 2;
        }
        // bottom right
        if (Math.abs(touchX - mPoint.x - rect.width()) <= space && Math.abs(touchY - mPoint.y - rect.height()) <= space) {
            return 3;
        }
        return -1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect, mPaint);
    }

}
