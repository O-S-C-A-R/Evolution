package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by missionbit on 7/19/18.
 */

public class Laser {
    private ParticleEffect Cannon;
    private Sprite small;
    public Laser(float x, float y){


        Cannon = new ParticleEffect();
        Cannon.load(Gdx.files.internal("images/particle effects/Laser"), Gdx.files.internal("images/particle effects"));
        Cannon.setPosition(x, y);
        Cannon.start();

         small = new Sprite(new Texture(Gdx.files.internal("images/Enemies/LaserCannonTwo.png")));
         small.setX(x);
         small.setY(y);


    }
      public void draw(SpriteBatch batch){
      Cannon.draw(batch, Gdx.graphics.getDeltaTime());
      small.draw(batch);



      }






}
