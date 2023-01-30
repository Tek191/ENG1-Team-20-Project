package com.gourdoramsay.piazzapanic;
import com.badlogic.gdx.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gourdoramsay.piazzapanic.screens.GameScreen;
import com.gourdoramsay.piazzapanic.screens.MainMenu;

public class PiazzaPanic extends Game {
	public static final int V_WIDTH = 448;
	public static final int V_HEIGHT = 384;

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
