package com.mobdeve.project.sibat;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Player extends BaseObject {


    private ArrayList<Bitmap> arrBms = new ArrayList<>();

    private int count, vFlap, idCurrBitmap;

    /**
     * Constructor for player
     */
    public Player(){
        this.count = 0;
        this.vFlap = 5;
        this.idCurrBitmap = 0;
    }

    /**
     * Draws the player on the canvas
     * @param canvas canvas where player is drawn
     * @param Pause state of the game
     */
    public void draw(Canvas canvas, boolean Pause){


        canvas.drawBitmap(this.getBm(Pause), this.x, this.y, null);
    }


    /**
     *
     * @return the bitmaps of the player
     */
    public ArrayList<Bitmap> getArrBms() {
        return arrBms;
    }

    /**
     * Sets the bitmaps of the player
     * @param arrBms the bitmap to be set
     */
    public void setArrBms(ArrayList<Bitmap> arrBms) {
        this.arrBms = arrBms;

        for(int i = 0; i < arrBms.size(); i++){
            this.arrBms.set(i, Bitmap.createScaledBitmap(this.arrBms.get(i), this.width, this.height, true));
        }
    }

    /**
     * Returns the current bitmap of the player
     * @param Pause State of the game
     * @return the bitmap of the player
     */
    public Bitmap getBm(boolean Pause) {

        if(!Pause)
        count++;

        if(this.count == vFlap)
        {
            for(int i = 0; i < arrBms.size(); i++)
            {
                if(i == arrBms.size()-1){
                    this.idCurrBitmap = 0;
                    break;
                }
                else if(this.idCurrBitmap == i){
                    idCurrBitmap = i+1;
                    break;
                }
            }
            this.count = 0;
        }
        return this.getArrBms().get(idCurrBitmap);
    }

    /**
     *
     * @param y new y coordinate of the object
     */
    @Override
    public void setY(float y) {

        if(y + this.height < Constants.SCREEN_HEIGHT)
        this.y = y;
    }

    /**
     *
     * @param x new x coordinate of the object
     */
    @Override
    public void setX(float x) {

        if(x + this.width < Constants.SCREEN_WIDTH)
        this.x = x;
    }
}
