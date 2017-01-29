package com.simfoner.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.simfoner.gHelpers.AssetLoader;
import com.simfoner.gameobjects.Boll;

/**
 * Created by Valentin on 23.01.2017.
 */

public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;

        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 480, gameHeight);

        batcher = new SpriteBatch();

        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime) {
        //Gdx.app.log("GameRender", "render");
        Boll boll = myWorld.getBoll();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.draw(AssetLoader.boll, boll.getX(), boll.getY(), boll.getWidth(), boll.getHeight());

        batcher.end();

    }
}
