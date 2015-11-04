package com.moonstub.training.app.alpha;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Micah on 11/2/2015.
 */
public class Game extends AppCompatActivity {

    private static final String LOG_TAG = Game.class.getSimpleName();

    int width;
    int height;

    Bitmap mBuffer;
    RenderView mRenderView;
    Screen mCurrentScreen;
    Context mContext;
    GameInput mGameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels / 50 * 50;
        height = dm.heightPixels / 50 * 50;
        Log.v(LOG_TAG, width + " : " + height);

        mBuffer = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_4444);
        mCurrentScreen = new Screen(this, width, height);
        mRenderView = new RenderView(this, mBuffer);
        mGameInput = new GameInput(this, mRenderView);
        setContentView(mRenderView);


    }

    public Bitmap getGraphics(){
        return mBuffer;
    }
    public Screen getCurrentScreen(){
        return mCurrentScreen;
    }
    public GameInput getGameInput() { return mGameInput;}

    @Override
    public void onBackPressed() {
        if (getCurrentScreen().mGAME_states != GAME_STATES.PAUSED) {
            getCurrentScreen().backPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRenderView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRenderView.pause();
    }
}
