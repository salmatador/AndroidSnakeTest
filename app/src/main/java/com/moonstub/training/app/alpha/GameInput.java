package com.moonstub.training.app.alpha;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class GameInput{

    MotionEvent mEvent = null;

    public GameInput(Context context, View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mEvent == null){
                    mEvent = event;
                }
                return false;
            }
        });
    }

    public MotionEvent consumeEvent(){
        MotionEvent event = mEvent;
        mEvent = null;
        return event;
    }

}
