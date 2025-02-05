package edu.niu.android.pongapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View
{
    public static int DELTA_TIME = 100;
    private Paint paint;
    private BallModel ballModel;

    public GameView(Context context, int width, int height)
    {
        super(context);

        // Set background color
        setBackgroundColor(android.graphics.Color.DKGRAY);

        // Initialize paint (ensure contrast)
        paint = new Paint();
        paint.setColor(android.graphics.Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        // Initialize the ball model (paddle position)
        ballModel = new BallModel(width, height);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        // Draw the ball at its position
        canvas.drawCircle(
                ballModel.getPaddleCenter().x,
                ballModel.ballY(),
                30,
                paint
        );

        canvas.drawRect(
                ballModel.paddleX(),
                ballModel.paddleY(),
                ballModel.paddleX() + ballModel.paddleWidth(),
                ballModel.paddleY() + ballModel.paddleHeight(),
                paint
        );

        ballModel.incrementBallY(getHeight());

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        ballModel.updatePaddleX((int) event.getX(), getWidth());
        invalidate();
        return true;
    }

}
