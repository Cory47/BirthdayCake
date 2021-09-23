package cs301.birthdaycake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Balloon {
    private float cx;
    private float cy;
    private float radius;
    private Paint balloonColor;
    private Paint linePaint;

    public Balloon(float x, float y){
        cx = x;
        cy = y;
        radius = 50;
        balloonColor = new Paint();
        linePaint = new Paint();
        balloonColor.setColor(Color.MAGENTA);
        linePaint.setColor(Color.BLACK);
    }

    public void draw(Canvas canvas){
        canvas.drawOval(cx-radius,cy-radius,cx+radius,cy+2*radius, balloonColor);
        canvas.drawLine(cx,cy+2*radius, cx, cy+5*radius, linePaint);
    }

    public void setXY(float x, float y){
        cx = x;
        cy = y;
    }

}
