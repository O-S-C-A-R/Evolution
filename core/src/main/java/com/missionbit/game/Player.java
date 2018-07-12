package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;




public class Player {
    protected Sprite BlackPlayer;
    private static final float PLAYER_SPEED = 445f;
    protected float jumpvelocity = 0;
    protected float Xvelocity = 0;
    protected boolean touchplatform = true;
    private Vector2 lastposition = new Vector2();
    private static final int GRAVITY = -5;
    private  static int DRAG = 5;
    protected static int Lives = 3;
    long lasthit;
    public void draw(SpriteBatch batch){
        BlackPlayer.draw(batch);
    }





    public Player(){
        lasthit = System.currentTimeMillis();
        BlackPlayer = new Sprite( new Texture(Gdx.files.internal("images/player/BlackPlayer.png")));
        BlackPlayer.setX(90);
        BlackPlayer.setY(59);

    }
    public void Moveleft(){
        BlackPlayer.setX(BlackPlayer.getX()-Gdx.graphics.getDeltaTime() * PLAYER_SPEED);


    }
    public void Moveright(){
        BlackPlayer.setX(BlackPlayer.getX()+Gdx.graphics.getDeltaTime() * PLAYER_SPEED);

    }   public void Jump(){
        jumpvelocity= 195;
        touchplatform = false;



    }
    public void Update(){
        if(Xvelocity != 0) {
            Xvelocity += DRAG;
        }
        if(Lives == 0) {
            Xvelocity = 0;

        }
        if(!touchplatform) {
            jumpvelocity += GRAVITY;

        }

        if(BlackPlayer.getY() < -400){
            BlackPlayer.setX(20);
            BlackPlayer.setY(500);
            System.out.println("One life is gone");
            Lives --;
        }
        BlackPlayer.setY(BlackPlayer.getY()+ jumpvelocity * Gdx.graphics.getDeltaTime());
        BlackPlayer.setX(BlackPlayer.getX()+ Xvelocity * Gdx.graphics.getDeltaTime());
      //  BlackPlayer.setY(BlackPlayer.getY()+ Xvelocity * Gdx.graphics.getDeltaTime());


    }

    public void UpdateLast(){
        lastposition.x = BlackPlayer.getX();
        lastposition.y = BlackPlayer.getY();
    }

    public Rectangle getBounding(){
        return BlackPlayer.getBoundingRectangle();
    }
    public void collide(Platform p)

    {
        if ((p.getTop() > BlackPlayer.getY() && p.getTop() < lastposition.y)) {
            BlackPlayer.setY(p.getTop() - 1);
            jumpvelocity = 0;
            touchplatform = true;

        } else if ((int) BlackPlayer.getX() + (int) BlackPlayer.getWidth() > p.getLeft() && BlackPlayer.getX() < p.getLeft()) {
            BlackPlayer.setX(p.getLeft() - BlackPlayer.getWidth());

        } else if ((int) BlackPlayer.getX() + (int) BlackPlayer.getWidth() > p.getRight() && BlackPlayer.getX() < p.getRight()) {
            BlackPlayer.setX(p.getRight());

        }


    }
    public void Die(){
        BlackPlayer.setX(0);
        BlackPlayer.setY(62);
        Lives -- ;
        System.out.println("One life is gone");
    }
    public void SpiderDie(Enemies Spider){
        if(System.currentTimeMillis() - lasthit > 2000){
            Lives -- ;
            System.out.println("One life is gone");
            lasthit = System.currentTimeMillis();
        }
        CollideWithSpider(Spider);


    }
    public void reset(){
        BlackPlayer.setX(0);
        BlackPlayer.setY(62);
        System.out.println("You Died");
        Lives = 3;
    }
    public void CollideWithSpider(Enemies sp) {
        if ((sp.getTop() > BlackPlayer.getY() && sp.getTop() < lastposition.y)) {
           jumpvelocity = 200;

        } else if ((int) BlackPlayer.getX() + (int) BlackPlayer.getWidth() > sp.getLeft() && BlackPlayer.getX() < sp.getLeft()) {
            Xvelocity = - 300;
            DRAG = 5;


        } else if ((int) BlackPlayer.getX() + (int) BlackPlayer.getWidth() > sp.getRight() && BlackPlayer.getX() < sp.getRight()) {
            Xvelocity =  220;
            DRAG = -5;

        }
    }

}
