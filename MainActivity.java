/************************************************************************
 * CSCI 428                Pong App                        Spring 2025  *
 *                                                                      *
 *       App Name: Pong App                                             *
 *                                                                      *
 *     Class Name: MainActivity.java                                    *
 *                                                                      *
 *   Developer(s): Diana Alvarez                                        *
 *                                                                      *
 *       Due Date: 2/7/2024                                             *
 *                                                                      *
 *        Purpose: App that simulates the game of pong; user can        *
 *        move a paddle at the bottom of the screen and moves the ball  *
 *                                                                      *
 ************************************************************************/

package edu.niu.android.pongapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.os.Handler;

public class MainActivity extends AppCompatActivity
{
    private GameView gameView; // Declares GameView which draws game
    private BallModel ballModel; // Declares BallModel which controls movement
    private Handler loop = new Handler(); //Game loop
    private final int FRAME_RATE = 16; // USed to update screen

    // Function that sets up the Game
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Gets the width and height of the specific screen
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;


        ballModel = new BallModel(screenWidth, screenHeight);
        gameView = new GameView(this, ballModel);

        setContentView(gameView);

        // Start game loop for ball movement
        loop.postDelayed(gameLoop, FRAME_RATE);
    }

    // Used as a while loop
    private Runnable gameLoop = new Runnable()
    {
        @Override
        public void run()
        {
            ballModel.updateBall(); //updates where ball moves
            gameView.updateView();
            loop.postDelayed(this, FRAME_RATE); //creates loop
        }
    };

    // Detects when the user touches the paddle
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // detects where the screen in touched and moves paddle horizontally
        ballModel.updatePaddleX((int) event.getX());
        return true;
    }
}
