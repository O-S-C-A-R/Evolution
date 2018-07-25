package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

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
    private Vector2 pos;
    private long LaserTime;
    private boolean LaserOn = true;
    private boolean CannonDirection = true;

    public Laser(float x, float y, boolean Direction){
        CannonDirection = Direction;
        Cannon = new ParticleEffect();
        Cannon.load(Gdx.files.internal("images/particle effects/Laser"), Gdx.files.internal("images/particle effects"));
        Cannon.setPosition(x, y);
        Cannon.start();

        pos = new Vector2();
        pos.x = x;
        pos.y = y;



         if(CannonDirection == true)
         {
             KickBackLeft = Utils.LoadAnimation("images/Enemies/ShootingLaserCannon.png",5,5,24,0.2f);
             beam = Utils.LoadAnimation("images/Enemies/LaserBeam-2.png",2,13,15,0.2f);
         }
         else
         {
             KickBackLeft = Utils.LoadAnimation("images/Enemies/SideLaser.png",5,5,24,0.2f);
             beam = Utils.LoadAnimation("images/Enemies/LaserBeam-2.png",2,13,15,0.2f);
         }
         LaserTime = System.currentTimeMillis();
    }
      public void draw(SpriteBatch batch){
     // Cannon.draw(batch, Gdx.graphics.getDeltaTime());
      //small.draw(batch);


      TextureRegion KickBackLeftFrame = KickBackLeft.getKeyFrame(KickBackLeftTime, true);
      batch.draw(KickBackLeftFrame, pos.x, pos.y);
      System.out.println("Laser " + LaserOn + " " + Cannontime + " " + (System.currentTimeMillis() - LaserTime));
      if (LaserOn)
      {
          KickBackLeftTime += Gdx.graphics.getDeltaTime();
          Cannontime += Gdx.graphics.getDeltaTime();
          TextureRegion drawFrame = beam.getKeyFrame(Cannontime, false);
          if(CannonDirection == true)
          {
              batch.draw(drawFrame ,pos.x + 97, pos.y + 40);
          }

          else
          {
              batch.draw(drawFrame ,pos.x - 347, pos.y + 55);
          }
      }

      if(beam.isAnimationFinished(Cannontime) && LaserOn == true)
      {
          LaserOn = false;
          LaserTime = System.currentTimeMillis();
      }
      if (System.currentTimeMillis() - LaserTime > 3000 && LaserOn == false)
      {
          LaserOn = true;
          Cannontime = 0;
          KickBackLeftTime = 0;

      }
      }

    public boolean collide (Player idk){
        if(LaserOn == false)
        {
            return false;
        }
        boolean flag = getBounding().overlaps(idk.getBounding());
        return flag ;
    }

    public Rectangle getBounding() {
        TextureRegion texture = beam.getKeyFrame(Cannontime, true);
        if(CannonDirection == true)
        {
            return new Rectangle(pos.x + 97, pos.y + 40, texture.getRegionWidth(), texture.getRegionHeight());
        }
        else
        {
            return new Rectangle(pos.x + -347, pos.y + 55, texture.getRegionWidth(), texture.getRegionHeight());
        }
    }

    public void drawDebug(ShapeRenderer renderer){
        if(LaserOn == true)
        {
            Rectangle rect = getBounding();
            renderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        }
    }

    public float getTop(){
        return getBounding().getY()+getBounding().height;
    }
    public float getLeft(){
        return getBounding().getX();
    }
    public float getRight(){
        return getBounding().getX()+getBounding().width;
    }


//1730, 878
//1633,838

}
