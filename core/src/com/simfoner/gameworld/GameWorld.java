package com.simfoner.gameworld;

import com.simfoner.gameobjects.Boll;

/**
 * Created by Valentin on 23.01.2017.
 */

public class GameWorld {
    private Boll boll;

    public GameWorld(int midPointY){
        boll = new Boll(30, midPointY - 24, 48, 48);
    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        boll.update(delta);
    }
    public Boll getBoll(){
        return boll;
    }
}
