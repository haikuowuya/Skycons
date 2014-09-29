package com.thbs.skycons.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by administrator on 29/09/14.
 */
public class CloudSunView extends View {

    private static Paint paint;
    private int screenW, screenH;
    private float X,Y,XSun,YSun,X2,Y2;
    private Path path, path1, path2;
    private double count;
    int degrees;
    float startAngle;
    float sweepAngle;

    public CloudSunView(Context context) {
        super(context);
        init();
    }

    public CloudSunView(Context context, AttributeSet attrs) {
        super(context, attrs);

        String num[] = attrs.getAttributeValue(0).split(".dip");
        System.out.println(num[0]);

        // screenW = Integer.valueOf(attrs.getAttributeValue(0).split("dp""));
        //screenH = Integer.valueOf(attrs.getAttributeValue(1));

        X = screenW/2;
        Y = (screenH/2);

        // path.moveTo(X, Y);

        init();
    }

    public CloudSunView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        //screenW = Integer.valueOf(attrs.getAttributeValue(0));
        //screenH = Integer.valueOf(attrs.getAttributeValue(1));

        X = screenW/2;
        Y = (screenH/2);

        //path.moveTo(X, Y);

        init();
    }

    private void init() {

        degrees = 0;
        count = 0;
        startAngle = 45;
        sweepAngle = 165;
        paint = new Paint();


        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(0, 0, 0, Color.BLACK);

    }

    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenW = w;
        screenH = h;
        X = screenW/2;
        Y = (screenH/2);
        XSun= X+70;
        YSun= Y-100;



    }



    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        path = new Path();
        path1 = new Path();
        path2 = new Path();

        count = count+0.5;
        System.out.println("count : "+count);
        int retval = Double.compare(count, 360.00);
        if(retval > 0) {

        }
        else if(retval < 0) {

        }
        else {
            count = 0;
        }



        path.addCircle(XSun,YSun,30, Path.Direction.CW);

        for(int i=0;i<360;i+=45){
            path1.moveTo(XSun,YSun);
            float X1 = (float)(50 * Math.cos(Math.toRadians(i+count)) + XSun);
            float Y1 = (float)(50 * Math.sin(Math.toRadians(i+count))+YSun);
            float X2 = (float)(65 * Math.cos(Math.toRadians(i+count))+XSun);
            float Y2 = (float)(65 * Math.sin(Math.toRadians(i+count))+YSun);
            path1.moveTo(X1,Y1);
            path1.lineTo(X2,Y2);

        }


        float X1 = (float)(90 * Math.cos(Math.toRadians(0+(0.222*count))) + X);
        float Y1 = ((float)(50 * Math.sin(Math.toRadians(0+(0.222*count))) + Y));
        float P1X = (float)(90 * Math.cos(Math.toRadians(80+(0.111*count))) + X);
        float P1Y = ((float)(50 * Math.sin(Math.toRadians(80+(0.111*count))) + Y));
        float P2X = (float)(90 * Math.cos(Math.toRadians(120+(0.222*count))) + X);
        float P2Y = ((float)((50+(0.111*count)) * Math.sin(Math.toRadians(120+(0.222*count))) + Y));
        float P3X = (float)(90 * Math.cos(Math.toRadians(200+(0.222*count))) + X);
        float P3Y = ((float)(90 * Math.sin(Math.toRadians(200+(0.222*count))) + Y));
        float P4X =(float)(90 * Math.cos(Math.toRadians(280+(0.222*count))) + X);
        float P4Y = ((float)(90 * Math.sin(Math.toRadians(280+(0.222*count))) + Y));


        //canvas.drawCircle(X,Y,120, paint1);


        path2.moveTo(X1,Y1);
        float XQ1 = ((3*X1)+X2)/4;
        //float YQ1 = ((3*Y1)+Y2)/4;
        PointF P1c1 = calculateTriangle(X1, Y1, P1X, P1Y, true, count);
        PointF P1c2 = calculateTriangle(X1, Y1, P1X, P1Y, false, count);
        PointF P2c1 = calculateTriangle(P1X, P1Y, P2X, P2Y, true, count);
        PointF P2c2 = calculateTriangle(P1X, P1Y, P2X, P2Y, false, count);
        PointF P3c1 = calculateTriangle(P2X, P2Y, P3X, P3Y, true, count);
        PointF P3c2 = calculateTriangle(P2X, P2Y, P3X, P3Y, false, count);
        PointF P4c1 = calculateTriangle(P3X, P3Y, P4X, P4Y, true, count);
        PointF P4c2 = calculateTriangle(P3X, P3Y, P4X, P4Y, false, count);
        PointF P5c1 = calculateTriangle(P4X, P4Y, X1, Y1, true, count);
        PointF P5c2 = calculateTriangle(P4X, P4Y, X1,Y1, false, count);
        float YQ1 = (float)Math.cos(XQ1);
        float XQ2 = ((3*X2)+X1)/4;
        //float YQ2 = ((3*Y2)+Y1)/4;
        float YQ2 = (float) Math.cos(XQ2);
        path2.moveTo(X1,Y1);
        path2.cubicTo(P1c1.x,P1c1.y,P1c2.x,P1c2.y,P1X,P1Y);
        path2.cubicTo(P2c1.x,P2c1.y,P2c2.x,P2c2.y,P2X,P2Y);
        path2.cubicTo(P3c1.x,P3c1.y,P3c2.x,P3c2.y,P3X,P3Y);
        path2.cubicTo(P4c1.x,P4c1.y,P4c2.x,P4c2.y,P4X,P4Y);
        path2.cubicTo(P5c1.x,P5c1.y,P5c2.x,P5c2.y,X1,Y1);


        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, paint);
        canvas.drawPath(path1, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawPath(path2, paint);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawPath(path2, paint);


        invalidate();


    }

    private PointF calculateTriangle(float x1, float y1, float x2, float y2, boolean left, double count) {
        PointF result = new PointF(0,0);
        float dy = y2 - y1;
        float dx = x2 - x1;
        float dangle = (float) ((Math.atan2(dy, dx) - Math.PI /2f));
        float sideDist = (float)0.5 * (float) Math.sqrt(dx * dx + dy * dy); //square
        if (left){
            result.x = (int) (Math.cos(dangle) * sideDist + x1);
            result.y = (int) (Math.sin(dangle) * sideDist + y1);
        }else{
            result.x = (int) (Math.cos(dangle) * sideDist + x2);
            result.y = (int) (Math.sin(dangle) * sideDist + y2);
        }
        return result;
    }

}
