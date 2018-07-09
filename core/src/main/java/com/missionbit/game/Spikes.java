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
    public void drawDebug(ShapeRenderer renderer){ renderer.polygon(spike.getVertices()); }
    public boolean CollideWithPlayer(Rectangle Player){
        boolean CollideWithPlayer = false;
        if(spike.contains(Player.getX(),Player.getY())){
            CollideWithPlayer = true;

        }
        if(spike.contains(Player.getX()+Player.getWidth(),Player.getY()+Player.getHeight())){
            CollideWithPlayer = true;

        }
        if(spike.contains(Player.getX()+Player.getWidth(),Player.getY())){
            CollideWithPlayer = true;

        }
        if(spike.contains(Player.getX(),Player.getY()+Player.getHeight())){
            CollideWithPlayer = true;

        }
        return CollideWithPlayer;

    }
}