package com.mobdeve.project.sibat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {

    private Player player;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        player = new Player();
        player.setWidth(100*Constants.SCREEN_WIDTH/1080);
        player.setHeight(100*Constants.SCREEN_HEIGHT/1920);
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
    }
}
