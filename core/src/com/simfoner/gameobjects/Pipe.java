package com.simfoner.gameobjects;

import java.util.Random;

/**
 * Created by Valentin on 29.01.2017.
 */

public class Pipe extends Scrollable {

    private Random r;

    public Pipe(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

        r = new Random();
    }
    @Override
    public void  reset(float newX){
        super.reset(newX);

        height = r.nextInt(680) + 15;
    }
}
