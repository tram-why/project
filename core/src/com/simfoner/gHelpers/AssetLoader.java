package com.simfoner.gHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by Valentin on 23.01.2017.
 */

public class AssetLoader {
    public static Texture texture;

    public static TextureRegion boll;

    public static TextureRegion skull;

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        boll = new TextureRegion(texture, 0, 0, 48, 48);
        boll.flip(false, true);

        skull = new TextureRegion(texture, 49, 0, 112, 16);
        skull.flip(false, true);
    }

    public static void dispose() {
        texture.dispose();
    }
}
