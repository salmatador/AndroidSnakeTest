package com.moonstub.training.app.alpha;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Abstract Class for creating Game Screens
 */
public abstract class BaseScreen {

    private Game mGame;
    private int mWidth;
    private int mHeight;
    private Paint mPaint = new Paint();
    private Bitmap mBuffer;
    private Canvas mCanvas;

    public BaseScreen(Game game, int width, int height){
        mGame = game;
        mWidth = width;
        mHeight = height;
        mBuffer = mGame.getGraphics();
        mCanvas = new Canvas(mBuffer);
        init();
    }

    public abstract void init();
    public abstract void update();

    public abstract void draw();

    public Game getGame(){
        return mGame;
    }

    public int getWidth(){
        return mWidth;
    }

    public int getHeight(){
        return mHeight;
    }

    public Paint getPaint(){
        return mPaint;
    }

    public Canvas getCanvas(){
        return mCanvas;
    }

    public Bitmap getGraphics(){
        return mBuffer;
    }


    public abstract void backPressed();
}
