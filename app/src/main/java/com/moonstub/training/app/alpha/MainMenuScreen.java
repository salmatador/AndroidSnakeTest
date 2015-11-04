package com.moonstub.training.app.alpha;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

/**
 * Created by Micah on 11/2/2015.
 */
public class MainMenuScreen extends BaseScreen {


    public MainMenuScreen(Game game, int width, int height) {
        super(game, width, height);

    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        Bitmap map = getGame().getGraphics();
        Canvas canvas = new Canvas(map);
        //Paint paint = new Paint();
        getPaint().setColor(Color.BLACK);
        canvas.drawRect(new Rect(0,0,getWidth(),getHeight()),getPaint());
        getPaint().setColor(Color.RED);
        canvas.drawRect(new Rect(100,100,200, 200), getPaint());
        getPaint().setColor(Color.WHITE);
        getPaint().setTextSize(50f);
        //canvas.drawText("Time" + " : " + currentTime,100f,100f,getPaint());
    }

    @Override
    public void backPressed() {

    }
}
