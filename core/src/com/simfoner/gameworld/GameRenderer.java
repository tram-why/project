package com.simfoner.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.simfoner.gHelpers.AssetLoader;
import com.simfoner.gameobjects.Boll;
import com.simfoner.gameobjects.Pipe;
import com.simfoner.gameobjects.ScrollHandler;

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

    private Boll boll;
    private ScrollHandler scroller;
    private Pipe pipe1, pipe2, pipe3;


    private TextureRegion bollImg;
    private TextureRegion skull;

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

        initGameObjects();
        initAssets();
    }
    private void initGameObjects() {
        boll = myWorld.getBoll();
        scroller = myWorld.getScroller();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
    }

    private void initAssets() {
        bollImg = AssetLoader.boll;
        skull = AssetLoader.skull;

    }
    private void drawPipes() {
        // Временный код, извините за кашу :)
        // Мы это починим, как только закончим с Pipe классом.

        batcher.draw(skull, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),  pipe1.getHeight());
        batcher.draw(skull, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 120, pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 90));

        batcher.draw(skull, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),pipe2.getHeight());
        batcher.draw(skull, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 120, pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 90));

        batcher.draw(skull, pipe3.getX(), pipe3.getY(), pipe3.getWidth(), pipe3.getHeight());
        batcher.draw(skull, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 120, pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 90));
    }
    public void render(float runTime) {
        //Gdx.app.log("GameRender", "render");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.draw(bollImg, boll.getX(), boll.getY(), boll.getWidth(), boll.getHeight());
        drawPipes();
        batcher.enableBlending();
        batcher.end();


    }

}
