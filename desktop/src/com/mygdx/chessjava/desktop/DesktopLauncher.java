package com.mygdx.chessjava.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.chessjava.Chess;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Chess.SCREEN_WIDTH;
        config.height = Chess.SCREEN_HEIGHT;
        LwjglApplication app = new LwjglApplication(new Chess(), config);
    }
}
