package com.mobdeve.project.sibat;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class BaseObject {
    protected float x, y;
    protected int width, height;
    protected Bitmap bm;
    protected Rect rect;

    public BaseObject() {

    }

    /**
     *
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param width width of the object
     * @param height height of the object
     */
    public BaseObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    /**
     *
     * @return the x coordinate of the object
     */
    public float getX() {
        return x;
    }

    /**
     * sets a new x coordinate for the object
     *
     * @param x new x coordinate of the object
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     *
     * @return the y coordinate of the object
     */
    public float getY() {
        return y;
    }

    /**
     * sets a new y coordinate for the object
     *
     * @param y new y coordinate of the object
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     *
     * @return the width of the object
     */
    public int getWidth() {
        return width;
    }

    /**
     * sets the new width of the object
     *
     * @param width new width of the object
     */
    public void setWidth(int width) {
        this.width = width;
    }


    /**
     *
     * @return the height of the object
     */
    public int getHeight() {
        return height;
    }

    /**
     * sets new height of the object
     *
     * @param height new height of the object
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return the bitmap of the object
     */
    public Bitmap getBm() {
        return bm;
    }

    /**
     * sets the bitmap of the object
     *
     * @param bm new bitmap of the object
     */
    public void setBm(Bitmap bm) {
        this.bm = bm;
    }


    /**
     *
     * @return the rectangle of the object
     */
    public Rect getRect() {

        return new Rect((int)this.x, (int)this.y, (int)this.x+this.width, (int)this.y+this.height);
    }

    /**
     * sets the rectangle of the object
     *
     * @param rect new rectangle of the object
     */
    public void setRect(Rect rect) {
        this.rect = rect;
    }
}
