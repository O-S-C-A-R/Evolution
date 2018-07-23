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

//import com.missionbit.game.Bouncepad;
public class TutorialTwo extends Levelmaker {

    /* game constants */
    private static final int CAMERA_OFFSET_X = 350;
    private static final int CAMERA_OFFSET_Y = 150;
    private static final int VIEWPORT_WIDTH = 960;
    private static final int VIEWPORT_HEIGHT = 540;
    private static final int ExtraJump = 200;
    private Particles Portal;
    private OrthographicCamera camera;

    private Player blackplayer;
    private Random randomSource;
    private Sprite LVLone;
    private Sound JumpSound;
    private Sound pad;
    private Sprite Bouncepad;
    private Music music;

    private com.missionbit.game.Bouncepad Pad;
    private com.missionbit.game.Bouncepad Pad2;
    private com.missionbit.game.Bouncepad Pad3;
    private com.missionbit.game.Bouncepad Pad4;
    private com.missionbit.game.Bouncepad Pad5;
    private com.missionbit.game.Bouncepad Pad6;
    private com.missionbit.game.Bouncepad Pad7;

    private Rumble rumble;
    private Texture RestartScreen;

    private Buttons Fade;
    private Buttons FullLives;
    private Buttons TwoLives;
    private Buttons OneLife;
    private Buttons FullLivesBlue;
    private Buttons TwoLivesBlue;
    private Buttons OneLifeBlue;
    private Buttons SuperSpeed;
    private Color tooclose;


    private Enemies Spider;
    private SpriteBatch myBatch;
    //private Vector2 velocity;
    private float Speed;
    private boolean showDebug =  false;


    private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
    //protected static boolean GameMode = true;

    private Buttons LeftButton;
    private Buttons RightButton;
    private Buttons UpButton;
    private Buttons SuperJump;
    Vector3 touchPos;


    private boolean platformcheck = false;


    private static final int[][] PLAT_LOCS = new int[][]{

            {32, 169, 505, 13},
            {366, 336, 196, 16},
            {564, 498, 177, 10},
            {860, 389, 227, 13},
            {1085, 280, 562, 26},
            {1743, 376, 24, 118},
            {1536, 477, 33, 131},
            {1714, 571, 36, 138},
            {1529, 693, 34, 144},
    };

    private static final float[][] spike_locs = new float[][]{
            {716.0f, 749.99994f, 691.0f, 782.99994f, 685.0f, 739.99994f, 791.0f, 620.99994f, 842.0f, 657.99994f, 850.0f, 693.99994f, 890.0f, 656.99994f, 938.0f, 643.99994f, 1001.0f, 716.99994f, 984.0f, 846.99994f, 939.0f, 866.99994f, 895.0f, 919.99994f, 813.0f, 881.99994f, 747.0f, 829.99994f, 722.0f, 755.99994f, },
            {1087.9999f, 735.99994f, 1077.9999f, 641.99994f, 1121.9999f, 569.99994f, 1168.9999f, 508.0f, 1267.9999f, 589.0f, 1314.9999f, 602.0f, 1318.9999f, 655.0f, 1344.9999f, 769.0f, 1313.9999f, 875.0f, 1193.9999f, 833.0f, 1086.9999f, 738.0f, },
    };
    private static ArrayList<Platform> platforms;

    private ShapeRenderer debugRenderer;

