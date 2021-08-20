package com.mobdeve.project.sibat;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Player extends BaseObject {


    private ArrayList<Bitmap> arrBms = new ArrayList<>();

    private int count, vFlap, idCurrBitmap;

    public Player(){
        this.count = 0;
        this.vFlap = 5;
        this.idCurrBitmap = 0;
    }

    public void draw(Canvas canvas, boolean Pause){


        canvas.drawBitmap(this.getBm(Pause), this.x, this.y, null);
    }

    public ArrayList<Bitmap> getArrBms() {
        return arrBms;
    }

    public void setArrBms(ArrayList<Bitmap> arrBms) {
        this.arrBms = arrBms;

        for(int i = 0; i < arrBms.size(); i++){
            this.arrBms.set(i, Bitmap.createScaledBitmap(this.arrBms.get(i), this.width, this.height, true));
        }
    }

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

    @Override
    public void setY(float y) {

        if(y + this.height < Constants.SCREEN_HEIGHT)
        this.y = y;
    }

    @Override
    public void setX(float x) {

        if(x + this.width < Constants.SCREEN_WIDTH)
        this.x = x;
    }
}
