package com.missionbit.game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Spikes {
    public Polygon spike;
    private boolean showDebug = true;
    public static int collideside;

    public Spikes(float[] points) {
        boolean showDebug = true;
        spike = new Polygon(points);
    }

    public void drawDebug(ShapeRenderer renderer) {
        renderer.polygon(spike.getVertices());
    }

    public boolean CollideWithPlayer(Player player) {
        Rectangle r = player.getBounding();

        boolean CollideWithPlayer = false;
        if (spike.contains(r.getX(), r.getY())) {
            CollideWithPlayer = true;
            collideside = 1;

        }
        if (spike.contains(r.getX() + r.getWidth(), r.getY() + r.getHeight())) {
            CollideWithPlayer = true;
            collideside = 2;

        }
        if (spike.contains(r.getX() + r.getWidth(), r.getY())) {
            CollideWithPlayer = true;
            collideside = 2;

        }

        if (spike.contains(r.getX(), r.getY() + r.getHeight())) {
            CollideWithPlayer = true;
            collideside = 1;

        }
        return CollideWithPlayer;
    }
}

