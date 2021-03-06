package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();

    //Paint for the Text Coordinates
    Paint coordPaint = new Paint();
    Paint checkerPaint = new Paint();
    Paint checkerPaint2 = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 60.0f;
    public static final float wickHeight = 25.0f;
    public static final float wickWidth = 9.0f;
    public static final float outerFlameRadius = 45.0f;
    public static final float innerFlameRadius = 20.0f;
    private CakeModel cake;



    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(Color.BLUE);  //New blue color //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);

        checkerPaint.setColor(Color.RED); //set color to red
        checkerPaint.setStyle(Paint.Style.FILL);
        checkerPaint2.setColor(Color.GREEN); //set color to green
        checkerPaint2.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);  //better than black default
        cake = new CakeModel();

        //set Coordinates and Paint
        coordPaint.setTextSize(50);
        coordPaint.setColor(Color.RED);
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        if (cake.hasCandles) {
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);
            if (cake.candlesLit) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }
            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);

        }
    }
    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now two candles in the center
        for (int i = 1; i < cake.numCandles + 1; i++ ) {
            drawCandle(canvas, cakeLeft + (cakeWidth * i) / (cake.numCandles + 1) - (candleWidth * i) / 2, cakeTop);
        }
        if(cake.drawBalloon){
            cake.myBalloon.draw(canvas);
        }

        //Draw the coordinate touched on the surface view
        if (cake.xcoord != 0 && cake.ycoord != 0) {
            canvas.drawText("(" + cake.xcoord + ", " + cake.ycoord + ")", 1330, 500, coordPaint);
            canvas.drawRect(cake.xcoord,cake.ycoord,cake.xcoord + 30,cake.ycoord + 30,checkerPaint2);
            canvas.drawRect(cake.xcoord,cake.ycoord - 30,30 + cake.xcoord,cake.ycoord,checkerPaint);
            canvas.drawRect(cake.xcoord - 30,cake.ycoord - 30,cake.xcoord,cake.ycoord,checkerPaint2);
            canvas.drawRect(cake.xcoord - 30,cake.ycoord,cake.xcoord,cake.ycoord + 30,checkerPaint);

        }


        //drawCandle(canvas, cakeLeft + cakeWidth/3 - candleWidth/3, cakeTop);
        //drawCandle(canvas, cakeLeft + cakeWidth*2/3 - candleWidth*2/3, cakeTop);

    }//onDraw

    public CakeModel getCakeModel(){
        return this.cake;
    }
}//class CakeView


