package com.mygdx.tanks2d.InputProcessor;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.tanks2d.Screens.GamePlayScreen;

public class InputProcessorPC implements InputProcessor {
    GamePlayScreen gps;


    public InputProcessorPC(GamePlayScreen gamePlayScreen) {
        this.gps = gamePlayScreen;

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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
