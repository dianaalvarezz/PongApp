/************************************************************************
 *                                                                      *
 *     Class Name: BallModel.java                                       *
 *                                                                      *
 *        Purpose: Class that manages the logic of the game such        *
 *        as ball movement and paddle movement                          *
 ************************************************************************/
package edu.niu.android.pongapp;

import android.graphics.Point;

public class BallModel
{
    private int paddleX; // Stores paddle top left x
    private int paddleY; // Stores paddle top left y
    private int ballY; // Stores balls y
    private int ballX; // Stores balls x
    private int paddleWidth; // Controls paddle width
    private int paddleHeight; // Controls paddle height
    private int ballYspeed; // Controls balls vertical speed
    private int ballXspeed; // Controls balls horizontal speed
    private int screenWidth; // Screen width
    private int screenHeight; // Screens Height

    public BallModel(int screenWidth, int screenHeight)
    {
        // Gets screen width and height
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;


        paddleWidth = screenWidth / 5; // Makes paddle 1/5 size of screen width
        paddleHeight = screenHeight / 20; // Makes paddle 1/20 of screen height
        paddleX = (screenWidth / 2) - (paddleWidth / 2); // Centers paddle in screen
        paddleY = screenHeight - (paddleHeight * 2); // Makes sure paddle isnt fully at bottom

        // Places ball in the middle of the screen to start
        ballY = screenHeight / 2;
        ballX = screenWidth / 2;

        // Sets ball to move 5 pixels per frame
        ballYspeed = 5;
        ballXspeed = 5;
    }

    // Getters for access
    public int getPaddleX() { return paddleX; }
    public int getPaddleY() { return paddleY; }
    public int getPaddleWidth() { return paddleWidth; }
    public int getPaddleHeight() { return paddleHeight; }
    public int getBallX() { return ballX; }
    public int getBallY() { return ballY; }


    // Makes sure paddle stays on screen
    public void updatePaddleX(int newX)
    {
        // Prevents paddle moving off screen
        paddleX = Math.max(0, Math.min(newX, screenWidth - paddleWidth));
    }

    // Updated ball position
    public void updateBall()
    {
        // increments the speed of the ball up and down and side ways
        ballY += ballYspeed;
        ballX += ballXspeed;

        // Reverse if ball hits the top so can look like bouncing down without going out of orders
        if (ballY <= 0)
        {
            // reverses the y direction
            ballYspeed = -ballYspeed;
        }

        // code that handles ball and paddle collision
        if (ballY + 30 >= paddleY && ballX >= paddleX && ballX <= paddleX + paddleWidth)
        {
            // REverses y to look like bounced off
            ballYspeed = -ballYspeed;
            int impactPoint = ballX - (paddleX + paddleWidth / 2);

            // Changes speed based on paddle impact
            ballXspeed+= (impactPoint / 10);

            // Added because my ball wasnt bouncing properly
            ballY = paddleY - 35;

            // Controls the speed
            ballXspeed= Math.max(-10, Math.min(ballXspeed, 10));
        }

        // Reverse if ball hits walls
        if (ballX <= 0 || ballX >= screenWidth - 30)
        {
            ballXspeed = -ballXspeed;
        }

        // sets ball back in middle
        if (ballY > screenHeight)
        {
            resetBall();
        }
    }

    // Resets ball position when ball touches the bottom of the screen
    public void resetBall()
    {
        // Moves ball back to center when ball touches the bottom of the screen
        ballY = screenHeight / 2;
        ballX = screenWidth / 2;

        // Resets ball speed
        ballYspeed = 5;

        // Randomizes movement so ball doesnt just go up and down
        ballXspeed = (Math.random() > 0.5) ? 5 : -5;
    }
}
