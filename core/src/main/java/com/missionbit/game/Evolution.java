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
    private static final float PLAYER_SPEED = 550.0f;

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
    private boolean touchplatform = true;
    private Vector2 lastposition = new Vector2();

    private Buttons LeftButton;
    private Buttons RightButton;



 private boolean platformcheck = false;




    private static final int[][] PLAT_LOCS = new int[][] {
            {0, 0, 1500, 60}, // floor
            {390,60,78,28},
            {545,60,84,75},
            {754, 160, 235, 5},
    };
    private static ArrayList<Platform> platforms;

    private ShapeRenderer debugRenderer;


    @Override
    public void create() {
        LeftButton = new Buttons(-10, -40, "images/LeftButton.png");
        RightButton = new Buttons(40, -40, "images/RightButton.png");

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

        // Initialize platforms
        platforms = new ArrayList<Platform>();

        for (int[] loc : PLAT_LOCS) {
            platforms.add(new Platform(loc[0], loc[1], loc[2], loc[3]));
        }

        BlackPlayer.setX(70);
        BlackPlayer.setY(60);

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
            jumpvelocity = 180;
            touchplatform = false;
        }

        if(!touchplatform) {
            jumpvelocity += GRAVITY;
        }

        //BlackPlayer.setY(BlackPlayer.getY()+GRAVITY+ jumpvelocity * Gdx.graphics.getDeltaTime());
        BlackPlayer.setY(BlackPlayer.getY()+ jumpvelocity * Gdx.graphics.getDeltaTime());

        //if (!touchplatform) {
            for (Platform p : platforms) {
                if (p.hit(BlackPlayer.getBoundingRectangle())){
                    System.out.println(BlackPlayer.getX()+" "+ (int)BlackPlayer.getY());
                    System.out.println((int)lastposition.y);

                    platformcheck = true;

                    if((int)lastposition.y > (int)Math.ceil(BlackPlayer.getY())){
                        BlackPlayer.setY(p.getTop() - 1);
                        touchplatform = true;
                        jumpvelocity = 0;

                    }
                    else if ( (int)BlackPlayer.getX()+(int)BlackPlayer.getWidth() > p.getLeft() && BlackPlayer.getX()< p.getLeft()){
                        BlackPlayer.setX(p.getLeft()-BlackPlayer.getWidth()) ;

                    }

                    if((int)lastposition.y > (int)Math.ceil(BlackPlayer.getY())){
                        BlackPlayer.setY(p.getTop() - 1);
                        touchplatform = true;
                        jumpvelocity = 0;

                    }
                    else if ( (int)BlackPlayer.getX()+(int)BlackPlayer.getWidth() > p.getRight() && BlackPlayer.getX()< p.getRight()){
                        BlackPlayer.setX(p.getRight()) ;

                    }

                }
            }




        camera.position.set(BlackPlayer.getX() + CAMERA_OFFSET_X, BlackPlayer.getY() + CAMERA_OFFSET_Y, 0);
        camera.update();

//        Buttons.position.set(BlackPlayer.getX() + CAMERA_OFFSET_X, BlackPlayer.getY() + CAMERA_OFFSET_Y, 0);
//        Button.update();

        myBatch.begin();
        Tutorial.draw(myBatch);
        LeftButton.draw(myBatch);
        myBatch.end();

        myBatch.begin();
       // myBatch.draw(BlackPlayer,(int)velocity.x,(int)velocity.y);
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
            debugRenderer.rect(BlackPlayer.getX(), BlackPlayer.getY(), BlackPlayer.getWidth(), BlackPlayer.getHeight());
            debugRenderer.end();
        }
        //       System.out.println(platformcheck);
        if(!platformcheck && touchplatform){
            touchplatform = false;
        }

    }




    @Override
    public void dispose() {
        myBatch.dispose();
    }
}