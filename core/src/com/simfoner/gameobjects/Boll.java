package com.simfoner.gameobjects;

import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.sqrt;

/**
 * Created by Valentin on 23.01.2017.
 */

public class Boll {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation; // Для обработки поворота птицы
    private int width;
    private int height;

    private int screenX;
    private int screenY;

    private boolean touch;

    public Boll(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        screenX = ((int)position.x+24);
        screenY = (int)position.y+24;
    }
    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));
        if (position.x > 432){
            position.x = 432;
            velocity.x = 0;
        }
        if (position.x < 0){
            position.x = 0;
            velocity.x = 0;
        }
        if (position.y > 752){
            position.y = 752;
            velocity.y = 0;
        }
        if (position.y < 0){
            position.y = 0;
            velocity.y = 0;
        }

        if(!touch) {
            acceleration.x = 0;
            acceleration.y = 0;
            if (velocity.x > 10) {
                velocity.x += -2;
            }
            if (velocity.x < -10) {
                velocity.x += 2;
            }
            if (velocity.y > 10) {
                velocity.y += -2;
            }
            if (velocity.y < -10) {
                velocity.y += 2;
            }
            if (velocity.x >= -10&&velocity.x <= 10){
                velocity.x = 0;
            }
            if (velocity.y >= -10&&velocity.y <= 10){
                velocity.y = 0;
            }
        }
        if(((screenX-(position.x+24))*(screenX-(position.x+24))+(screenY-(position.y+24))*(screenY-(position.y+24))!=0)&&touch) {
            acceleration.x = 300*(screenX-(position.x+24))/((float)sqrt(sqrt((screenX-(position.x+24))*(screenX-(position.x+24))+(screenY-(position.y+24))*(screenY-(position.y+24)))*(float)sqrt((screenX-(position.x+24))*(screenX-(position.x+24))+(screenY-(position.y+24))*(screenY-(position.y+24)))));
            acceleration.y = 300*(screenY-(position.y+24))/((float)sqrt(sqrt((screenX-(position.x+24))*(screenX-(position.x+24))+(screenY-(position.y+24))*(screenY-(position.y+24)))*(float)sqrt((screenX-(position.x+24))*(screenX-(position.x+24))+(screenY-(position.y+24))*(screenY-(position.y+24)))));
            //System.out.println("acceleration.x  "+acceleration.x);
        }

        position.add(velocity.cpy().scl(delta));

    }

    public void onTouchDown(int screenX, int screenY) {
        this.screenX = screenX;
        this.screenY = screenY;
        touch = true;
        if (screenX != position.x);
        //acceleration.x = 200*(screenX-position.x)/(float)sqrt((screenX-position.x)*(screenX-position.x)+(screenY-position.y)*(screenY-position.y));
        //acceleration.y = 200*(screenY-position.y)/(float)sqrt((screenX-position.x)*(screenX-position.x)+(screenY-position.y)*(screenY-position.y));
        //System.out.println("position.x"+position.x);
        //System.out.println("screenX"+screenX);
        //velocity.y = -340;
    }
    public void onTouchUp(int screenX, int screenY) {
        touch = false;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }
}
