package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by missionbit on 7/3/18.
 */

public class Enemies {
    private Sprite Spider;
    private Sprite Drone;
    private Sprite Armaroll;

    private int direction = -1;
    //private int otherdirection = 5;
    private float speed = 1;

    public Enemies(float x, float y) {
        Spider = new Sprite(new Texture(Gdx.files.internal("images/TutorialSpider.png")));
        Spider.setX(x);
        Spider.setY(y);

    }

    public void draw(SpriteBatch batch) {
        Spider.setX(Spider.getX() + speed * direction);
        //  images.setX(images.getX() + speed * otherdirection);
        Spider.draw(batch);
    }
}
