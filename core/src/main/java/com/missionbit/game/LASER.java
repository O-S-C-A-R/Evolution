package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LASER {
    private ParticleEffect portal;

    public LASER(float x, float y){
     portal = new ParticleEffect();
     portal.load(Gdx.files.internal("images/particle effects/tutorialportal"), Gdx.files.internal("images/particle effects"));
     portal.setPosition(1750, 250);

        portal.start();

    }
     public void draw(SpriteBatch myBatch){
         portal.draw(myBatch, Gdx.graphics.getDeltaTime());


     }



//             effect.setPosition(myImage.getX() + myImage.getWidth() / 2.0f, myImage.getY() + myImage.getHeight() / 2.0f);
//
//                 effect.start();
//
//
//            effect.draw(myBatch, Gdx.graphics.getDeltaTime());



        }
