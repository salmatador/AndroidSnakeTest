package com.moonstub.training.app.alpha;

import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Test Screen Class
 */
public class Screen extends BaseScreen {


    private static final String LOG_TAG = Screen.class.getSimpleName();

    GAME_STATES mGAME_states = GAME_STATES.NEW;
    int moveX = 0;
    int moveY = 0;
    GameText mGameMessage;

    DIRECTION mDIRECTION = DIRECTION.NO_DIRECTION;
    MotionEvent mMotionEvent = null;

    List<SnakeSection> snake;
    Apple mApple;

    public Screen(Game game, int width, int height) {
        super(game, width, height);
    }

    public void init() {
        mGameMessage = new GameText(this, 75, 100, getHeight() / 2);
        snake = new LinkedList<SnakeSection>();
        snake.add(new SnakeSection(150, 50));
        snake.add(new SnakeSection(100, 50));
        snake.add(new SnakeSection(50, 50));

        snake.get(0).setColor(Color.RED);
        mApple = new Apple();
        dropApple();

    }

    private void dropApple() {
        Random rand = new Random();
        mApple.setX(rand.nextInt((10)) * 50 + 50);
        mApple.setY(rand.nextInt((10)) * 50 + 50);

        for(int link = 0; link < snake.size(); link++){
            SnakeSection s = snake.get(link);
            if(mApple.getX() == s.getX() && mApple.getY() == s.getY()){
                Log.v("APPLE SNAKE COLLISION", "REQUESTING NEW APPLE");
                dropApple();
                break;
            }
        }

    }

    public void update() {

        switch (mGAME_states) {
            case DEMO:
                demoMode();
                break;
            case PAUSED:
                pausedMode();
                break;
            case RUNNING:
                updateGameRunning();
                break;
            case GAME_OVER:
                gameOver();
                break;
            case NEW:
                SetupGame();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void SetupGame() {
        mGameMessage.setText(getGame().getResources().getString(R.string.begin_game_text));
        mMotionEvent = getGame().getGameInput().consumeEvent();
        if (mMotionEvent != null && mMotionEvent.getAction() == 0) {
            mGameMessage.setText(null);
            mGAME_states = GAME_STATES.RUNNING;
            mDIRECTION = DIRECTION.EAST;
        }

    }

    private void gameOver() {
        mGameMessage.setText(getGame().getResources().getString(R.string.game_over_text));
    }

    private void pausedMode() {
        mGameMessage.setText(getGame().getResources().getString(R.string.game_paused_text));
        mMotionEvent = getGame().getGameInput().consumeEvent();
        if (mMotionEvent != null && mMotionEvent.getAction() == 0) {
            mGameMessage.setText(null);
            mGAME_states = GAME_STATES.RUNNING;
        }
    }

    private void demoMode() {

    }

    public void updateGameRunning() {
        //Check for Event
        mMotionEvent = getGame().getGameInput().consumeEvent();
        if (mMotionEvent != null && mMotionEvent.getAction() == 0) {
            changeDirection(mMotionEvent.getRawX() > getWidth() / 2);
        }

        switch (mDIRECTION) {
            case NO_DIRECTION:
                moveX = 0;
                moveY = 0;
                break;
            case NORTH:
                moveX = 0;
                moveY = -50;
                break;
            case SOUTH:
                moveX = 0;
                moveY = 50;
                break;
            case EAST:
                moveX = 50;
                moveY = 0;
                break;
            case WEST:
                moveX = -50;
                moveY = 0;
                break;
            default:
                throw new IllegalArgumentException();
        }
        MoveSnake();
        checkCollision(snake.get(0));
    }

    private void checkCollision(SnakeSection head) {
        int x = head.getX();
        int y = head.getY();

        if(x == mApple.getX() && y == mApple.getY()){
            addSnakeSection();
            dropApple();
            //Add to Score
        }
        for(int section = 1; section < snake.size(); section++) {
            SnakeSection s = snake.get(section);
            if (x == s.getX() && y == s.getY()){
               mGAME_states = GAME_STATES.GAME_OVER;
            }
        }
    }

    private void addSnakeSection(){
        snake.add(new SnakeSection(-100,-100));
    }

    private void MoveSnake() {
        for (int i = snake.size() - 1; i >= 0; i--) {
            SnakeSection link = snake.get(i);

            if (i != 0) {
                link.setX(snake.get(i - 1).getX());
                link.setY(snake.get(i - 1).getY());
            } else {
                link.incX(moveX);
                link.incY(moveY);

                //Adjust Snake Head if Off Screen
                if (link.getX() < 0) {
                    link.setX(getWidth() - link.getWidth());
                }
                if (link.getX() > getWidth() - link.getWidth()) {
                    link.setX(0);
                }
                if (link.getY() < 0) {
                    link.setY(getHeight() - link.getWidth());
                }
                if (link.getY() > getHeight() - link.getWidth()) {
                    link.setY(0);
                }
            }
        }
    }

    private void changeDirection(boolean forward) {
        if (forward) {
            switch (mDIRECTION) {
                case EAST:
                    mDIRECTION = DIRECTION.SOUTH;
                    break;
                case SOUTH:
                    mDIRECTION = DIRECTION.WEST;
                    break;
                case WEST:
                    mDIRECTION = DIRECTION.NORTH;
                    break;
                case NO_DIRECTION:
                case NORTH:
                    mDIRECTION = DIRECTION.EAST;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            switch (mDIRECTION) {
                case EAST:
                    mDIRECTION = DIRECTION.NORTH;
                    break;
                case NORTH:
                    mDIRECTION = DIRECTION.WEST;
                    break;
                case WEST:
                    mDIRECTION = DIRECTION.SOUTH;
                    break;
                case NO_DIRECTION:
                case SOUTH:
                    mDIRECTION = DIRECTION.EAST;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

    }


    public void draw() {
        //Reset Background
        getPaint().setColor(Color.BLACK);
        getCanvas().drawRect(new Rect(0, 0, getWidth(), getHeight()), getPaint());

        mApple.draw(getCanvas(),getPaint());
        snake.get(0).drawSnake(snake, getCanvas(), getPaint());

        if (mGameMessage.hasMessage()) {
            mGameMessage.draw();
        }

    }

    @Override
    public void backPressed() {
        mGAME_states = GAME_STATES.PAUSED;
    }

}
