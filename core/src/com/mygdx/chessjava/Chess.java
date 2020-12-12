package com.mygdx.chessjava;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chess extends ApplicationAdapter {
	SpriteBatch batch;
    Board board;

    public static final int SCREEN_WIDTH = 512;
    public static final int SCREEN_HEIGHT = 512;
    public static final OrthographicCamera CAMERA = new OrthographicCamera();
        
	@Override
	public void create () {
        this.board = RulesSetup.setBoardFromFile();

        InputManager inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);
        Chess.CAMERA.setToOrtho(true, Chess.SCREEN_WIDTH, Chess.SCREEN_HEIGHT);

        this.batch = new SpriteBatch();
    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();
        this.board.render(this.batch);
        this.batch.end();
	}
	
	@Override
	public void dispose () {
        this.board.dispose();
        this.batch.dispose();
	}
}
