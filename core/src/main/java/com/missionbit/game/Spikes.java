package com.missionbit.game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Spikes {
    public Polygon spike;
    private boolean showDebug = true;

    public Spikes(float[] points ) {
        boolean showDebug = true;
        spike = new Polygon(points);

    }
    public void drawDebug(ShapeRenderer renderer){
        renderer.polygon(spike.getVertices());
    }
//    public boolean hit(Rectangle other){
//        boolean flag = spike.overlaps(other);
//        return flag;
//
//    }
//    public float getTop(){
//        return platform.getY()+platform.height;
//    }
//    public float getLeft(){
//        return platform.getX();
//    }
//    public float getRight(){
//        return platform.getX()+platform.width;
//    }

}