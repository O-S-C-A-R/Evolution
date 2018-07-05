package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

public class Evolution extends ApplicationAdapter {

    /* game constants */
    private static final int GRAVITY = -5;
    private static final int CAMERA_OFFSET_X = 350;
    private static final int CAMERA_OFFSET_Y = 150;
    private static final int VIEWPORT_WIDTH = 960;
    private static final int VIEWPORT_HEIGHT = 540;
    private static final int FLOOR_WIDTH = 1500;
    private static final int FLOOR_HEIGHT = 60;
    private static final float PLAYER_SPEED = 700.0f;

    private OrthographicCamera camera;
    private Random randomSource;
    private Sprite BlackPlayer;
    private Sprite Tutorial;
    private Enemies Spider;
    private float jumpvelocity = 0;

    private SpriteBatch myBatch;
    //private Vector2 velocity;
    private float Speed;
    private boolean showDebug =true;
    private Platform floor;
    private Platform platform1;
    private Platform platform2;
    private Platform platform3;
    private boolean touchplatform = true;
    private ArrayList<Platform>platforms;


    private ShapeRenderer debugRenderer;


    @Override
    public void create() {
        randomSource = new Random();

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        // Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //TODO: Load our image
        platforms = new ArrayList<Platform>();
        BlackPlayer = new Sprite( new Texture(Gdx.files.internal("images/BlackPlayer.png")));
        Tutorial = new Sprite( new Texture(Gdx.files.internal("images/Tutorial.png")));
        platform1 = new Platform(390,60,78,28);
        platform2 = new Platform(545,60,88,75);
        platform3 = new Platform(747,200,200,5);


        floor = new Platform(0,0, FLOOR_WIDTH, FLOOR_HEIGHT);


        BlackPlayer.setX(0);
        BlackPlayer.setY(0);

       // velocity = new Vector2(0, 0);
        Spider = new Enemies(6, 6);
    }

    @Override
    public void render() {

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            System.out.println(touchPos);
        }
        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);

        //todo: Draw our image!

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            BlackPlayer.setX(BlackPlayer.getX()-Gdx.graphics.getDeltaTime() * PLAYER_SPEED);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            BlackPlayer.setX(BlackPlayer.getX()+Gdx.graphics.getDeltaTime() * PLAYER_SPEED);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) && touchplatform == true) {
           // BlackPlayer.setY(BlackPlayer.getY() + Gdx.graphics.getDeltaTime() * Speed* 100);
            jumpvelocity = 180;
            touchplatform = false;
        }
        jumpvelocity += GRAVITY;
            //BlackPlayer.setY(BlackPlayer.getY()+GRAVITY+ jumpvelocity * Gdx.graphics.getDeltaTime());
        BlackPlayer.setY(BlackPlayer.getY()+ jumpvelocity * Gdx.graphics.getDeltaTime());


        if (floor.hit(BlackPlayer.getBoundingRectangle())){
           // velocity.y=floor.getTop();
            BlackPlayer.setY(floor.getTop());
            //System.out.println("hit "+floor.getTop() + " " + BlackPlayer.getY());

            touchplatform = true;
            jumpvelocity = 0;

        }
        if (platform1.hit(BlackPlayer.getBoundingRectangle())){
            // velocity.y=floor.getTop();
            //BlackPlayer.setY(platform1.getTop());

            //
//            if(BlackPlayer.getX()==platform1(){
//                BlackPlayer.setY(platform1.getTop());
//            }

            //System.out.println("hit "+floor.getTop() + " " + BlackPlayer.getY());

            touchplatform = true;
            jumpvelocity = 0;

        }
        if (platform2.hit(BlackPlayer.getBoundingRectangle())){
            touchplatform = true;
            jumpvelocity = 0;
        }
        if (platform3.hit(BlackPlayer.getBoundingRectangle())){
            touchplatform = true;
            jumpvelocity = 0;
        }

        camera.position.set(BlackPlayer.getX() + CAMERA_OFFSET_X, BlackPlayer.getY() + CAMERA_OFFSET_Y, 0);
        camera.update();




        myBatch.begin();
        Tutorial.draw(myBatch);
        myBatch.end();

        myBatch.begin();
       // myBatch.draw(BlackPlayer,(int)velocity.x,(int)velocity.y);
        BlackPlayer.draw(myBatch);
       Spider.draw(myBatch);
        myBatch.end();


        if(showDebug){
            debugRenderer.setProjectionMatrix(camera.combined);
            debugRenderer.begin(ShapeRenderer.ShapeType.Line);
            debugRenderer.setColor(0, 1, 0, 1);
            floor.drawDebug(debugRenderer);
           platform1.drawDebug(debugRenderer);
            platform2.drawDebug(debugRenderer);
            platform3.drawDebug(debugRenderer);
            debugRenderer.rect(BlackPlayer.getX(), BlackPlayer.getY(), BlackPlayer.getWidth(), BlackPlayer.getHeight());
            debugRenderer.end();
        }
//        if(showDebug){
//            debugRenderer.setProjectionMatrix(camera.combined);
//            debugRenderer.begin(ShapeRenderer.ShapeType.Line);
//            debugRenderer.setColor(1, 0, 0, 1);
//            platform1.drawDebug(debugRenderer);
//            debugRenderer.rect(BlackPlayer.getX(), BlackPlayer.getY(), BlackPlayer.getWidth(), BlackPlayer.getHeight());
//            debugRenderer.end();
//       }
        System.out.println(BlackPlayer.getX()+" "+ BlackPlayer.getY());
    }


    @Override
    public void dispose() {
        myBatch.dispose();
    }
}