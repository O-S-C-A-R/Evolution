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

<<<<<<< HEAD:core/src/main/java/com/missionbit/game/LASER.java
    public LASER(float x, float y){
=======
    private ParticleEffect portal;

    public Particles(float x, float y){
>>>>>>> 6cabe87c9c0cabe49e8a6f0a82930a6b5c37137d:core/src/main/java/com/missionbit/game/Particles.java
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
