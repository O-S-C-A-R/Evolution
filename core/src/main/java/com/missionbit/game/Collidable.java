package com.missionbit.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class Collidable {
    public boolean hit(Rectangle other) {
        boolean flag = getrect().overlaps(other);
        System.out.println(flag);

        return flag;
    }
    public void drawDebug(ShapeRenderer renderer){
        renderer.rect(getrect().getX(), getrect().getY(), getrect().getWidth(), getrect().getHeight());
    }
    public abstract Rectangle getrect();

}
