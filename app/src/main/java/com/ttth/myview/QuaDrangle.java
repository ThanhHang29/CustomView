package com.ttth.myview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.Random;

/**
 * Created by Thanh Hang on 16/01/17.
 */

public class QuaDrangle extends MyShape {
    private Point point1, point2, point3, point4;

    public QuaDrangle(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public QuaDrangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public QuaDrangle(Point point1, Point point2, Point point3, Point point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;

    }
    public void parallelogram(int width, int height, int scale, boolean left){
        point2.set(point1.x + width, point1.y);
        if (left == true){
            point3.set(point1.x + width - scale, point1.y + height);
            point4.set(point1.x - scale, point1.y +height);
        }else {
            point3.set(point1.x + width + scale, point1.y +height);
            point4.set(point1.x + scale, point1.y + height);
        }
    }
    public  void diamond(int height, int scale){
        point2.set(point1.x + scale, point1.y + height/2);
        point3.set(point1.x,point1.y + height);
        point4.set(point1.x - scale, point1.y + height/2);
    }
    public void retangle(int width, int height){
        point2.set(point1.x + width, point1.y);
        point3.set(point1.x + width, point1.y + height);
        point4.set(point1.x, point1.y +height);
    }
    public void square (int width){
        point2.set(point1.x + width, point1.y);
        point3.set(point1.x + width, point1.y + width);
        point4.set(point1.x, point1.y + width);
    }
    public void trapezium(int height, int scale, int widthTop, int widthBottom,boolean left){
        point2.set(point1.x + widthTop, point1.y);
        if (left == true){
            point3.set(point1.x + widthTop - scale, point1.y + height);
            point4.set(point1.x + widthTop - scale - widthBottom, point1.y +height);
        }else {
            point3.set(point1.x + widthTop + scale, point1.y + height);
            point4.set(point1.x + widthTop + scale - widthBottom, point1.y +height);
        }
    }
    public void caculOval(int scale1, int scale2){
        point2.set(point1.x + scale1, point1.y + scale2);
    }
    public void caculCircle(int scale){
        if (point1.x != point1.y){
            point1.set(point1.x, point1.x);
        }
        point2.set(point1.x + scale, point1.x +scale);

    }
    public void drawMyOval(Canvas canvas, Paint paint, int r, int g, int b){
        paint.setColor(Color.rgb(r, g, b));
        canvas.drawOval(point1.x, point1.y, point2.x, point2.y, paint);
    }
    public void drawTraingle(Canvas canvas, Paint paint, int r, int g, int b){
        paint.setColor(Color.rgb(r, g, b));
        Path path = new Path();
        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point1.x, point1.y);
        path.close();
        canvas.drawPath(path, paint);
    }

    @Override
    public void drawShape(Canvas canvas, Paint paint, int r, int g, int b) {
        super.drawShape(canvas, paint, r, g, b);
        Path path = new Path();
        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.lineTo(point1.x, point1.y);
        path.close();
        canvas.drawPath(path, paint);
    }

}
