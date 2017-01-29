package com.simfoner.gravity.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.simfoner.gravity.GGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Gravity";
		config.width = 480;
		config.height = 800;
		new LwjglApplication(new GGame(), config);
	}
}
