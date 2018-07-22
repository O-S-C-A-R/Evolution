package com.missionbit.game.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.Bouncepad;
import com.missionbit.game.Buttons;
import com.missionbit.game.Enemies;
import com.missionbit.game.Particles;
import com.missionbit.game.Platform;
import com.missionbit.game.Player;
import com.missionbit.game.Rumble;
import com.missionbit.game.Spikes;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

public class TutorialState extends Levelmaker{

    /* game constants */
    private static final int CAMERA_OFFSET_X = 350;
    private static final int CAMERA_OFFSET_Y = 150;
    private static final int VIEWPORT_WIDTH = 960;
    private static final int VIEWPORT_HEIGHT = 540;
    private Particles Portal;
    private OrthographicCamera camera;

    private Player blackplayer;
    private Random randomSource;
    private Sprite Tutorial;
    private Sound JumpSound;
    private Sprite Bouncepad;
    private Music music;

    private com.missionbit.game.Bouncepad Pad;
    private Rumble rumble;
    private Texture RestartScreen;

    private Buttons Fade;
    private Buttons FullLives;
    private Buttons TwoLives;
    private Buttons OneLife;
    private Color tooclose;


    private Enemies Spider;
    private SpriteBatch myBatch;
    //private Vector2 velocity;
    private float Speed;
    private boolean showDebug = false;


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
            {755, 59, 880, 125, 1000, 59},// SPIKES
            {1359, 61,1450,100,1600, 235, 1800, 235, 1800, 61},
    };
    private static ArrayList<Platform> platforms;

    private ShapeRenderer debugRenderer;

    public TutorialState(GameStateManager gsm)
    {
        super(gsm);
        music = Gdx.audio.newMusic(Gdx.files.internal("music/Howling-wind.mp3"));
        music.setLooping(true);
        music.setVolume(0.4f);
        music.play();
        JumpSound = Gdx.audio.newSound(Gdx.files.internal("music/Swooshing.mp3"));
        JumpSound.setLooping(1,false);
        JumpSound.setVolume(1, 0.01f);
        tooclose = new Color(1,1,1,1);

        blackplayer = new Player(90,59);
        Pad = new Bouncepad(1260,59);

        LeftButton = new Buttons(-70, -100, "images/ui/LeftButton.png");
        RightButton = new Buttons(30, -100, "images/ui/RightButton.png");
        UpButton = new Buttons(690, -100, "images/ui/UpButton.png");
        //Fade = new Buttons(-140,-120 ,"images/Fade.png");

        FullLives = new Buttons(-140, 350, "images/ui/FullLives.png");
        TwoLives = new Buttons(-140, 350, "images/ui/TwoLives.png");
        OneLife = new Buttons(-140, 350, "images/ui/OneLife.png");

        randomSource = new Random();
        // TODO Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        Portal = new Particles(1750, 295);
        //TODO Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //LOAD IMAGES
        platforms = new ArrayList<Platform>();

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

    protected void handleInput()
    {
        for (int i = 0; i < 10; i++) {
            if (Gdx.input.isTouched(i)) {
                touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touchPos);
                System.out.println(touchPos);
                if (touchPos.x > LeftButton.getX() && touchPos.x < LeftButton.getX() + LeftButton.getWidth()) {
                    if (touchPos.y > LeftButton.getY() && touchPos.y < LeftButton.getY() + LeftButton.getHeight()) {
                        blackplayer.Moveleft();
                    }

                }
                if (touchPos.x > RightButton.getX() && touchPos.x < RightButton.getX() + RightButton.getWidth()) {
                    if (touchPos.y > RightButton.getY() && touchPos.y < RightButton.getY() + RightButton.getHeight()) {
                        blackplayer.Moveright();
                    }
                }
                if (touchPos.x > UpButton.getX() && touchPos.x < UpButton.getX() + UpButton.getWidth()) {
                    if (touchPos.y > UpButton.getY() && touchPos.y < UpButton.getY() + UpButton.getHeight() && blackplayer.touchplatform) {
                        blackplayer.Jump();
                        JumpSound.play();
                    }
                }

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            blackplayer.Moveleft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            blackplayer.Moveright();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) && blackplayer.touchplatform || Gdx.input.isKeyPressed(Input.Keys.W) && blackplayer.touchplatform) {

            blackplayer.Jump();
            JumpSound.play();
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
        camera.update();
        blackplayer.Update();

    }
    @Override
    public void render(SpriteBatch myBatch) {
blackplayer.tutorialupdate();
        // Clear the screen
        Gdx.gl.glClearColor(tooclose.r, tooclose.g,tooclose.b, tooclose.a );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        myBatch.setProjectionMatrix(camera.combined);

        if(Pad.bounce(blackplayer)){
            blackplayer.jumpvelocity = 675;

        }
        platformcheck = false;

        for (Platform p : platforms) {
            if (p.hit(blackplayer)) {
                platformcheck = true;
                blackplayer.collide(p);

            }
        }
        for (Spikes s : spikes) {
            if (s.CollideWithPlayer(blackplayer)) {
                blackplayer.SpikeDie();

            }
        }

        if (Spider.spidercollide(blackplayer)) {
            blackplayer.SpiderDie(Spider);

        }
        if(Portal.hit(blackplayer.getBounding())){
            System.out.println("idk");
            gsm.set(new LevelOne(gsm));
          LevelOne.GameMode = true;
        }


        // CAMERA AND PLAYER DRAWING
        camera.position.set(blackplayer.getBounding().getX() + CAMERA_OFFSET_X, blackplayer.getBounding().getY() + CAMERA_OFFSET_Y, 0);
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);
        myBatch.begin();
        Pad.draw(myBatch);
        if(GameMode == true)
        {
            Tutorial.draw(myBatch);
            //music.play();
        }
        else
        {
            blackplayer.reset();
            gsm.push(new RestartState(gsm));
            music.stop();
        }
        myBatch.end();
        myBatch.begin();

        blackplayer.draw(myBatch);
        Spider.draw(myBatch);
        Portal.draw(myBatch);

        myBatch.end();


        if (showDebug) {
            debugRenderer.setProjectionMatrix(camera.combined);
            debugRenderer.begin(ShapeRenderer.ShapeType.Line);
            debugRenderer.setColor(1, 0, 0, 1);
            for (Platform p : platforms) {
                p.drawDebug(debugRenderer);
            }
            for (Spikes s : spikes) {
                s.drawDebug(debugRenderer);
            }
            Portal.drawDebug(debugRenderer);

            Spider.drawDebug(debugRenderer);
            debugRenderer.rect(blackplayer.getBounding().getX(), blackplayer.getBounding().getY(), blackplayer.getBounding().getWidth(), blackplayer.getBounding().getHeight());
            debugRenderer.end();
        }
        if (!platformcheck && blackplayer.touchplatform) {
            blackplayer.touchplatform = false;
        }

        blackplayer.UpdateLast();

        camera.position.set(CAMERA_OFFSET_X, CAMERA_OFFSET_Y, 0);
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);
        myBatch.begin();
        LeftButton.draw(myBatch);
        RightButton.draw(myBatch);
        UpButton.draw(myBatch);


        if (blackplayer.Lives == 3) {
            FullLives.draw(myBatch);
            tooclose.r = 1;
            tooclose.g = 1;
            tooclose.b = 1;
            tooclose.a = 1;
        }

        else if (blackplayer.Lives == 2) {
            TwoLives.draw(myBatch);
            tooclose.r = 1;
            tooclose.g = 1;
            tooclose.b = 1;
            tooclose.a = 1;

        }
        else if (blackplayer.Lives == 1) {
            OneLife.draw(myBatch);
            tooclose.r = 1;
            tooclose.g = 1;
            tooclose.b = 1;
            tooclose.a = 1;

        }
        else if (blackplayer.Lives == 0 && blackplayer.deathanimationfin())
        {

            GameMode = false;
        }
        myBatch.end();
    }

    @Override
    public void dispose () {
        myBatch.dispose();
        music.stop();
        music.dispose();

    }
}

