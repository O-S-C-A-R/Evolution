package com.missionbit.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Spider extends Enemies{
    private Sprite spider;

    public Spider (float x, float y, float left, float right){
        super(x,y,left,right);


    }
    public void drawDebug(ShapeRenderer renderer){
        renderer.rect(spider.getX(), spider.getY(), spider.getWidth(), spider.getHeight());
    }

}
