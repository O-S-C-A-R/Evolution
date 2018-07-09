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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Random;

public class Evolution extends ApplicationAdapter {

    /* game constants */
    private static final int GRAVITY = -5;
    private static final int CAMERA_OFFSET_X = 350;
    private static final int CAMERA_OFFSET_Y = 150;
    private static final int VIEWPORT_WIDTH = 960;
    private static final int VIEWPORT_HEIGHT = 540;
    private static final float PLAYER_SPEED = 499.999f;

    private OrthographicCamera camera;
    private Random randomSource;
    private Sprite BlackPlayer;
    private Sprite Tutorial;
    private Enemies Spider;
    private float jumpvelocity = 0;

    private SpriteBatch myBatch;
    //private Vector2 velocity;
    private float Speed;
    private boolean showDebug = true;
    private boolean touchplatform = true;
    private Vector2 lastposition = new Vector2();

    private ArrayList<Spikes> spikes = new ArrayList<Spikes>();

    private Buttons LeftButton;
    private Buttons RightButton;
    private Buttons UpButton;
    Vector3 touchPos;



 private boolean platformcheck = false;




    private static final int[][] PLAT_LOCS = new int[][] {
            {0, 0, 1800, 60}, // PLATFORMS
            {390,60,78,28},
            {545,60,84,75},
            {754, 160, 235, 5},
    };
    private static final float[][] spike_locs = new float[][] {
            {765, 60, 870, 135,1010,60}, // SPIKES
    };
    private static ArrayList<Platform> platforms;

    private ShapeRenderer debugRenderer;


    @Override
    public void create() {
        LeftButton = new Buttons(-10, -40, "images/LeftButton.png");
        RightButton = new Buttons(120, -40, "images/RightButton.png");
        UpButton = new Buttons(620, -20, "images/UpButton.png");

        randomSource = new Random();

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        // Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //LOAD IMAGES
        platforms = new ArrayList<Platform>();
        BlackPlayer = new Sprite( new Texture(Gdx.files.internal("images/BlackPlayer.png")));
        Tutorial = new Sprite( new Texture(Gdx.files.internal("images/Tutorial.png")));

        // Initialize platforms
        platforms = new ArrayList<Platform>();

        for (int[] loc : PLAT_LOCS) {
            platforms.add(new Platform(loc[0], loc[1], loc[2], loc[3]));
        }

        for (float[] loc : spike_locs) {
           spikes.add(new Spikes(loc));
        }
        BlackPlayer.setX(70);
        BlackPlayer.setY(59);

       // velocity = new Vector2(0, 0);
        Spider = new Enemies(6, 6);
    }

    @Override
    public void render() {

          platformcheck = false;

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            System.out.println(touchPos);
            camera.unproject(touchPos);
            if(touchPos.x > LeftButton.getX() && touchPos.x < LeftButton.getX() + LeftButton.getWidth())
            {
                if(touchPos.y > LeftButton.getY() && touchPos.y < LeftButton.getY() + LeftButton.getHeight())
                {
                    BlackPlayer.setX(BlackPlayer.getX()-Gdx.graphics.getDeltaTime() * PLAYER_SPEED);
                }
            }
            if(touchPos.x > RightButton.getX() && touchPos.x < RightButton.getX() + RightButton.getWidth())
            {
                if(touchPos.y > RightButton.getY() && touchPos.y < RightButton.getY() + RightButton.getHeight())
                {
                    BlackPlayer.setX(BlackPlayer.getX()+Gdx.graphics.getDeltaTime() * PLAYER_SPEED);
                }
            }
            if(touchPos.x > UpButton.getX() && touchPos.x < UpButton.getX() + UpButton.getWidth())
            {
                if(touchPos.y > UpButton.getY() && touchPos.y < UpButton.getY() + UpButton.getHeight() && touchplatform)
                {
                    jumpvelocity = 195;
                    touchplatform = false;
                }
            }
        }

        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);


        //todo: Draw our image!

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            BlackPlayer.setX(BlackPlayer.getX()-Gdx.graphics.getDeltaTime() * PLAYER_SPEED);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            BlackPlayer.setX(BlackPlayer.getX()+Gdx.graphics.getDeltaTime() * PLAYER_SPEED);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) && touchplatform || Gdx.input.isKeyPressed(Input.Keys.W) && touchplatform) {
           // BlackPlayer.setY(BlackPlayer.getY() + Gdx.graphics.getDeltaTime() * Speed* 100);
            jumpvelocity = 195;
            touchplatform = false;
        }

