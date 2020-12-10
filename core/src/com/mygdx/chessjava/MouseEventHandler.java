package com.mygdx.javachess;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.math.Vector3;

public class MouseEventHandler {
    static List<MouseEventListener> listeners = new ArrayList<>();
    
    static void addListener(MouseEventListener listener) {
        listeners.add(listener);
    }
    
    static void activateListeners(int button, int x, int y) {
        Vector3 input = new Vector3(x, y, 0);
        Chess.CAMERA.unproject(input);
        int flipY = Chess.SCREEN_HEIGHT - (int)input.y;
        
        for (MouseEventListener l : listeners) {
            l.activateEvent(button, (int)input.x, flipY);
        }
    }
}
