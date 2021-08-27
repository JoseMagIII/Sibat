package com.mobdeve.project.sibat;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
    private Handler  handler, handler2;
    private Runnable r, scoreincrement;
    private int numObstacles;
    private ArrayList<Obstacle> arrObstacle;
    private boolean Pause, Gameover;
    public static int score;
    private int speedCap1, speedCap2, speedCap3, speedCapMax;

    float positions[]; //Array of possible positions


    Random rand = new Random(); //Random variable to generate a random number from 0 - 3

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        initPlayer();
        initObstacle();
        handler = new Handler();
        handler2 = new Handler();

        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        scoreincrement = new Runnable() {
            @Override
            public void run() {
                GameView.score += 1000;
                Constants.SCOREVIEW.setText(String.valueOf(score));
            }
        };

        Pause = true;
        Gameover = false;


        speedCap1 = 5000;
        speedCap2 = 10000;
        speedCap3 = 20000;
        speedCapMax = 50000;

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
        if(!Gameover) {
            player.draw(canvas, Pause);

            for (int i = 1; i < numObstacles + 1; i++) {
                if (player.getRect().intersect(arrObstacle.get(i - 1).getRect()))
                    Gameover = true;
                //Reset obstacle position
                if (arrObstacle.get(i - 1).getY() > Constants.SCREEN_HEIGHT) {
                    arrObstacle.get(i - 1).setX(positions[rand.nextInt(4)]);
                    arrObstacle.get(i - 1).setY(-500);
                    handler2.postDelayed(scoreincrement, 100);
                }

                if (score == speedCap1) {
                    arrObstacle.get(i - 1).speedUp();
                    speedCap1 = 0;
                }

                if (score == speedCap2) {
                    arrObstacle.get(i - 1).speedUp();
                    speedCap2 = 0;
                }

                if (score == speedCap3) {
                    arrObstacle.get(i - 1).speedUp();
                    speedCap3 = 0;
                }

                if (score == speedCapMax) {
                    arrObstacle.get(i - 1).speedUp();
                    speedCapMax = 0;
                }

                arrObstacle.get(i - 1).draw(canvas, Pause);
            }

            if (Gameover) {
                Constants.PAUSEVIEW.setText("GAME OVER");
                Constants.PAUSEVIEW.setTextColor(Color.RED);
                Constants.PAUSEVIEW.setVisibility(View.VISIBLE);

                Constants.SCOREVIEW.setVisibility(View.GONE);
                Constants.SCORETEXT.setVisibility(View.GONE);

                Constants.INSTRUCTIONS.setText("FINAL SCORE: " + String.valueOf(score));
                Constants.INSTRUCTIONS.setVisibility(View.VISIBLE);
                Constants.HIGHSCORETEXT.setVisibility(View.VISIBLE);
                Constants.HIGHSCORETEXT.setText("HIGH SCORE: " + Constants.HIGHSCORE);

                Constants.RESTART.setVisibility(View.VISIBLE);
                Constants.QUIT.setVisibility(View.VISIBLE);

                if (score > Constants.HIGHSCORE) {
                    Constants.EDITOR.putInt("HighScore", score);
                    Constants.EDITOR.apply();
                    Constants.HIGHSCORE = score;
                    Constants.INSTRUCTIONS.setText("FINAL SCORE: " + score);
                    Constants.HIGHSCORETEXT.setText("You have beaten your high score!");
                }
            }
        }
        handler.postDelayed(r, 10);
    }

    public void reset() {
        score = 0;
        initObstacle();
        initPlayer();
        Gameover = false;
        Pause = true;
        Constants.PAUSEVIEW.setText("PAUSE");
        Constants.PAUSEVIEW.setTextColor(Color.WHITE);
        Constants.PAUSEVIEW.setVisibility(View.GONE);

        /*Constants.SCOREVIEW.setVisibility(View.VISIBLE);
        Constants.SCORETEXT.setVisibility(View.VISIBLE);*/

        Constants.INSTRUCTIONS.setText("Put your finger on the character to unpause");
        Constants.INSTRUCTIONS.setVisibility(View.GONE);
        Constants.HIGHSCORETEXT.setVisibility(View.GONE);
        Constants.HIGHSCORETEXT.setText("HIGH SCORE: " + Constants.HIGHSCORE);

        Constants.RESTART.setVisibility(View.GONE);
        Constants.QUIT.setVisibility(View.GONE);

        Constants.SCOREVIEW.setText("0");

        speedCap1 = 5000;
        speedCap2 = 10000;
        speedCap3 = 20000;
        speedCapMax = 50000;
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

                    Constants.INITIAL.setVisibility(View.GONE);
                    Constants.SCOREVIEW.setVisibility(View.VISIBLE);
                    Constants.SCORETEXT.setVisibility(View.VISIBLE);
                    if(!Gameover) {
                        Constants.PAUSEVIEW.setVisibility(View.GONE);
                        Constants.INSTRUCTIONS.setVisibility(View.GONE);
                    }
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
                if(Constants.INITIAL.getVisibility() == View.GONE) {
                    Constants.PAUSEVIEW.setVisibility(View.VISIBLE);
                    Constants.INSTRUCTIONS.setVisibility(View.VISIBLE);
                }
                break;

            default:
                return false;
        }


        return super.onTouchEvent(event);
    }
}
