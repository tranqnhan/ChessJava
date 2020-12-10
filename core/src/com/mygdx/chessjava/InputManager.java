package com.mygdx.javachess;

import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor{
    
    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
       MouseEventHandler.activateListeners(button, x, y);
       return true;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;    
    }

    @Override
    public boolean keyTyped(char c) {
        return false;    
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;    
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;    
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
