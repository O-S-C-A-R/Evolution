package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by missionbit on 7/3/18.
 */

public class Enemies {
    private Animation<TextureRegion> Spider;
    private Sprite Drone;
    private Texture Sheet;
    private Sprite Armaroll;
    private Vector2 pos;
    private int frame = 8;
    private int Rows = 3;
    private int Cols = 3;
    private float stateTime;
    private int direction = -1;
    //private int otherdirection = 5;
    private float speed = 1;

    public Enemies(float x, float y) {
        Sheet = new Texture(Gdx.files.internal("images/TutorialSpider.png"));
        TextureRegion[][] tmp = TextureRegion.split(Sheet,
        Sheet.getWidth() / Cols,
        Sheet.getHeight() / Rows);

        TextureRegion[] Frames = new TextureRegion[frame];
        int index = 0;
        for (int i = 0; i <Rows; i++){
        for ( int j = 0; j < Cols; j++){
        if (index < frame){
                 Frames[index++] = tmp[i][j];
                }
            }
        }

        Spider = new Animation<TextureRegion>(0.025f, Frames);
        pos = new Vector2();
        pos.x = x;
        pos.y = y;
        stateTime = 0;
    }

    public void draw(SpriteBatch batch) {
        pos.x = pos.x + speed * direction;
        //  images.setX(images.getX() + speed * otherdirection);
       // Spider.draw(batch);
      stateTime += Gdx.graphics.getDeltaTime();
      TextureRegion drawFrame = Spider.getKeyFrame(stateTime, true);
      batch.draw(drawFrame, pos.x, pos.y);
    }
}
