package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

/**
 * Created by missionbit on 7/19/18.
 */

public class Laser {
    private ParticleEffect Cannon;

    public Laser(float x, float y){


        Cannon = new ParticleEffect();
        Cannon.load(Gdx.files.internal("images/particle effects/Laser"), Gdx.files.internal("images/particle effects"));
        Cannon.setPosition(x, y);




    }







}
