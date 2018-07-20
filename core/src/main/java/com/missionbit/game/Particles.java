package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Particles extends Collidable{
    public Rectangle getrect() {
        return bounds;
    }
private Rectangle bounds;

    private ParticleEffect portal;
    public Particles(float x, float y){

     portal = new ParticleEffect();
     bounds = new Rectangle(x-40,y-35,85,85);
     portal.load(Gdx.files.internal("images/particle effects/NewPortal"), Gdx.files.internal("images/particle effects"));
     portal.setPosition(x, y);

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
