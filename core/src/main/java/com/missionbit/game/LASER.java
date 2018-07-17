package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LASER {
    private ParticleEffect effect;

 public LASER(float x, float y){
     effect = new ParticleEffect();
     effect.load(Gdx.files.internal("images/particle effects/Laser"), Gdx.files.internal("images/particle effects"));
     effect.setPosition(x, y);
     effect.start();
 }
     public void draw(SpriteBatch myBatch){
         effect.draw(myBatch, Gdx.graphics.getDeltaTime());


     }



//             effect.setPosition(myImage.getX() + myImage.getWidth() / 2.0f, myImage.getY() + myImage.getHeight() / 2.0f);
//
//                 effect.start();
//
//
//            effect.draw(myBatch, Gdx.graphics.getDeltaTime());
        }
