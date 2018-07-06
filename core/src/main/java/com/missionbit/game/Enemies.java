package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by missionbit on 7/3/18.
 */

public class Enemies {
    private Sprite images;
    private int direction = -1;
    //private int otherdirection = 5;
    private float speed = 1;

    public Enemies(float x, float y) {
        images = new Sprite(new Texture(Gdx.files.internal("images/TutorialSpider.png")));
        images.setX(x);
        images.setY(y);

    }

    public void draw(SpriteBatch batch) {
        images.setX(images.getX() + speed * direction);
        //  images.setX(images.getX() + speed * otherdirection);
        images.draw(batch);
    }
}
