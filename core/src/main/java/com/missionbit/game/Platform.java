package com.missionbit.game;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
    protected Rectangle platform;
    public Platform(float x, float y, float w, float h){
        platform = new Rectangle(x,y,w,h);

    }
    public void drawDebug(ShapeRenderer renderer){
        renderer.rect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
    }
    public boolean hit(Rectangle other){
        boolean flag = platform.overlaps(other);
        return flag;
        }
        public boolean hit(Player other) {
           return hit(other.getBounding());
        }
    public float getTop(){
        return platform.getY()+platform.height;
    }
    public float getLeft(){
        return platform.getX();
    }
    public float getRight(){
        return platform.getX()+platform.width;
    }




}