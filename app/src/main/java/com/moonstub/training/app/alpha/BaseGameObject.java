package com.moonstub.training.app.alpha;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Micah on 11/2/2015.
 */
public class BaseGameObject {

    int mWidth = 50;
    int mHeight = 50;
    int mX;
    int mY;

    public BaseGameObject(int x,int y){
        mX = x;
        mY = y;
    }

    public BaseGameObject() {
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawRect(new Rect(mX,mY,mX+mWidth,mY+mHeight), paint);
    }

    public void setX(int x){
        mX = x;
    }

    public void setY(int y){
        mY = y;
    }


    public int getWidth() {
        return mWidth;
    }
}
