package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by missionbit on 7/19/18.
 */

public class Laser {
    private ParticleEffect Cannon;
    private Sprite small;
    private Animation<TextureRegion> beam;
    private Animation<TextureRegion> KickBackLeft;
    private float Cannontime = 0;
    private float KickBackLeftTime = 0;
    public Laser(float x, float y){


        Cannon = new ParticleEffect();
        Cannon.load(Gdx.files.internal("images/particle effects/Laser"), Gdx.files.internal("images/particle effects"));
        Cannon.setPosition(x, y);
        Cannon.start();

         small = new Sprite(new Texture(Gdx.files.internal("images/Enemies/LaserCannonTwo.png")));
         small.setX(x);
         small.setY(y);

         beam = Utils.LoadAnimation("images/Enemies/LaserBeam-2.png",2,13,25,0.2f);
         KickBackLeft = Utils.LoadAnimation("images/Enemies/ShootingLaserCannon.png",5,5,25,0.2f);
    }
      public void draw(SpriteBatch batch, int x, int y){
     // Cannon.draw(batch, Gdx.graphics.getDeltaTime());
      //small.draw(batch);
      Cannontime += Gdx.graphics.getDeltaTime();
      TextureRegion drawFrame = beam.getKeyFrame(Cannontime, true);
      batch.draw(drawFrame ,x + 97, y + 40);

      KickBackLeftTime += Gdx.graphics.getDeltaTime();
      TextureRegion KickBackLeftFrame = KickBackLeft.getKeyFrame(KickBackLeftTime, true);
      batch.draw(KickBackLeftFrame, x,y);

      }



//1730, 878
//1633,838

}
