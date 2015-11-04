package com.moonstub.training.app.alpha;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.List;

/**
 * Created by Micah on 11/2/2015.
 */
public class SnakeSection extends BaseGameObject {

    private int mColor = Color.YELLOW;

    public SnakeSection(int x, int y) {
        super(x, y);
    }

    public void incX(int inc){
        mX += inc;
    }

    public void incY(int inc){
        mY += inc;
    }

    public void setColor(int color){
        mColor = color;
    }

    public int getColor(){
        return mColor;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public void drawSnake(List<SnakeSection> snake, Canvas canvas, Paint paint) {

        for (int i = 0; i < snake.size(); i++) {
            paint.setColor(snake.get(i).getColor());
            snake.get(i).draw(canvas,paint);
        }
    }
}
