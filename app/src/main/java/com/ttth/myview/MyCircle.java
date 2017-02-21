package com.ttth.myview;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Thanh Hang on 17/01/17.
 */

public class MyCircle extends View {
    private Paint mPaint;
    public MyCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCircle();
    }

    private void initCircle() {
        mPaint = new Paint();

    }
}
