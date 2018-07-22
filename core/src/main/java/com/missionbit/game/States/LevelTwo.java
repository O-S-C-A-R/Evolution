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
//import com.missionbit.game.Particles;
public class LevelTwo extends Levelmaker {

    /* game constants */
    private static final int CAMERA_OFFSET_X = 350;
    private static final int CAMERA_OFFSET_Y = 150;
    private static final int VIEWPORT_WIDTH = 960;
    private static final int VIEWPORT_HEIGHT = 540;
    //private Particles Portal;
    private OrthographicCamera camera;
    private Particles Portal;

    private Player blackplayer;
    private Random randomSource;
    private Sprite LVLone;
    private Sound JumpSound;
    private Sprite Bouncepad;
    private Music music;

    private com.missionbit.game.Bouncepad Pad;
    private com.missionbit.game.Bouncepad Pad2;
    private com.missionbit.game.Bouncepad Pad3;
    private com.missionbit.game.Bouncepad Pad4;
    private com.missionbit.game.Bouncepad Pad5;
    private com.missionbit.game.Bouncepad Pad6;
    private com.missionbit.game.Bouncepad Pad7;
    private com.missionbit.game.Bouncepad Pad8;
    private com.missionbit.game.Bouncepad Pad9;
    private com.missionbit.game.Bouncepad Pad10;
    private com.missionbit.game.Bouncepad Pad12;
    private com.missionbit.game.Bouncepad Pad13;
    private com.missionbit.game.Bouncepad Pad11;

    private Rumble rumble;
    private Texture RestartScreen;

    private Buttons Fade;
    //    private Buttons FullLives;
//    private Buttons TwoLives;
//    private Buttons OneLife;
    private Buttons FullLivesBlue;
    private Buttons TwoLivesBlue;
    private Buttons OneLifeBlue;
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
    Vector3 touchPos;


    private boolean platformcheck = false;


    private static final int[][] PLAT_LOCS = new int[][]{
            {744, 790, 94, 46},
            {2720, 1360, 1950, 1105},
            {977, 2477, 4679, 216},
            {780, 816, 103, 63},
            {824, 857, 126, 57},
            {939, 854, 140, 36},
            {1080, 873, 558, 64},
            {497, 793, 246, 5},
            {413, 794, 94, 359},
            {497, 1152, 585, 46},
            {1074, 1057, 411, 137},
            {1632, 934, 1237, 423},
            {895, 1186, 585, 649},
            {1475, 1739, 1030, 303},
            {553, 1851, 940, 185},
            {594, 2016, 176, 1963},
            {761, 3817, 5920, 100},
            {6318, 3615, 370, 210},
            {6577, 3332, 301, 286},
            {6873, 3043, 452, 289},
            {7203, 1715, 90, 1339},
            {6876, 1485, 347, 401},
            {6593, 1226, 292, 359},
            {4993, 728, 1608, 584},
            {3085, 731, 1752, 585},
            {3033, 1308, 55, 123},
            {3079, 1387, 197, 413},
            {3267, 1699, 2149, 390},
            {5394, 1951, 264, 345},
            {5650, 2152, 252, 326},
            {950, 2464, 4707, 230},
    };


    private static final float[][] spike_locs = new float[][]{
            {2055, 877, 2212, 1045, 2371, 1046, 2376, 1013, 2217, 1011, 2217, 981, 2161, 964, 2158, 913, 2105, 915, 2101, 875, 2052, 877, },
            {2376, 1063, 2376, 1078, 2431, 1082, 2432, 1063, 2432, 1063, 2375, 1064, },
            {2432, 1110, 2409, 1100, 2430, 1089, 2433, 1106, 2459, 1159, 2487, 1114, 2435, 1113, },
            {2491, 1161, 2509, 1190, 2527, 1161, 2495, 1156, 2493, 1160, },
            {2560, 1166, 2736, 1341, 2736, 1341, 2840, 1341, 2865, 1292, 2730, 1290, 2729, 1262, 2674, 1247, 2665, 1199, 2613, 1190, 2612, 1165, 2562, 1164, 2561, 1167, },
            {3193, 1456, 3213, 1489, 3230, 1458, 3195, 1457, },
            {3364, 1509, 3520, 1509, 3682, 1364, 3635, 1365, 3627, 1390, 3571, 1397, 3571, 1440, 3522, 1448, 3516, 1486, 3359, 1489, 3364, 1509, },
            {3951, 1308, 3983, 1358, 4012, 1303, 3951, 1309, },
            {4019, 1365, 4030, 1385, 4043, 1365, 4019, 1364, },
            {4449, 1462, 4488, 1548, 4528, 1508, 4512, 1507, 4514, 1459, 4448, 1461, },
            {4549, 1506, 4577, 1553, 4605, 1501, 4549, 1505, },
            {4821, 1874, 4850, 1897, 4859, 1962, 4890, 1967, 4920, 1993, 4922, 1949, 4875, 1949, 4869, 1881, 4820, 1870, },
            {4974, 2095, 5007, 2126, 5015, 2176, 5066, 2189, 5074, 2222, 5198, 2224, 5200, 2214, 5087, 2211, 5085, 2165, 5037, 2164, 5031, 2092, 4974, 2096, },
            {5081, 2228, 5104, 2253, 5121, 2238, 5147, 2262, 5168, 2235, 5203, 2289, 5240, 2236, 5265, 2263, 5284, 2231, 5307, 2253, 5328, 2219, 5082, 2228, },
    };