    public TutorialTwo(GameStateManager gsm)
    {
        super(gsm);
        music = Gdx.audio.newMusic(Gdx.files.internal("music/Howling-wind.mp3"));
        music.setLooping(true);
        music.setVolume(0.4f);
        //music.play();
        JumpSound = Gdx.audio.newSound(Gdx.files.internal("music/Swooshing.mp3"));
        JumpSound.setLooping(1,false);
        JumpSound.setVolume(1, 0.01f);
        tooclose = new Color(1,1,1,1);
        pad = Gdx.audio.newSound(Gdx.files.internal("music/My Song 4.mp3"));
        pad.setLooping(1,false);
        pad.setVolume(1, 0.01f);
        blackplayer = new Player(100,400);
        blackplayer.maxjump = 450;
        blackplayer.UltimateJump = -700;

        Pad = new Bouncepad(280,182);
        Pad2 = new Bouncepad(481,357);
        Pad3 = new Bouncepad(1546,307);
        Pad4 = new Bouncepad(1566,524);
        Pad5 = new Bouncepad(1708,628);
        Pad6 = new Bouncepad(1562,734);
        Pad7 = new Bouncepad(1721,416);



        LeftButton = new Buttons(-70, -100, "images/ui/LeftButton.png");
        RightButton = new Buttons(30, -100, "images/ui/RightButton.png");
        UpButton = new Buttons(690, -100, "images/ui/UpButton.png");
        SuperJump = new Buttons(690,-50 ,"images/ui/SuperJump.png");
        SuperSpeed = new Buttons(690, -10, "images/ui/SuperSpeed.png");

        FullLives = new Buttons(-140, 350, "images/ui/FullLives.png");
        TwoLives = new Buttons(-140, 350, "images/ui/TwoLives.png");
        OneLife = new Buttons(-140, 350, "images/ui/OneLife.png");

        randomSource = new Random();
        // TODO Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        Portal = new Particles(1712, 856);
        //TODO Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //LOAD IMAGES
        platforms = new ArrayList<Platform>();

        LVLone = new Sprite(new Texture(Gdx.files.internal("images/map/TutorialTwo.png")));

        // Initialize platforms
        platforms = new ArrayList<Platform>();

        for (int[] loc : PLAT_LOCS) {
            platforms.add(new Platform(loc[0], loc[1], loc[2], loc[3]));
        }

        for (float[] loc : spike_locs) {
            spikes.add(new Spikes(loc));
        }


        // velocity = new Vector2(0, 0);
        Spider = new Enemies(960, 687, 910, 1816);
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
                if (touchPos.x > SuperJump.getX() && touchPos.x < SuperJump.getX() + SuperJump.getWidth()) {
                    if (touchPos.y > SuperJump.getY() && touchPos.y < SuperJump.getY() + SuperJump.getHeight() && blackplayer.touchplatform) {
                        blackplayer.jumpvelocity = 600;

                    }
                }
                if (touchPos.x > SuperSpeed.getX() && touchPos.x < SuperSpeed.getX() + SuperSpeed.getWidth()) {
                    if (touchPos.y > SuperSpeed.getY() && touchPos.y < SuperSpeed.getY() + SuperSpeed.getHeight() && blackplayer.touchplatform) {
                        if (touchPos.x > RightButton.getX() && touchPos.x < RightButton.getX() + RightButton.getWidth()) {
                            if (touchPos.y > RightButton.getY() && touchPos.y < RightButton.getY() + RightButton.getHeight()) {
                                blackplayer.Xvelocity = 400;
                            }
                        }


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
        if (Gdx.input.isKeyPressed(Input.Keys.L) && blackplayer.touchplatform || Gdx.input.isKeyPressed(Input.Keys.E) && blackplayer.touchplatform)
        {
            blackplayer.jumpvelocity = 600;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            blackplayer.Xvelocity = -400;
            System.out.println("Super Left");
        }
        else
        {
            blackplayer.Xvelocity = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            blackplayer.Xvelocity = 400;
            System.out.println("Super Right");
        }
        else
        {
            blackplayer.Xvelocity = 0;
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

        // Clear the screen
        Gdx.gl.glClearColor(tooclose.r, tooclose.g,tooclose.b, tooclose.a );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        myBatch.setProjectionMatrix(camera.combined);

        if(Pad4.bounce(blackplayer)){
            blackplayer.jumpvelocity = 600;
            pad.play();
        }
        if(Pad5.bounce(blackplayer)){
            blackplayer.jumpvelocity = 500;
            pad.play();
        }
        if(Pad6.bounce(blackplayer)){
            blackplayer.jumpvelocity = 500;
            pad.play();
        }
        if(Pad7.bounce(blackplayer)){
            blackplayer.jumpvelocity = 500;
            pad.play();
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




        // CAMERA AND PLAYER DRAWING
        camera.position.set(blackplayer.getBounding().getX() + CAMERA_OFFSET_X, blackplayer.getBounding().getY() + CAMERA_OFFSET_Y, 0);
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);
        myBatch.begin();

        // Pad.draw(myBatch);
        //System.out.println("Game mode " + GameMode);

        if(GameMode == true)
        {
            //System.out.println("Playing");
            LVLone.draw(myBatch);


            //music.play();
        }
        else
        {
            System.out.println("stopping");
            blackplayer.reset();
            music.stop();
            gsm.push(new RestartState(gsm));
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
        SuperJump.draw(myBatch);
        SuperSpeed.draw(myBatch);
        Pad4.draw(myBatch);
        Pad5.draw(myBatch);
        Pad6.draw(myBatch);
        Pad7.draw(myBatch);



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


        if(blackplayer.getBounding().getY() < -400){
            blackplayer.tutorial2update();

        }
        if(Portal.hit(blackplayer.getBounding())){
            System.out.println("idk");
            gsm.set(new LevelTwo(gsm));
            LevelTwo.GameMode = true;
        }

    }

    @Override
    public void dispose () {
        myBatch.dispose();
        music.stop();
        music.dispose();
    }
}





