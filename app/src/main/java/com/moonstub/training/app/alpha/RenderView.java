package com.moonstub.training.app.alpha;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


/**
 * Created by Micah on 11/2/2015.
 */
public class RenderView extends SurfaceView implements Runnable {

    private static final String LOG_TAG = RenderView.class.getSimpleName();

    Game mGame;
    Thread mThread;
    boolean mIsRunning;
    SurfaceHolder mHolder;
    Bitmap mBuffer;
    //View mView;

    public RenderView(Game game, Bitmap buffer) {
        super(game);
        mGame = game;
        mBuffer = buffer;
        mHolder = getHolder();


    }

    public void resume() {
        mThread = new Thread(this);
        mIsRunning = true;
        mThread.start();
    }

    public void pause() {
        mIsRunning = false;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            //Error
        }

    }

    @Override
    public void run() {
        Rect rect = new Rect();
        Thread currentThread = Thread.currentThread();

        while (mIsRunning && currentThread == mThread) {
            if (!mHolder.getSurface().isValid()) {
                continue;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mGame.getCurrentScreen().update();
            mGame.getCurrentScreen().draw();


            Canvas canvas = mHolder.lockCanvas();
            canvas.getClipBounds(rect);
            canvas.drawBitmap(mBuffer, null, rect, null);
            mHolder.unlockCanvasAndPost(canvas);


        }

    }
}
