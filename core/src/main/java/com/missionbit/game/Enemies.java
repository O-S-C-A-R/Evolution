package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by missionbit on 7/3/18.
 */

public class Enemies {
    private Animation<TextureRegion> Spider;
    private Sprite Drone;
    private Sprite Armaroll;
    private Vector2 pos;
    private float stateTime;
    private int direction = -1;
    private float leftbound;
    private float rigthbound;
    //private int otherdirection = 5;
    private float speed = 1;


    public Enemies(float x, float y, float left, float right) {

        Spider = Utils.LoadAnimation("images/Enemies/TutorialSpider.png", 3, 3, 8, .15f);
        pos = new Vector2();
        pos.x = x;
        leftbound = left;
        rigthbound = right;
        pos.y = y;
        stateTime = 0;
    }

    public void draw(SpriteBatch batch) {
        pos.x = pos.x + speed * direction;
        if (leftbound > pos.x ){
        direction = 1;
        }
        if (rigthbound < pos.x){
        direction = -1;
        }
        //  images.setX(images.getX() + speed * otherdirection);
       // Spider.draw(batch);
      stateTime += Gdx.graphics.getDeltaTime();
      TextureRegion drawFrame = Spider.getKeyFrame(stateTime, true);
      batch.draw(drawFrame, pos.x, pos.y);
    }
public boolean spidercollide (Player idk){
    boolean flag = getBounding().overlaps(idk.getBounding());
    return flag ;
    }
    public void drawDebug(ShapeRenderer renderer){
        Rectangle rect = getBounding();
        renderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }
public Rectangle getBounding() {
    TextureRegion texture = Spider.getKeyFrame(stateTime, true);
    return new Rectangle(pos.x,pos.y,texture.getRegionWidth(),texture.getRegionHeight());
    }


    public float getTop(){
        return getBounding().getY()+getBounding().height;
    }
    public float getLeft(){
        return getBounding().getX();
    }
    public float getRight(){
        return getBounding().getX()+getBounding().width;
    }
}

