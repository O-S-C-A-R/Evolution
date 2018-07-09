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
    private int Lives = 3;


    private ArrayList<Spikes> spikes = new ArrayList<Spikes>();


    private Buttons LeftButton;
    private Buttons RightButton;
    Vector3 touchPos;


    private boolean platformcheck = false;


    private static final int[][] PLAT_LOCS = new int[][] {
            {0, 0, 1800, 60}, // PLATFORMS
            {390,60,78,28},
            {545,60,84,75},
            {754, 160, 235, 5},
    };
    private static final float[][] spike_locs = new float[][] {
            {745, 59, 880, 115,1020,59}, // SPIKES
    };
    private static ArrayList<Platform> platforms;

    private ShapeRenderer debugRenderer;


    @Override
    public void create() {
        LeftButton = new Buttons(-10, -40, "images/LeftButton.png");
        RightButton = new Buttons(120, -40, "images/RightButton.png");

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
        }

        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);


        //todo: Draw our image!

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)/* || Gdx.input.isKeyJustPressed(LeftButton)*/) {
            BlackPlayer.setX(BlackPlayer.getX()-Gdx.graphics.getDeltaTime() * PLAYER_SPEED);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            BlackPlayer.setX(BlackPlayer.getX()+Gdx.graphics.getDeltaTime() * PLAYER_SPEED);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) && touchplatform || Gdx.input.isKeyPressed(Input.Keys.W) && touchplatform) {
           // BlackPlayer.setY(BlackPlayer.getY() + Gdx.graphics.getDeltaTime() * Speed* 100);
            jumpvelocity = 295;
            touchplatform = false;
        }


        if(!touchplatform) {
            jumpvelocity += GRAVITY;
        }

        BlackPlayer.setY(BlackPlayer.getY()+ jumpvelocity * Gdx.graphics.getDeltaTime());
        for (Platform p : platforms) {
                if (p.hit(BlackPlayer.getBoundingRectangle())){

                    platformcheck = true;



                     if((p.getTop() > BlackPlayer.getY()&&p.getTop()<lastposition.y)){
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
        for (Spikes s : spikes) {
            if (s.CollideWithPlayer(BlackPlayer.getBoundingRectangle())) {
                BlackPlayer.setX(0);
                BlackPlayer.setY(62);
                Lives =- 1;
                System.out.println("One life is gone");


            }
        }
        if(Lives == 0){
            BlackPlayer.setX(0);
            BlackPlayer.setY(62);
            System.out.println("You Died");
            Lives = 3;


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
        if(!platformcheck && touchplatform){
            touchplatform = false;
        }


    }




    @Override
    public void dispose() {
        myBatch.dispose();
    }
}