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
import com.missionbit.game.Laser;
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
    private Sprite LVLoneotherhalf;

    private Sound JumpSound;
    private Sound pad;
    private Sprite Bouncepad;
    private Music music;
    private Laser laser;
    private Laser laser1;
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
    private boolean showDebug = true;


    private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
    //protected static boolean GameMode = true;

    private Buttons LeftButton;
    private Buttons RightButton;
    private Buttons UpButton;
    Vector3 touchPos;


    private boolean platformcheck = false;


    private static final int[][] PLAT_LOCS = new int[][]{


            {630, 564, 214, 79},
                    {658, 620, 239, 39},
                    {685, 650, 254, 26},
                    {712, 666, 250, 29},
                    {741, 690, 123, 16},
                    {204, 594, 432, 31},
                    {492, 606, 32, 304},
                    {508, 886, 167, 37},
                    {670, 845, 1003, 53},
                    {858, 555, 1225, 139},
                    {1848, 543, 114, 202},
                    {1358, 862, 315, 331},
                    {1651, 951, 75, 294},
                    {1700, 1034, 81, 250},
                    {1767, 1080, 71, 272},
                    {1821, 1133, 77, 268},
                    {1899, 718, 295, 68},
                    {1841, 864, 105, 13},
                    {1997, 754, 567, 79},
                    {2052, 817, 501, 61},
                    {2102, 827, 206, 91},
                    {1860, 1045, 96, 8},
                    {2044, 1121, 96, 11},
                    {2160, 866, 220, 103},
                    {2215, 948, 335, 68},
                    {2373, 998, 149, 65},
                    {2435, 1039, 149, 74},
                    {2491, 1093, 272, 66},
                    {2610, 1146, 101, 50},
                    {2668, 1183, 190, 66},
                    {2721, 1231, 598, 74},
                    {3085, 1269, 357, 90},
                    {3137, 1294, 434, 116},
                    {3194, 1355, 137, 101},
                    {3240, 1422, 110, 88},
                    {3321, 1398, 191, 92},
                    {3431, 1233, 139, 220},
                    {3547, 1394, 1, 0},
                    {3540, 1279, 88, 111},
                    {3608, 1182, 72, 180},
                    {3652, 1155, 460, 145},
                    {4019, 1262, 333, 97},
                    {4232, 1331, 404, 78},
                    {4375, 1375, 360, 83},
                    {2208, 1291, 349, 23},
                    {2610, 1364, 94, 11},
                    {1855, 1235, 98, 261},
                    {1934, 1282, 71, 299},
                    {1968, 1335, 101, 381},
                    {2040, 1437, 82, 313},
                    {2106, 1486, 70, 290},
                    {2169, 1532, 68, 333},
                    {2223, 1637, 64, 392},
                    {2268, 1687, 78, 449},
                    {2332, 1731, 319, 238},
                    {2637, 1777, 69, 377},
                    {2699, 1828, 64, 356},
                    {2752, 1876, 68, 346},
                    {2793, 1937, 89, 266},
                    {2820, 1978, 116, 193},
                    {2913, 2021, 72, 199},
                    {2973, 2093, 256, 341},
                    {3181, 2130, 723, 177},
                    {3762, 2083, 159, 125},
                    {3825, 2034, 132, 113},
                    {3875, 1990, 146, 81},
                    {3933, 1935, 156, 91},
                    {3996, 1884, 141, 95},
                    {4051, 1837, 183, 73},
                    {4102, 1792, 262, 73},
                    {4333, 1834, 83, 214},
                    {4389, 1882, 87, 262},
                    {4460, 1930, 72, 327},
                    {4517, 1992, 74, 309},
                    {4580, 2031, 65, 367},
                    {4630, 2083, 72, 381},
                    {4692, 2182, 66, 392},
                    {2820, 1302, 333, 136},
                    {3029, 1410, 122, 149},
                    {3610, 1299, 125, 250},
                    {3611, 1305, 335, 122},
                    {4051, 1341, 392, 148},
                    {4510, 1457, 284, 45},
                    {4612, 1448, 207, 105},
                    {4674, 1500, 139, 103},
                    {4631, 1548, 491, 231},
                    {4820, 1778, 161, 89},
                    {4867, 1847, 165, 104},
                    {4893, 1949, 151, 121},
                    {4892, 1915, 171, 153},
                    {4975, 2052, 157, 43},
                    {5032, 2080, 187, 80},
                    {5080, 2133, 258, 82},
                    {5170, 2117, 170, 99},
                    {5149, 2069, 131, 114},
                    {5003, 1970, 221, 129},
                    {4932, 1921, 239, 66},
                    {4949, 1734, 167, 193},
                    {5075, 1596, 150, 239},
                    {5201, 1546, 33, 106},
                    {5219, 1393, 211, 208},
                    {5410, 1452, 243, 96},
                    {5382, 1403, 212, 118},
                    {5253, 1000, 286, 451},
                    {5501, 1084, 91, 144},
                    {5564, 991, 82, 150},
                    {5591, 825, 114, 268},
                    {5686, 765, 138, 283},
                    {5792, 772, 339, 233},
                    {6114, 883, 44, 103},
                    {6133, 877, 49, 86},
                    {6169, 865, 129, 69},
                    {6277, 923, 98, 282},
                    {6069, 1164, 231, 77},
                    {5952, 1131, 156, 119},
                    {5780, 1180, 183, 268},
                    {5730, 1226, 84, 87},
                    {5839, 1429, 202, 67},
                    {5889, 1488, 209, 290},
                    {5838, 1629, 54, 201},
                    {5836, 1627, 87, 223},
                    {5780, 1678, 74, 194},
                    {5649, 1726, 164, 209},
                    {5474, 1776, 192, 49},
                    {5486, 1812, 208, 131},
                    {5582, 1905, 366, 90},
                    {5637, 1957, 267, 111},
                    {4701, 2232, 112, 201},
                    {4776, 2333, 92, 170},
                    {4860, 2375, 62, 281},
                    {4911, 2470, 542, 162},
                    {5284, 2424, 226, 95},
                    {5457, 2379, 172, 79},
                    {5597, 2337, 141, 69},
                    {5700, 2277, 133, 80},
                    {5794, 2047, 140, 261},
                    {5744, 2053, 88, 101},
            {630, 564, 214, 79},
            {658, 620, 239, 39},
            {685, 650, 254, 26},
            {712, 666, 250, 29},
            {741, 690, 123, 16},
            {204, 594, 432, 31},
            {492, 606, 32, 304},
            {508, 886, 167, 37},
            {670, 845, 1003, 53},
            {858, 555, 1225, 139},
            {1848, 543, 114, 202},
            {1358, 862, 315, 331},
            {1651, 951, 75, 294},
            {1700, 1034, 81, 250},
            {1767, 1080, 71, 272},
            {1821, 1133, 77, 268},
            {1899, 718, 295, 68},
            {1841, 864, 105, 13},
            {1997, 754, 567, 79},
            {2052, 817, 501, 61},
            {2102, 827, 206, 91},
            {1860, 1045, 96, 8},
            {2044, 1121, 96, 11},
            {2160, 866, 220, 103},
            {2215, 948, 335, 68},
            {2373, 998, 149, 65},
            {2435, 1039, 149, 74},
            {2491, 1093, 272, 66},
            {2610, 1146, 101, 50},
            {2668, 1183, 190, 66},
            {2721, 1231, 598, 74},
            {3085, 1269, 357, 90},
            {3137, 1294, 434, 116},
            {3194, 1355, 137, 101},
            {3240, 1422, 110, 88},
            {3321, 1398, 191, 92},
            {3431, 1233, 139, 220},
            {3547, 1394, 1, 0},
            {3540, 1279, 88, 111},
            {3608, 1182, 72, 180},
            {3652, 1155, 460, 145},
            {4019, 1262, 333, 97},
            {4232, 1331, 404, 78},
            {4375, 1375, 360, 83},
            {2208, 1291, 349, 23},
            {2610, 1364, 94, 11},
            {1855, 1235, 98, 261},
            {1934, 1282, 71, 299},
            {1968, 1335, 101, 381},
            {2040, 1437, 82, 313},
            {2106, 1486, 70, 290},
            {2169, 1532, 68, 333},
            {2223, 1637, 64, 392},
            {2268, 1687, 78, 449},
            {2332, 1731, 319, 238},
            {2637, 1777, 69, 377},
            {2699, 1828, 64, 356},
            {2752, 1876, 68, 346},
            {2793, 1937, 89, 266},
            {2820, 1978, 116, 193},
            {2913, 2021, 72, 199},
            {2973, 2093, 256, 341},
            {3181, 2130, 723, 177},
            {3762, 2083, 159, 125},
            {3825, 2034, 132, 113},
            {3875, 1990, 146, 81},
            {3933, 1935, 156, 91},
            {3996, 1884, 141, 95},
            {4051, 1837, 183, 73},
            {4102, 1792, 262, 73},
            {4333, 1834, 83, 214},
            {4389, 1882, 87, 262},
            {4460, 1930, 72, 327},
            {4517, 1992, 74, 309},
            {4580, 2031, 65, 367},
            {4630, 2083, 72, 381},
            {4692, 2182, 66, 392},
            {2820, 1302, 333, 136},
            {3029, 1410, 122, 149},
            {3610, 1299, 125, 250},
            {3611, 1305, 335, 122},
            {4051, 1341, 392, 148},
            {4510, 1457, 284, 45},
            {4612, 1448, 207, 105},
            {4674, 1500, 139, 103},
            {4631, 1548, 491, 231},
            {4820, 1778, 161, 89},
            {4867, 1847, 165, 104},
            {4893, 1949, 151, 121},
            {4892, 1915, 171, 153},
            {4975, 2052, 157, 43},
            {5032, 2080, 187, 80},
            {5080, 2133, 258, 82},
            {5170, 2117, 170, 99},
            {5149, 2069, 131, 114},
            {5003, 1970, 221, 129},
            {4932, 1921, 239, 66},
            {4949, 1734, 167, 193},
            {5075, 1596, 150, 239},
            {5201, 1546, 33, 106},
            {5219, 1393, 211, 208},
            {5410, 1452, 243, 96},
            {5382, 1403, 212, 118},
            {5253, 1000, 286, 451},
            {5501, 1084, 91, 144},
            {5564, 991, 82, 150},
            {5591, 825, 114, 268},
            {5686, 765, 138, 283},
            {5792, 772, 339, 233},
            {6114, 883, 44, 103},
            {6133, 877, 49, 86},
            {6169, 865, 129, 69},
            {6277, 923, 98, 282},
            {6069, 1164, 231, 77},
            {5952, 1131, 156, 119},
            {5780, 1180, 183, 268},
            {5730, 1226, 84, 87},
            {5839, 1429, 202, 67},
            {5889, 1488, 209, 290},
            {5838, 1629, 54, 201},
            {5836, 1627, 87, 223},
            {5780, 1678, 74, 194},
            {5649, 1726, 164, 209},
            {5474, 1776, 192, 49},
            {5486, 1812, 208, 131},
            {5582, 1905, 366, 90},
            {5637, 1957, 267, 111},
            {4701, 2232, 112, 201},
            {4776, 2333, 92, 170},
            {4860, 2375, 62, 281},
            {4911, 2470, 542, 162},
            {5284, 2424, 226, 95},
            {5457, 2379, 172, 79},
            {5597, 2337, 141, 69},
            {5700, 2277, 133, 80},
            {5794, 2047, 140, 261},
            {5744, 2053, 88, 101},
            {2009, 942, 98, 12},

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
        music = Gdx.audio.newMusic(Gdx.files.internal("music/My Song 2.mp3"));
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();
        JumpSound = Gdx.audio.newSound(Gdx.files.internal("music/Swooshing.mp3"));
        JumpSound.setLooping(1,false);
        JumpSound.setVolume(1, 0.01f);
        tooclose = new Color(1,1,1,1);
        pad = Gdx.audio.newSound(Gdx.files.internal("music/My Song 4.mp3"));
        pad.setLooping( 1, false);
        pad.setVolume(1, 0.01f);


        blackplayer = new Player(600,740);
        blackplayer.maxjump = 450;

        Pad = new Bouncepad(2057,1135);
        Pad2 = new Bouncepad(2936,1447);
        Pad3 = new Bouncepad(4352,1505);
        Pad4 = new Bouncepad(4746,1797);
        Pad5 = new Bouncepad(4920,2075);


        LeftButton = new Buttons(-70, -100, "images/ui/LeftButtonBlue.png");
        RightButton = new Buttons(30, -100, "images/ui/RightButtonBlue.png");
        UpButton = new Buttons(690, -100, "images/ui/UpButtonBlue.png");
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
        laser = new Laser( 1633, 838, true);
        laser1 = new Laser(1733, 938, false);

        debugRenderer = new ShapeRenderer();

        //LOAD IMAGES
        platforms = new ArrayList<Platform>();

        LVLone = new Sprite(new Texture(Gdx.files.internal("images/map/LevelOne.png")));
        LVLoneotherhalf = new Sprite(new Texture(Gdx.files.internal("images/map/LevelOneotherhalf.png")));
        LVLone.setX(4198);
        LVLone.setY(67);


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
            blackplayer.jumpvelocity = 750;
            pad.play();

        }if(Pad2.bounce(blackplayer)){
            blackplayer.jumpvelocity = 750;
            pad.play();

        }
        if(Pad3.bounce(blackplayer)){
            blackplayer.jumpvelocity = 800;
            pad.play();

        }
        if(Pad4.bounce(blackplayer)){
            blackplayer.jumpvelocity = 800;
            pad.play();

        }
        if(Pad5.bounce(blackplayer)){
            blackplayer.jumpvelocity = 750;
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

        if (laser.collide(blackplayer)){
            blackplayer.LaserDie(laser);
        }

        if (laser1.collide(blackplayer)){
            blackplayer.LaserDie(laser1);
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
        //System.out.println("Game mode " + GameMode);

        if(GameMode == true)
        {
            //System.out.println("Playing");
            LVLone.draw(myBatch);
            LVLoneotherhalf.draw(myBatch);
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
            blackplayer.reset(632, 703);
            music.stop();
            gsm.push(new RestartState(gsm));
        }
        myBatch.end();
        myBatch.begin();

        blackplayer.draw(myBatch);
        Spider.draw(myBatch);
        laser.draw(myBatch);
        laser1.draw(myBatch);
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

            laser.drawDebug(debugRenderer);
            laser1.drawDebug(debugRenderer);
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
        //music.stop();
        music.dispose();
    }
}





