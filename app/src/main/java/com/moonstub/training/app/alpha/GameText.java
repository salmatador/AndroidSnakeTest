package com.moonstub.training.app.alpha;

import android.graphics.Color;

/**
 * Created by Micah on 11/3/2015.
 */
public class GameText {

    float mX,mY;
    String mText;
    int mTextSize;
    Screen mScreen;
    int mColor = Color.WHITE;

    public GameText(Screen screen, int textSize, float x, float y) {
        mScreen = screen;
        mTextSize = textSize;
        mText = "Default Game Text Message";
        mY = y;
        mX = x;
    }


    public void draw(){
        mScreen.getPaint().setColor(mColor);
        mScreen.getPaint().setTextSize(mTextSize);
        mScreen.getCanvas().drawText(getText(),getX(),getY(),mScreen.getPaint());
    }

    public float getX() {
        return mX;
    }

    public void setX(float x) {
        mX = x;
    }

    public float getY() {
        return mY;
    }

    public void setY(float y) {
        mY = y;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
    }

    public Screen getScreen() {
        return mScreen;
    }

    public void setScreen(Screen screen) {
        mScreen = screen;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public boolean hasMessage() {
        return mText != null;
    }
}
