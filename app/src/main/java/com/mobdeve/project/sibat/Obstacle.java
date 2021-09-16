package com.mobdeve.project.sibat;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Obstacle extends BaseObject{

    public static int speed;

    /**
     * Constructor of the obstacle
     *
     * @param x x coordinate of the obstacle
     * @param y y coordinate of the obstacle
     * @param width width of the obstacle
     * @param height height of the obstacle
     */
    public Obstacle(float x, float y, int width, int height) {
        super(x, y, width, height);
        //Initial speed of the obstacle
        speed = 5*Constants.SCREEN_WIDTH/1080;
    }

    /**
     * Draws this obstacle on the canvas
     *
     * @param canvas canvas where obstacle is drawn
     * @param Pause status of the game
     */
    public void draw(Canvas canvas, boolean Pause){
        if(!Pause)
        this.y += speed;

        canvas.drawBitmap(this.bm, this.x, this.y, null);
    }

    /**
     * Sets new bitmap of the object
     *
     * @param bm new bitmap of the object
     */
    @Override
    public void setBm(Bitmap bm){
        this.bm = Bitmap.createScaledBitmap(bm,  width, height, true);
    }

    /**
     * Doubles the speed of the object
     */
    public void speedUp() {
        speed = speed*2;
    }
}
