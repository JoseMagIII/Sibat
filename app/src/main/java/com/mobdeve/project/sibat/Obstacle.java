package com.mobdeve.project.sibat;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Obstacle extends BaseObject{

    public static int speed;

    public Obstacle(float x, float y, int width, int height) {
        super(x, y, width, height);
        speed = 10*Constants.SCREEN_WIDTH/1080;
    }

    public void draw(Canvas canvas, boolean Pause){
        if(!Pause)
        this.y += speed;

        canvas.drawBitmap(this.bm, this.x, this.y, null);
    }

    @Override
    public void setBm(Bitmap bm){
        this.bm = Bitmap.createScaledBitmap(bm,  width, height, true);
    }
}
