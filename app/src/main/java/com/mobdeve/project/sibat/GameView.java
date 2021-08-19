package com.mobdeve.project.sibat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {

    private Player player;
    private Handler  handler;
    private Runnable r;


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

    }

    private void initObstacle() {

    }

    private void initPlayer() {

        player = new Player();

        //Set player size
        player.setWidth(100*Constants.SCREEN_WIDTH/1080);
        player.setHeight(100*Constants.SCREEN_HEIGHT/1920);

        //Set initial positions of player
        player.setX(100*Constants.SCREEN_WIDTH/1080);
        player.setY(Constants.SCREEN_HEIGHT/2-player.getHeight()/2);

        //ADD PLAYER ANIMATIONS HERE
        ArrayList<Bitmap> animations = new ArrayList<>();
        animations.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.tempchar));
        animations.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.tempchar2));
        player.setArrBms(animations);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        player.draw(canvas);
        handler.postDelayed(r, 10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPos = event.getX();
        float yPos = event.getY();


        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                //Add logic to unpause/start the game if finger is on player
                return true;


            case MotionEvent.ACTION_MOVE:
                //Add logic to move player to finger position. Change player x, y to finger's x y
                player.setX(xPos);
                player.setY(yPos);
                break;

            case MotionEvent.ACTION_UP:
                //Add logic to pause the game
                break;

            default:
                return false;
        }


        return super.onTouchEvent(event);
    }
}
