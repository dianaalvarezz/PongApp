package edu.niu.android.pongapp;
import android.graphics.Point;

public class BallModel
{
    public Object ballModel;
    private int deltaTime;
    private Point paddleCenter;
    private int paddleX;
    private int ballY;
    private int ballX;
    private int paddleY;
    private int paddleWidth;
    private int paddleHeight;
    private int ballVelocity;
    private int ballVelocityX;

    public BallModel(int screenWidth, int screenHeight)
    {
        paddleCenter = new Point(screenWidth / 2, screenHeight - 300);
        paddleWidth = screenWidth / 5;
        paddleHeight = screenHeight / 20;
        paddleX = (screenWidth / 2) - (paddleWidth / 2);
        paddleY = screenHeight - (paddleHeight * 2);
        ballY = screenHeight / 2;
        ballX = screenWidth / 2;
        ballVelocity = 5;
        ballVelocityX = 5;

    }

    public Point getPaddleCenter()
    {
        return paddleCenter;
    }

    public int paddleX()
    {
        return paddleX;
    }

    public int ballY()
    {
        return ballY;
    }

    public void updatePaddleX(int newX, int screenWidth)
    {
        paddleX = Math.max(0, Math.min(newX, screenWidth - paddleWidth));
    }

    public void incrementBallY(int screenHeight, int screenWidth)
    {
        ballY += ballVelocity;
        ballX += ballVelocityX;


        // Reverse direction if the ball hits the top or bottom
        if (ballY <= 0 || ballY >= screenHeight - 30)
        {
            ballVelocity = -ballVelocity;
        }

        if (ballY + 30 >= paddleY && ballX >= paddleX && ballX <= paddleX + paddleWidth)
        {
            ballVelocity = -ballVelocity;
        }

        if (ballY > screenHeight)
        {
            resetBall(screenHeight, screenWidth);
        }

        // Reverse direction if the ball hits the left or right wall
        if (ballX <= 0 || ballX >= screenWidth - 30)
        {
            ballVelocityX = -ballVelocityX;
        }

    }


    public void resetBall(int screenHeight, int screenWidth)
    {
        ballY = screenHeight / 2;
        ballX = screenWidth / 2;
    }

    public boolean inBounds(int screenWidth, int newX)
    {
        return paddleX >= 0 && paddleX + paddleWidth <= screenWidth;
    }


    public int paddleY()
    {
        return paddleY;
    }

    public int paddleWidth()
    {
        return paddleWidth;
    }

    public int paddleHeight()
    {
        return paddleHeight;
    }

    public int ballX()
    {
        return ballX;
    }
}
