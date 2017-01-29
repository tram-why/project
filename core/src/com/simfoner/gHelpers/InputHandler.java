package com.simfoner.gHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.simfoner.gameobjects.Boll;

/**
 * Created by Valentin on 23.01.2017.
 */

public class InputHandler implements InputProcessor {
    private Boll myBoll;
    private float screenWidth = Gdx.graphics.getWidth();
    private float gameWidth = 480;
    private float screenSize = (screenWidth/gameWidth);

    public InputHandler(Boll boll){
        myBoll = boll;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //myBoll.onTouchDown(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        myBoll.onTouchUp((int)(screenX/screenSize), (int)(screenY/screenSize));
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        myBoll.onTouchDown((int)(screenX/screenSize), (int)(screenY/screenSize));
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
