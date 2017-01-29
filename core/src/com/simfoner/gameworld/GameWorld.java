package com.simfoner.gameworld;

import com.simfoner.gameobjects.Boll;
import com.simfoner.gameobjects.Pipe;
import com.simfoner.gameobjects.ScrollHandler;

/**
 * Created by Valentin on 23.01.2017.
 */

public class GameWorld {
    private Boll boll;
    private ScrollHandler scroller;


    public GameWorld(int midPointY){
        boll = new Boll(30, midPointY / 2 - 24, 48, 48);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        //Gdx.app.log("GameWorld", "update");
        boll.update(delta);
        scroller.update(delta);
    }
    public Boll getBoll(){
        return boll;
    }
    public ScrollHandler getScroller() {
        return scroller;
    }
}