    private static ArrayList<Platform> platforms;

    private ShapeRenderer debugRenderer;

    public LevelTwo(GameStateManager gsm)
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

        blackplayer = new Player(700, 867);
        blackplayer.maxjump = 450;

        Pad = new Bouncepad(1531,934);
        Pad2 = new Bouncepad(1617,1095);
        Pad3 = new Bouncepad(1489,1185);
        Pad4 = new Bouncepad(1601,1797);
        Pad5 = new Bouncepad(1479,1406);
        Pad6 = new Bouncepad(2557,1365);
        Pad7 = new Bouncepad(2697,1763);
        Pad8 = new Bouncepad(2510,1934);
        Pad9 = new Bouncepad(2691,2068);
        Pad10 = new Bouncepad(820,2045);
        Pad12 = new Bouncepad(776,2389);
        Pad11 = new Bouncepad(929,2547);
        Pad13= new Bouncepad(775,2676);






        LeftButton = new Buttons(-70, -100, "images/ui/LeftButton.png");
        RightButton = new Buttons(30, -100, "images/ui/RightButton.png");
        UpButton = new Buttons(690, -100, "images/ui/UpButton.png");
        //Fade = new Buttons(-140,-120 ,"images/Fade.png");

//        FullLives = new Buttons(-140, 350, "images/ui/FullLives.png");
//        TwoLives = new Buttons(-140, 350, "images/ui/TwoLives.png");
//        OneLife = new Buttons(-140, 350, "images/ui/OneLife.png");
        FullLivesBlue = new Buttons(-140, 350, "images/ui/FullLivesBlue.png");
        TwoLivesBlue = new Buttons(-140, 350, "images/ui/TwoLivesBlue.png");
        OneLifeBlue = new Buttons(-140, 350, "images/ui/OneLiveBlue.png");

        randomSource = new Random();
        // TODO Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        Portal = new Particles(6245, 1040);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        //TODO Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //LOAD IMAGES
        platforms = new ArrayList<Platform>();

        LVLone = new Sprite(new Texture(Gdx.files.internal("images/map/Level 2.png")));

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
        // Clear the screen
        Gdx.gl.glClearColor(tooclose.r, tooclose.g,tooclose.b, tooclose.a );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        myBatch.setProjectionMatrix(camera.combined);

        if(Pad.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }if(Pad2.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }
        if(Pad3.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }
        if(Pad4.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }
        if(Pad5.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }
        if(Pad6.bounce(blackplayer)){
            blackplayer.jumpvelocity = 850;

        }if(Pad7.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }
        if(Pad8.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }
        if(Pad9.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }
        if(Pad10.bounce(blackplayer)){
            blackplayer.jumpvelocity = 550;

        }if(Pad11.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }
        if(Pad12.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

        }
        if(Pad13.bounce(blackplayer)){
            blackplayer.jumpvelocity = 650;

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
            gsm.set(new TutorialTwo(gsm));
            TutorialTwo.GameMode = true;
        }



        // CAMERA AND PLAYER DRAWING
        camera.position.set(blackplayer.getBounding().getX() + CAMERA_OFFSET_X, blackplayer.getBounding().getY() + CAMERA_OFFSET_Y, 0);
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);
        myBatch.begin();

        // Pad.draw(myBatch);
        System.out.println("Game mode " + GameMode);

        if(GameMode == true)
        {
            System.out.println("Playing");
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
        // Portal.draw(myBatch);

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
            // Portal.drawDebug(debugRenderer);

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
        Pad.draw(myBatch);
        Pad2.draw(myBatch);
        Pad3.draw(myBatch);
        Pad4.draw(myBatch);
        Pad5.draw(myBatch);
        Pad6.draw(myBatch);
        Pad7.draw(myBatch);
        Pad8.draw(myBatch);
        Pad9.draw(myBatch);
        Pad10.draw(myBatch);
        Pad11.draw(myBatch);
        Pad12.draw(myBatch);
        Pad13.draw(myBatch);



        if (blackplayer.Lives == 3) {
            FullLivesBlue.draw(myBatch);
            tooclose.r = 0;
            tooclose.g = 0;
            tooclose.b = 0;
            tooclose.a = 0;
        }

        else if (blackplayer.Lives == 2) {
            TwoLivesBlue.draw(myBatch);
            tooclose.r = 0;
            tooclose.g = 0;
            tooclose.b = 0;
            tooclose.a = 0;

        }
        else if (blackplayer.Lives == 1) {
            OneLifeBlue.draw(myBatch);
            tooclose.r = 0;
            tooclose.g = 0;
            tooclose.b = 0;
            tooclose.a = 0;

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




