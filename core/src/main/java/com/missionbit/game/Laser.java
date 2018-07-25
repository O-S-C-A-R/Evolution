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

    public Laser(float x, float y){


        Cannon = new ParticleEffect();
        Cannon.load(Gdx.files.internal("images/particle effects/Laser"), Gdx.files.internal("images/particle effects"));
        Cannon.setPosition(x, y);
        Cannon.start();

        pos = new Vector2();
        pos.x = x;
        pos.y = y;

         beam = Utils.LoadAnimation("images/Enemies/LaserBeam-2.png",2,13,25,0.2f);
         KickBackLeft = Utils.LoadAnimation("images/Enemies/ShootingLaserCannon.png",5,5,25,0.2f);
    }
      public void draw(SpriteBatch batch){
     // Cannon.draw(batch, Gdx.graphics.getDeltaTime());
      //small.draw(batch);

      KickBackLeftTime += Gdx.graphics.getDeltaTime();
      TextureRegion KickBackLeftFrame = KickBackLeft.getKeyFrame(KickBackLeftTime, true);
      batch.draw(KickBackLeftFrame, pos.x, pos.y);

      Cannontime += Gdx.graphics.getDeltaTime();
      TextureRegion drawFrame = beam.getKeyFrame(Cannontime, true);
      batch.draw(drawFrame ,pos.x + 97, pos.y + 40);

      }

    public boolean collide (Player idk){
        boolean flag = getBounding().overlaps(idk.getBounding());
        return flag ;
    }

    public Rectangle getBounding() {
        TextureRegion texture = beam.getKeyFrame(Cannontime, true);
        return new Rectangle(pos.x+97,pos.y+40,texture.getRegionWidth(),texture.getRegionHeight());
    }

    public void drawDebug(ShapeRenderer renderer){
        Rectangle rect = getBounding();
        renderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
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
