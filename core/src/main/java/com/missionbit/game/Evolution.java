package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

public class Evolution extends ApplicationAdapter {

    /* game constants */
    private static final int CAMERA_OFFSET_X = 350;
    private static final int CAMERA_OFFSET_Y = 150;
    private static final int VIEWPORT_WIDTH = 960;
    private static final int VIEWPORT_HEIGHT = 540;
    private Player BlackPlayer;
    private OrthographicCamera camera;
    private Random randomSource;
    private Sprite Tutorial;
    private Sprite Bouncepad;


    private Buttons Fade;
    private Buttons FullLives;

    private Enemies Spider;
    private SpriteBatch myBatch;
    //private Vector2 velocity;
    private float Speed;
    private boolean showDebug = true;
    private BitmapFont bodyFont;

    private ArrayList<Spikes> spikes = new ArrayList<Spikes>();


    private Buttons LeftButton;
    private Buttons RightButton;
    private Buttons UpButton;
    Vector3 touchPos;


    private boolean platformcheck = false;


    private static final int[][] PLAT_LOCS = new int[][]{
            {0, 0, 1800, 60}, // PLATFORMS
            {390, 60, 78, 28},
            {545, 60, 84, 75},
            {754, 160, 235, 5},
    };
    private static final float[][] spike_locs = new float[][]{
            {745, 59, 880, 115, 1020, 59},// SPIKES
            {1359, 61, 1600, 235, 1800, 235, 1800, 61},
    };
    private static ArrayList<Platform> platforms;

    private ShapeRenderer debugRenderer;


    @Override
    public void create() {
        BlackPlayer = new Player();

        LeftButton = new Buttons(-70, -100, "images/ui/LeftButton.png");
        RightButton = new Buttons(30, -100, "images/ui/RightButton.png");
        UpButton = new Buttons(690, -100, "images/ui/UpButton.png");
        FullLives = new Buttons(-140, 350, "images/ui/FullLives.png");
        //Fade = new Buttons(-140,-120 ,"images/Fade.png");




        randomSource = new Random();
        // TODO Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        //TODO Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //LOAD IMAGES
        BlackPlayer = new Player(new Texture(Gdx.files.internal("images/player/BlackPlayer.png")));
        Bouncepad = new Sprite(new Texture(Gdx.files.internal("images/Enemies/BouncePad.png")));
        Tutorial = new Sprite(new Texture(Gdx.files.internal("images/map/Tutorial.png")));
        // Initialize platforms
        platforms = new ArrayList<Platform>();

        for (int[] loc : PLAT_LOCS) {
            platforms.add(new Platform(loc[0], loc[1], loc[2], loc[3]));
        }

        for (float[] loc : spike_locs) {
            spikes.add(new Spikes(loc));
        }


        // velocity = new Vector2(0, 0);
        Spider = new Enemies(960, 160, 766, 962);
    }

    @Override
    public void render() {
        System.out.println(BlackPlayer.getBounding().getY());
        System.out.println(BlackPlayer.getBounding().getX());
        platformcheck = false;
        for (int i = 0; i < 10; i++) {
            if (Gdx.input.isTouched(i)) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touchPos);


                System.out.println(touchPos);
                if (touchPos.x > LeftButton.getX() && touchPos.x < LeftButton.getX() + LeftButton.getWidth()) {
                    if (touchPos.y > LeftButton.getY() && touchPos.y < LeftButton.getY() + LeftButton.getHeight()) {
                        BlackPlayer.Moveleft();
                    }

                }
                if (touchPos.x > RightButton.getX() && touchPos.x < RightButton.getX() + RightButton.getWidth()) {
                    if (touchPos.y > RightButton.getY() && touchPos.y < RightButton.getY() + RightButton.getHeight()) {
                        BlackPlayer.Moveright();
                    }
                }
                if (touchPos.x > UpButton.getX() && touchPos.x < UpButton.getX() + UpButton.getWidth()) {
                    if (touchPos.y > UpButton.getY() && touchPos.y < UpButton.getY() + UpButton.getHeight() && BlackPlayer.touchplatform) {
                        BlackPlayer.Jump();
                    }
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

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            BlackPlayer.Moveleft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            BlackPlayer.Moveright();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) && BlackPlayer.touchplatform || Gdx.input.isKeyPressed(Input.Keys.W) && BlackPlayer.touchplatform) {

            BlackPlayer.Jump();
        }


        BlackPlayer.Update();
        for (Platform p : platforms) {
            if (p.hit(BlackPlayer)) {
                platformcheck = true;
                BlackPlayer.collide(p);
                BlackPlayer.Update();
            }
        }
        for (Spikes s : spikes) {
            if (s.CollideWithPlayer(BlackPlayer)) {
                BlackPlayer.Die();
                if (s.CollideWithPlayer(BlackPlayer)) {
                    BlackPlayer.Die();


                }
            }
        }
        if (BlackPlayer.Lives == 0) {
            BlackPlayer.reset();
            if (BlackPlayer.Lives == 0) {
                BlackPlayer.reset();


            }


            // CAMERA AND PLAYER DRAWING
            camera.position.set(BlackPlayer.getBounding().getX() + CAMERA_OFFSET_X, BlackPlayer.getBounding().getY() + CAMERA_OFFSET_Y, 0);
            camera.update();
            myBatch.setProjectionMatrix(camera.combined);
            myBatch.begin();
            Bouncepad.draw(myBatch);
            Tutorial.draw(myBatch);
            myBatch.end();

            myBatch.begin();
            BlackPlayer.draw(myBatch);
            Spider.draw(myBatch);

            //bodyFont.draw(myBatch,"Lives left", 900,500 );

//        bodyFont.draw(myBatch,"Lives left", 900,500 );

//        bodyFont.draw(myBatch,"Lives left", 900,500 );

            myBatch.end();


            if (showDebug) {
                debugRenderer.setProjectionMatrix(camera.combined);
                debugRenderer.begin(ShapeRenderer.ShapeType.Line);
                debugRenderer.setColor(0, 1, 0, 1);
                for (Platform p : platforms) {
                    p.drawDebug(debugRenderer);
                }
                for (Spikes s : spikes) {
                    s.drawDebug(debugRenderer);
                }
                debugRenderer.rect(BlackPlayer.getBounding().getX(), BlackPlayer.getBounding().getY(), BlackPlayer.getBounding().getWidth(), BlackPlayer.getBounding().getHeight());
                debugRenderer.end();
            }
            if (!platformcheck && BlackPlayer.touchplatform) {
                BlackPlayer.touchplatform = false;
            }


            BlackPlayer.UpdateLast();

            camera.position.set(CAMERA_OFFSET_X, CAMERA_OFFSET_Y, 0);
            camera.update();
            myBatch.setProjectionMatrix(camera.combined);
            myBatch.begin();
            LeftButton.draw(myBatch);
            RightButton.draw(myBatch);
            UpButton.draw(myBatch);


            FullLives.draw(myBatch);

            myBatch.end();
            Bouncepad.setX(1260);
            Bouncepad.setY(59);
        }
    }


    @Override
    public void dispose() {
        myBatch.dispose();

    }
}