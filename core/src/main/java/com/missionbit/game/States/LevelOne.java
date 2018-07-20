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
import com.missionbit.game.Buttons;
import com.missionbit.game.Enemies;
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
    private boolean showDebug = false ;


    private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
    //protected static boolean GameMode = true;

    private Buttons LeftButton;
    private Buttons RightButton;
    private Buttons UpButton;
    Vector3 touchPos;


    private boolean platformcheck = false;


    private static final int[][] PLAT_LOCS = new int[][]{
            {559, 624, 72, 3},
            {494, 628, 64, 10},

            {495, 689, 4, 160},

            {499, 644, 1, 42},

            {505, 641, 49, 27},

            {535, 691, 11, 148},

            {541, 835, 39, 15},

            {542, 669, 42, 22},

            {631, 628, 26, 18},

            {657, 646, 30, 14},

            {687, 661, 24, 16},

            {711, 677, 29, 19},

            {740, 696, 122, 14},

            {862, 658, 985, 37},

            {674, 847, 1001, 55},

            {495, 858, 181, 32},

            {1634, 903, 39, 49},

            {1672, 949, 57, 84},

            {1843, 696, 56, 48},

            {1899, 744, 102, 39},

            {1999, 787, 58, 47},

            {2057, 834, 49, 47},

            {2101, 865, 11, 18},

            {2102, 882, 46, 34},

            {2145, 920, 61, 49},

            {2206, 969, 172, 48},

            {2372, 1017, 62, 48},

            {1845, 860, 103, 13},

            {1865, 1041, 88, 10},

            {2008, 943, 98, 12},

            {2043, 1116, 98, 14},

            {2430, 1065, 59, 49},
            {2486, 1119, 87, 40},
            {2570, 1119, 41, 37},
            {2612, 1158, 50, 40},
            {2660, 1199, 62, 49},
            {2722, 1248, 157, 44},
            {2879, 1292, 208, 12},
            {2609, 1364, 95, 8},
            {2206, 1288, 348, 24},
            {3084, 1302, 50, 58},
            {3133, 1361, 62, 49},
            {3189, 1410, 58, 317},
            {3187, 1410, 60, 48},
            {3237, 1462, 114, 50},
            {2820, 1356, 210, 82},
            {3028, 1357, 123, 199},
            {1730, 1032, 53, 50},
            {1783, 1082, 63, 53},
            {1847, 1134, 48, 99},
            {1895, 1233, 56, 48},
            {1951, 1281, 57, 85},
            {2009, 1331, 57, 106},
            {2066, 1437, 53, 46},
            {2119, 1481, 58, 54},
            {2177, 1532, 59, 116},
            {2235, 1634, 55, 50},
            {2288, 1683, 57, 50},
            {2345, 1733, 307, 42},
            {2649, 1778, 56, 47},
            {2701, 1830, 61, 56},
            {2762, 1877, 4, 1},
            {2763, 1875, 2, 1},
            {2762, 1878, 59, 54},
            {2820, 1931, 62, 44},
            {2882, 1976, 53, 43},
            {2935, 2019, 51, 69},
            {2986, 2089, 240, 40},
            {3224, 2132, 539, 82},
            {3764, 2085, 2, 47},
            {3766, 2083, 56, 48},
            {3822, 2034, 53, 48},
            {3875, 1991, 63, 43},
            {3938, 1935, 55, 56},
            {3993, 1885, 57, 50},
            {4052, 1836, 52, 47},
            {4104, 1796, 259, 40},
            {3350, 1459, 161, 45},
            {3511, 1407, 63, 52},
            {3573, 1354, 59, 37},
            {3632, 1306, 51, 48},
            {3612, 1392, 120, 153},
            {3732, 1392, 0, 0},
            {3734, 1304, 212, 121},
            {3947, 1286, 72, 21},
            {4017, 1308, 216, 48},
            {4231, 1356, 146, 50},
            {4043, 1363, 401, 135},
            {4443, 1424, 68, 34},
            {4512, 1458, 101, 46},
            {4609, 1506, 62, 47},
            {4671, 1553, 52, 48},
            {4723, 1601, 47, 92},
            {4770, 1693, 49, 90},
            {4632, 1557, 186, 228},
            {4817, 1786, 43, 86},
            {4866, 1872, 44, 76},
            {4910, 1952, 66, 56},
            {4976, 2008, 52, 83},
            {5033, 2093, 41, 71},
            {5074, 2164, 265, 53},
            {4891, 1954, 84, 117},
            {4361, 1836, 55, 48},
            {4416, 1884, 57, 51},
            {4473, 1935, 59, 55},
            {4532, 1990, 62, 42},
            {4594, 2032, 53, 51},
            {4646, 2082, 57, 99},
            {4703, 2181, 54, 54},
            {4757, 2235, 57, 95},
            {4814, 2330, 55, 47},
            {4869, 2377, 56, 94},
            {4924, 2471, 361, 42},
            {5286, 2426, 171, 42},
            {5278, 2118, 61, 44},
            {5225, 2070, 57, 42},
            {5164, 1971, 61, 99},
            {5117, 1921, 47, 50},
            {5076, 1833, 42, 87},
            {5039, 1748, 35, 85},
            {5074, 1701, 48, 46},
            {5122, 1662, 55, 39},
            {5177, 1605, 57, 49},
            {5234, 1518, 196, 86},
            {5430, 1451, 223, 101},
            {5541, 1404, 54, 47},
            {5428, 1233, 113, 171},
            {5545, 1141, 48, 87},
            {5120, 1701, 103, 137},
            {5180, 1652, 43, 45},
            {5075, 1747, 45, 80},
            {5454, 2377, 147, 49},
            {5600, 2336, 100, 38},
            {5700, 2278, 97, 56},
            {5796, 2156, 66, 123},
            {5745, 2066, 51, 85},
            {5638, 1993, 107, 73},
            {5584, 1910, 54, 83},
            {5487, 1911, 95, 31},
            {5487, 1826, 43, 85},
            {5475, 1777, 17, 49},
            {5491, 1775, 158, 33},
            {5647, 1730, 132, 42},
            {5779, 1677, 57, 50},
            {5836, 1628, 56, 49},
            {5892, 1499, 85, 129},
            {5837, 1449, 64, 46},
            {5784, 1302, 55, 147},
            {5733, 1227, 48, 84},
            {5592, 1093, 54, 48},
            {5646, 1048, 58, 42},
            {5704, 1008, 118, 40},
            {5822, 999, 307, 9},
            {6095, 986, 33, 13},
            {6129, 959, 27, 27},
            {6156, 931, 28, 28},
            {6184, 931, 102, 17},
            {6286, 949, 19, 235},
            {6101, 1175, 180, 18},
            {6212, 1135, 51, 30},
            {6247, 994, 11, 140},
            {6217, 960, 44, 32},
            {5949, 1133, 157, 49},
            {5778, 1182, 171, 44},

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
        music.play();
        JumpSound = Gdx.audio.newSound(Gdx.files.internal("music/Swooshing.mp3"));
        JumpSound.setLooping(1,false);
        JumpSound.setVolume(1, 0.01f);
        tooclose = new Color(1,1,1,1);

        blackplayer = new Player(600,670);
        blackplayer.maxjump = 250;

        //Pad = new Bouncepad(1260,59);

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
       // Portal = new Particles(1750, 295);
        //TODO Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //LOAD IMAGES
        platforms = new ArrayList<Platform>();

        Tutorial = new Sprite(new Texture(Gdx.files.internal("images/map/LevelOne.png")));
        // Initialize platforms
        platforms = new ArrayList<Platform>();

        for (int[] loc : PLAT_LOCS) {
            platforms.add(new Platform(loc[0], loc[1], loc[2], loc[3]));
        }

        for (float[] loc : spike_locs) {
            spikes.add(new Spikes(loc));
        }


        // velocity = new Vector2(0, 0);
        Spider = new Enemies(960, 670, 766, 962);
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

       // if(Pad.bounce(blackplayer)){
          //  blackplayer.jumpvelocity = 350;

        //}
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
        System.out.println("Game mode " + GameMode);
        if(GameMode == true)
        {
            System.out.println("Playing");
            Tutorial.draw(myBatch);
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
            FullLives.draw(myBatch);
            tooclose.r = 0;
            tooclose.g = 0;
            tooclose.b = 0;
            tooclose.a = 0;
        }

        else if (blackplayer.Lives == 2) {
            TwoLives.draw(myBatch);
            tooclose.r = 0;
            tooclose.g = 0;
            tooclose.b = 0;
            tooclose.a = 0;

        }
        else if (blackplayer.Lives == 1) {
            OneLife.draw(myBatch);
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




