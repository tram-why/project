package com.simfoner.gravity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.simfoner.gHelpers.AssetLoader;
import com.simfoner.screens.GameScreen;


public class GGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("GGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
