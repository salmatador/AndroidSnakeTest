package com.moonstub.training.app.alpha;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Micah on 11/3/2015.
 */
public class Apple extends BaseGameObject {

    int mColor = Color.GREEN;

    public Apple(int x, int y) {
        super(x, y);
    }

    public Apple() {

    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(getColor());
        super.draw(canvas,paint);
    }


    public int getColor() {
        return mColor;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }
}