//        if(Gdx.input.isTouched(touchPos.x = LeftButton.getX()))
//        {
//            if(Gdx.input.isTouched())
//            BlackPlayer.setX(BlackPlayer.getX()-Gdx.graphics.getDeltaTime() * PLAYER_SPEED);
//        }
//
//        if (touchPos.x > LeftButton.getX() && touchPos.x < LeftButton.getX() + LeftButton.getWidth()) {
//            if (touchPos.y > LeftButton.getY() && touchPos.y < LeftButton.getY() + LeftButton.getHeight()) {
//                //clicked on sprite
//                // do something that vanish the object clicked
//            }
//        }

        if(!touchplatform) {
            jumpvelocity += GRAVITY;
        }

        //BlackPlayer.setY(BlackPlayer.getY()+GRAVITY+ jumpvelocity * Gdx.graphics.getDeltaTime());
        BlackPlayer.setY(BlackPlayer.getY()+ jumpvelocity * Gdx.graphics.getDeltaTime());

        //if (!touchplatform) {
            for (Platform p : platforms) {
                if (p.hit(BlackPlayer.getBoundingRectangle())){

                    platformcheck = true;

                   // if((int)lastposition.y > (int)Math.ceil(BlackPlayer.getY())&& p.getTop() > BlackPlayer.getY()&&p.getTop()<lastposition.y){


                     if((p.getTop() > BlackPlayer.getY()&&p.getTop()<lastposition.y)){
                    System.out.println(BlackPlayer.getY());
                        System.out.println(lastposition.y);
                        System.out.println("platform"+p.getTop());
                        BlackPlayer.setY(p.getTop() - 1);
                        touchplatform = true;
                        jumpvelocity = 0;

                    }
                    else if ( (int)BlackPlayer.getX()+(int)BlackPlayer.getWidth() > p.getLeft() && BlackPlayer.getX()< p.getLeft()){
                        BlackPlayer.setX(p.getLeft()-BlackPlayer.getWidth()) ;

                    }

                    else if ( (int)BlackPlayer.getX()+(int)BlackPlayer.getWidth() > p.getRight() && BlackPlayer.getX()< p.getRight()){
                        BlackPlayer.setX(p.getRight()) ;

                    }

                }
            }



    // CAMERA AND PLAYER DRAWING
        camera.position.set(BlackPlayer.getX() + CAMERA_OFFSET_X, BlackPlayer.getY() + CAMERA_OFFSET_Y, 0);
        camera.update();
        myBatch.begin();
        Tutorial.draw(myBatch);
        myBatch.end();

        myBatch.begin();
        BlackPlayer.draw(myBatch);
        Spider.draw(myBatch);
        LeftButton.draw(myBatch);
        RightButton.draw(myBatch);
        UpButton.draw(myBatch);
        myBatch.end();
        lastposition.x = BlackPlayer.getX();
        lastposition.y = BlackPlayer.getY();

        if(showDebug){
            debugRenderer.setProjectionMatrix(camera.combined);
            debugRenderer.begin(ShapeRenderer.ShapeType.Line);
            debugRenderer.setColor(0, 1, 0, 1);
            for (Platform p: platforms){p.drawDebug(debugRenderer);
            }
            for(Spikes s: spikes) {
                s.drawDebug(debugRenderer);
            }
            debugRenderer.rect(BlackPlayer.getX(), BlackPlayer.getY(), BlackPlayer.getWidth(), BlackPlayer.getHeight());
            debugRenderer.end();
        }
        //       System.out.println(platformcheck);
        if(!platformcheck && touchplatform){
            touchplatform = false;
        }
        System.out.println(BlackPlayer.getY());
        System.out.println(BlackPlayer.getX());
//        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
//        camera.update();

//        camera.position.set(LeftButton.getX() + CAMERA_OFFSET_X, LeftButton.getY() + CAMERA_OFFSET_Y, 0);
//        camera.update();

//          camera.position.set(CAMERA_OFFSET_X, CAMERA_OFFSET_Y, 0);
//          camera.update();
//
//          myBatch.setProjectionMatrix(camera.combined);


        //          LeftButton.draw(myBatch);
//          RightButton.draw(myBatch);

    }




    @Override
    public void dispose() {
        myBatch.dispose();
    }
}