package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.missionbit.game.States.GameStateManager;
import com.missionbit.game.States.LevelOne;


public class Player {
    protected Sprite BlackPlayer;
    private static final float PLAYER_SPEED = 445f;
    public float SuperJumpVelocity = 0;
    public float jumpvelocity = 0;
    public float Xvelocity = 0;
    public boolean touchplatform = true;
    private Vector2 lastposition = new Vector2();
    private static final int GRAVITY = -15;
    public static int DRAG = 5;
    public static int Lives = 3;
    private Animation<TextureRegion> DeathAnimation;
    public float maxjump = 405;
    public float UltimateJump = 1000;
    long lasthit;
   // float playertop;
    float DeathAnimationTime = 0;

    public void draw(SpriteBatch batch){
        if(Lives == 0)
        {
            DeathAnimationTime += Gdx.graphics.getDeltaTime();
            TextureRegion drawFrame = DeathAnimation.getKeyFrame(DeathAnimationTime, false);
            batch.draw(drawFrame, BlackPlayer.getX() -50, BlackPlayer.getY() -50);
        }
        else
        {
            BlackPlayer.draw(batch);
        }
    }


    public Player(int x, int y){
        //playertop =BlackPlayer.getY()+BlackPlayer.getHeight();
        lasthit = System.currentTimeMillis();
        BlackPlayer = new Sprite( new Texture(Gdx.files.internal("images/player/BlackPlayer.png")));
//        if(GameStateManager == LevelOne){
//            BlackPlayer = new Sprite( new Texture(Gdx.files.internal("images/player/BluePlayer.png")));
//            DeathAnimation = Utils.LoadAnimation("images/player animation/BluePlayerDeath.png", 3, 4, 8, 0.05f);
//
//        }

        BlackPlayer.setX(x);
        BlackPlayer.setY(y);
        DeathAnimation = Utils.LoadAnimation("images/player animation/TutorialPlayerDeath.png", 3, 4, 8, 0.05f);
    }
    public void Moveleft(){
        BlackPlayer.setX(BlackPlayer.getX()-Gdx.graphics.getDeltaTime() * PLAYER_SPEED);



    }
    public void Moveright(){
        BlackPlayer.setX(BlackPlayer.getX()+Gdx.graphics.getDeltaTime() * PLAYER_SPEED);

    }   public void Jump(){
        jumpvelocity= maxjump;
        touchplatform = false;

    }

    public void SuperJump()
    {
        SuperJumpVelocity = UltimateJump;
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
        if(!touchplatform)
        {
            SuperJumpVelocity += GRAVITY;
        }
        BlackPlayer.setY(BlackPlayer.getY()+ jumpvelocity * Gdx.graphics.getDeltaTime());
        BlackPlayer.setX(BlackPlayer.getX()+ Xvelocity * Gdx.graphics.getDeltaTime());
    }
    public void tutorialupdate(){
        if(BlackPlayer.getY() < -400){
            BlackPlayer.setX(20);
            BlackPlayer.setY(500);
            System.out.println("One life is gone");
            Lives --; }
    } public void tutorial2update(){
        if(BlackPlayer.getY() < -400){
            BlackPlayer.setX(100);
            BlackPlayer.setY(500);
            System.out.println("One life is gone");
            Lives --; }
    }

    public void UpdateLast(){
        lastposition.x = BlackPlayer.getX();
        lastposition.y = BlackPlayer.getY();
    }

    public Rectangle getBounding(){
        return BlackPlayer.getBoundingRectangle();
    }
    public void collide(Platform p)

    { if(touchplatform == true)
        jumpvelocity= 0;

        if ((p.getTop() > BlackPlayer.getY() && p.getTop() < lastposition.y)) {
            BlackPlayer.setY(p.getTop() - 1);
            jumpvelocity = 0;
            SuperJumpVelocity = 0;
            touchplatform = true;

        } else if ((int) BlackPlayer.getX() + (int) BlackPlayer.getWidth() > p.getLeft() && BlackPlayer.getX() < p.getLeft()) {
            BlackPlayer.setX(p.getLeft() - BlackPlayer.getWidth());
        } else if ((int) BlackPlayer.getX() + (int) BlackPlayer.getWidth() > p.getRight() && BlackPlayer.getX() < p.getRight()) {
            BlackPlayer.setX(p.getRight());
        }
        else if ((p.getBottom() <= BlackPlayer.getY() + BlackPlayer.getHeight() && p.getBottom() > lastposition.y)) {
//            System.out.println("hyvuhdjid");
            jumpvelocity = 0;
            BlackPlayer.setY(p.getBottom()-BlackPlayer.getHeight()-1);
            SuperJumpVelocity = 0;
        }


    }
    public void SpikeDie() {
        if (System.currentTimeMillis() - lasthit > 500) {
            Lives--;
            System.out.println(DRAG +" "+ Spikes.collideside);
            if(Spikes.collideside == 1) {
                Xvelocity = 190;
                DRAG = -5;
            }
            if(Spikes.collideside == 2) {
                Xvelocity = -190;
                DRAG = 5;

            }

            System.out.println("One life is gone");
            System.out.println(lastposition.x +" "+ BlackPlayer.getX());

            lasthit = System.currentTimeMillis();

        }
    }
    public void SpiderDie(Enemies Spider){
        if(System.currentTimeMillis() - lasthit > 500){
            Lives -- ;
            System.out.println("One life is gone");
            lasthit = System.currentTimeMillis();

        }

        CollideWithSpider(Spider);


    }
    public void reset(float X, float Y){

        BlackPlayer.setX(X);
        BlackPlayer.setY(Y);
        System.out.println("You Died");
        Lives = 3;
        DeathAnimationTime = 0;
        Xvelocity = 0;
    }

    public void CollideWithSpider(Enemies sp) {
        if ((sp.getTop() > BlackPlayer.getY() && sp.getTop() < lastposition.y)) {
           jumpvelocity = 220;

        } else if ((int) BlackPlayer.getX() + (int) BlackPlayer.getWidth() > sp.getLeft() && BlackPlayer.getX() < sp.getLeft()) {
            Xvelocity = - 220;
            DRAG = 5;


        } else if ((int) BlackPlayer.getX() + (int) BlackPlayer.getWidth() > sp.getRight() && BlackPlayer.getX() < sp.getRight()) {
            Xvelocity =  220;
            DRAG = -5;

        }


    }
    public boolean deathanimationfin(){
        return DeathAnimation.isAnimationFinished(DeathAnimationTime);

    }

//    public  void setTop(float top){
//        playertop = top;
//    }


}
