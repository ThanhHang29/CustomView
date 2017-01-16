package com.ttth.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Thanh Hang on 16/01/17.
 */

public class MyView extends View {
    private Paint mPaint;
    private QuaDrangle quaDrangle, parallelogram, retangle,
            square, diamond, traingle, trapezium, oval, circle;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

        initQuaDrangle();
        initParallelogram();
        initRetangle();
        initSquare();
        initDiamond();
        initTraingle();
        initTrapezium();
        initCircle();
        initOval();

    }

    private void initOval() {
        Point point = new Point(50, 350);
        Point point1 = new Point();
        oval = new QuaDrangle(point, point1);
        oval.caculOval(50, 90);
    }
// hình tròn
    private void initCircle() {
        Point point = new Point(250, 100);
        Point point1 = new Point();
        circle = new QuaDrangle(point, point1);
        circle.caculCircle(80);
    }
// hình thang
    private void initTrapezium() {
        Point point = new Point(250, 100);
        Point point1 = new Point();
        Point point2 = new Point();
        Point point3 = new Point();
        trapezium = new QuaDrangle(point, point1, point2, point3);
        trapezium.trapezium(200,70,90,120, true);
    }
//tam giác
    private void initTraingle() {
        Point point = new Point(100, 500);
        Point point1 = new Point(40, 100);
        Point point2 = new Point(140, 200);
        traingle = new QuaDrangle(point, point1, point2);

    }
// hình thoi
    private void initDiamond() {
        Point point = new Point(100, 500);
        Point point1 = new Point();
        Point point2 = new Point();
        Point point3 = new Point();
        diamond = new QuaDrangle(point, point1, point2, point3);
        diamond.diamond(200, 50);
    }

    private void initSquare() {
        Point point = new Point(300, 500);
        Point point1 = new Point();
        Point point2 = new Point();
        Point point3 = new Point();
        square = new QuaDrangle(point, point1, point2, point3);
        square.square(150);
    }

    private void initRetangle() {
        Point point = new Point(300, 300);
        Point point1 = new Point();
        Point point2 = new Point();
        Point point3 = new Point();
        retangle = new QuaDrangle(point, point1, point2, point3);
        retangle.retangle(200, 100);
    }
// hình bình hành
    private void initParallelogram() {
        Point point = new Point(100, 100);
        Point point1 = new Point();
        Point point2 = new Point();
        Point point3 = new Point();
        parallelogram = new QuaDrangle(point, point1, point2, point3);
        parallelogram.parallelogram(250, 300, 70, true);
    }
// tứ giác
    private void initQuaDrangle() {
        Point point1 = new Point(100, 50);
        Point point2 = new Point(120, 100);
        Point point3 = new Point(70, 120);
        Point point4 = new Point(20, 80);
        quaDrangle = new QuaDrangle(point1,point2,point3,point4);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        quaDrangle.drawShape(canvas, mPaint,150, 70, 60);
        parallelogram.drawShape(canvas, mPaint,200, 230, 70);
        retangle.drawShape(canvas, mPaint,30, 60, 100);
        square.drawShape(canvas, mPaint, 250, 30, 190);
        diamond.drawShape(canvas, mPaint, 190, 60, 190);
        traingle.drawTraingle(canvas, mPaint, 250, 50, 100);
        trapezium.drawShape(canvas, mPaint, 240, 250, 180);
        circle.drawMyOval(canvas, mPaint, 150, 60, 190);
        oval.drawMyOval(canvas, mPaint, 190, 80, 200);

    }
}
