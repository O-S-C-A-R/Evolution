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
public class LevelOne extends Levelmaker {

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

            {1845, 678, 139, 65},
            {1899, 731, 112, 52},
            {2000, 769, 141, 64},
            {2055, 806, 167, 73},
            {3030, 1435, 122, 119},
            {3029, 1304, 55, 249},
            {2819, 1304, 211, 131},
            {3613, 1309, 121, 237},
            {3724, 1302, 225, 122},
            {4106, 1789, 256, 133},
            {677, 843, 994, 104},
            {633, 621, 139, 22},
            {659, 642, 113, 19},
            {686, 661, 98, 17},
            {710, 677, 110, 21},
            {741, 697, 123, 12},
            {448, 624, 184, 19},
            {460, 643, 39, 238},
            {491, 879, 186, 67},
            {526, 686, 14, 155},
            {499, 747, 25, 38},
            {511, 656, 64, 31},
            {526, 838, 63, 41},
            {862, 653, 986, 44},
            {1663, 945, 65, 98},
            {1844, 694, 64, 51},
            {1899, 742, 101, 42},
            {1998, 782, 57, 51},
            {2051, 831, 53, 48},
            {2101, 878, 61, 41},
            {2158, 919, 62, 51},
            {2208, 968, 173, 49},
            {2375, 1016, 61, 50},
            {2431, 1063, 67, 52},
            {2489, 1112, 130, 50},
            {2610, 1159, 117, 41},
            {2664, 1199, 108, 55},
            {2720, 1252, 382, 53},
            {1717, 1033, 66, 91},
            {1778, 1084, 61, 83},
            {1838, 1132, 59, 111},
            {1894, 1235, 57, 81},
            {1951, 1284, 57, 101},
            {2000, 1334, 65, 171},
            {2061, 1431, 59, 118},
            {2117, 1483, 62, 119},
            {2176, 1534, 60, 164},
            {1842, 862, 105, 16},
            {2011, 941, 93, 14},
            {1860, 1040, 98, 13},
            {2039, 1118, 105, 15},
            {2204, 1291, 348, 23},
            {2608, 1363, 100, 13},
            {2818, 1304, 213, 132},
            {3030, 1410, 122, 145},
            {3030, 1360, 105, 53},
            {3028, 1305, 56, 54},
            {2228, 1635, 63, 105},
            {2287, 1683, 58, 112},
            {2343, 1735, 309, 277},
            {2646, 1777, 60, 111},
            {2704, 1825, 58,138},
            {2758, 1875, 62, 143},
            {2818, 1932, 62, 129},
            {2875, 1975, 59, 86},
            {2924, 2024, 62, 132},
            {2983, 2090, 244, 107},
            {3216, 2132, 570, 24},
            {3764, 2082, 74, 62},
            {3823, 2034, 64, 49},
            {3876, 1991, 62, 40},
            {3932, 1933, 86, 59},
            {3994, 1883, 68, 56},
            {4049, 1835, 61, 50},
            {4103, 179, 261, 41},
            {4359, 1832, 57, 108},
            {4411, 1883, 61, 68},
            {4471, 1935, 61, 95},
            {4529, 1991, 64, 96},
            {4587, 2033, 60, 112},
            {4644, 2083, 59, 165},
            {4697, 2181, 60, 100},
            {4752, 2227, 65, 130},
            {4811, 2326, 73, 168},
            {4864, 2378, 57, 168},
            {4920, 2472, 362, 85},
            {5276, 2428, 185, 47},
            {5458, 2378, 166, 51},
            {5624, 2378, 0, 0},
            {3135, 1359, 90, 49},
            {3192, 1403, 188, 55},
            {3354, 1458, 152, 47},
            {3497, 1396, 77, 64},
            {3561, 1359, 71, 38},
            {3621, 1303, 61, 57},
            {3612, 1394, 125, 149},
            {3740, 1304, 216, 123},
            {3682, 1302, 60, 58},
            {3240, 1459, 112, 53},
            {3956, 1266, 63, 39},
            {4016, 1305, 240, 56},
            {4233, 1359, 162, 50},
            {4376, 1408, 168, 52},
            {4512, 1456, 280, 44},
            {4610, 1494, 71, 58},
            {4670, 1550, 118, 51},
            {4039, 1365, 405, 131},
            {4632, 1554, 187, 230},
            {4820, 1782, 83, 88},
            {4871, 1868, 85, 79},
            {4924, 1943, 78, 64},
                    {4890, 1950, 7, 123},
                    {4972, 2070,119, 22},
                    {5027, 2090, 59, 72},
                    {5082, 2158, 256, 59},
                    {5277, 2117, 59, 40},
                    {5190, 2069, 89, 47},
                    {5040, 1972, 186, 99},
                    {5046, 1923, 126, 48},
                    {5031, 1833, 87, 90},
                    {5114, 1653, 110, 183},
                    {5217, 1595, 15, 58},
                    {5229, 1465, 199, 133},
                    {5423, 1450, 230, 97},
                    {5505, 1404, 92, 48},
                    {5469, 1229, 72, 178},
                    {5533, 1142, 59, 90},
                    {5587, 1091, 60, 51},
                    {5647, 1043, 57, 48},
                    {5699, 987, 123, 59},
                    {5821, 975, 309, 32},
                    {6129, 943, 28, 42},
                    {6156, 919, 27, 38},
                    {6185, 862, 219, 72},
                    {6278, 932, 31, 278},
                    {6105, 1185, 174, 45},
                    {6213, 1131, 49, 44},
                    {6245, 1004, 14, 120},
                    {6221, 946, 39, 46},
                    {5952, 1131, 153, 59},
                    {5954, 1178, 0, 1},
                    {5781, 1177, 172, 273},
                    {5730, 1226, 51, 90},
                    {5837, 1449, 121, 49},
                    {5889, 1494, 115, 136},
                    {5836, 1627, 55, 74},
                    {5779, 1678, 59, 164},
                    {5648, 1726, 131, 267},
                    {5474, 1776, 179, 49},
                    {5528, 1820, 123, 91},
                    {5584, 1906, 65, 89},
                    {5487, 1825, 96, 115},
                    {5636, 1992, 180, 77},
                    {5745, 2063, 117, 89},
                    {5798, 2148, 193, 148},
                    {5599, 2336, 244, 47},
                    {5701, 2277, 109, 61},
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

    public LevelOne(GameStateManager gsm)
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

        blackplayer = new Player(600,640);
        blackplayer.maxjump = 250;

        Pad = new Bouncepad(2057,1135);
        Pad2 = new Bouncepad(2936,1447);
        Pad3 = new Bouncepad(4352,1505);
        Pad4 = new Bouncepad(4746,1797);
        Pad5 = new Bouncepad(4920,2075);


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

        LVLone = new Sprite(new Texture(Gdx.files.internal("images/map/LevelOne.png")));

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
            blackplayer.jumpvelocity = 350;

        }if(Pad2.bounce(blackplayer)){
            blackplayer.jumpvelocity = 350;

        }
        if(Pad3.bounce(blackplayer)){
            blackplayer.jumpvelocity = 400;

        }
        if(Pad4.bounce(blackplayer)){
            blackplayer.jumpvelocity = 400;

        }
        if(Pad5.bounce(blackplayer)){
            blackplayer.jumpvelocity = 400;

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
            Pad.draw(myBatch);
            Pad2.draw(myBatch);
            Pad3.draw(myBatch);
            Pad4.draw(myBatch);
            Pad5.draw(myBatch);
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




