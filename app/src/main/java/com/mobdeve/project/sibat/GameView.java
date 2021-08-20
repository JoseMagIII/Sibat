package com.mobdeve.project.sibat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    private Player player;
    private Handler  handler;
    private Runnable r;
    private int numObstacles;
    private ArrayList<Obstacle> arrObstacle;
    private boolean Pause;
    private TextView pauseScreen;

    float positions[]; //Array of possible positions

    Random rand = new Random(); //Random variable to generate a random number from 0 - 3

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        initPlayer();
        initObstacle();
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        Pause = true;

    }

    private void initObstacle() {
        numObstacles = 5;
        arrObstacle = new ArrayList<>();

        //Initialize position array
        positions = new float[4];
        positions[0] = 0;
        positions[1] = 275;
        positions[2] = 550;
        positions[3] = 825;



        for(int i = 1; i < numObstacles+1; i++)
        {
            arrObstacle.add(new Obstacle(positions[rand.nextInt(4)], i*-500, Constants.SCREEN_WIDTH/4, Constants.SCREEN_WIDTH/4));
            arrObstacle.get(i-1).setBm(BitmapFactory.decodeResource(this.getResources(), R.drawable.tempobstacle));
        }

    }

    private void initPlayer() {

        player = new Player();

        //Set player size
        player.setWidth(100*Constants.SCREEN_WIDTH/1080);
        player.setHeight(100*Constants.SCREEN_HEIGHT/1920);

        //Set initial positions of player
        player.setX(Constants.SCREEN_WIDTH/2-100);
        player.setY(1600*Constants.SCREEN_HEIGHT/1920-player.getHeight()/2);

        //ADD PLAYER ANIMATIONS HERE
        ArrayList<Bitmap> animations = new ArrayList<>();
        animations.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.tempchar));
        animations.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.tempchar2));
        player.setArrBms(animations);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        player.draw(canvas, Pause);

        for(int i = 1; i < numObstacles+1; i++)
        {
            //Reset obstacle position
            if(arrObstacle.get(i-1).getY() > Constants.SCREEN_HEIGHT) {
                arrObstacle.get(i-1).setX(positions[rand.nextInt(4)]);
                arrObstacle.get(i-1).setY(-500);
            }
            arrObstacle.get(i-1).draw(canvas, Pause);
        }


        handler.postDelayed(r, 10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPos = event.getX();
        float yPos = event.getY();


        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                //Add logic to unpause/start the game if finger is on player
                if(player.getRect().contains((int)xPos, (int)yPos)) {

                    Pause = false;
                    Constants.PAUSEVIEW.setVisibility(View.GONE);
                }

                return true;

            case MotionEvent.ACTION_MOVE:
                //Add logic to move player to finger position. Change player x, y to finger's x y
                if(!Pause) {
                    player.setX(xPos);
                    player.setY(yPos);
                }
                break;

            case MotionEvent.ACTION_UP:
                //Add logic to pause the game
                Pause = true;
                //Show pause screen
                Constants.PAUSEVIEW.setVisibility(View.VISIBLE);
                break;

            default:
                return false;
        }


       /* if(!Pause)
            Constants.PAUSEVIEW.setVisibility(View.GONE);

        else
            Constants.PAUSEVIEW.setVisibility(View.VISIBLE);*/

        return super.onTouchEvent(event);
    }
}
