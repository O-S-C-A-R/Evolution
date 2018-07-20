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
<<<<<<< HEAD
import com.missionbit.game.Bouncepad;
=======
>>>>>>> 32f8eccfc30ed442d72fbcefa9d028da60b40c25
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
    private com.missionbit.game.Bouncepad Pad2;
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
    private boolean showDebug =  false;


    private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
    protected static boolean GameMode = true;

    private Buttons LeftButton;
    private Buttons RightButton;
    private Buttons UpButton;
    Vector3 touchPos;


    private boolean platformcheck = false;


    private static final int[][] PLAT_LOCS = new int[][]{
            {{677, 843, 994, 104},
                    {633, 621, 139, 22},
                    {659, 642, 113, 19},
                    {686, 661, 98, 17},
                    {710, 677, 110, 21},
                    {741., 697, 123, 12},
                    {448, 624, 184, 19},
                    {460, 643, 39, 238},
                    {491, 879, 186, 67},
                    {526, 686, 14, 155.0f},
                    {499, 747.00006f, 25.99997f, 38.0f},
                    {511.0f, 656.00006f, 64.0f, 31.0f},
                    {526.0f, 838.00006f, 63.0f, 41.0f},
                    {862.0f, 653.0f, 986.9999f, 44.00006f},
                    {1663.9999f, 945.0f, 65.0f, 98.0f},
                    {1844.0f, 694.99994f, 64.0f, 51.0f},
                    {1899.0f, 742.99994f, 101.0f, 42.0f},
                    {1998.0f, 782.99994f, 57.0f, 51.0f},
                    {2051.9998f, 831.0f, 53.000244f, 48.0f},
                    {2101.0f, 878.0f, 61.0f, 41.0f},
                    {2158.9998f, 919.0f, 62.000244f, 51.0f},
                    {2208.9998f, 968.0f, 173.0f, 49.0f},
                    {2375.9998f, 1016.0f, 61.0f, 50.0f},
                    {2431.9998f, 1063.0f, 67.0f, 52.0f},
                    {2489.9998f, 1112.0f, 130.0f, 50.0f},
                    {2610.0f, 1159.0f, 117.0f, 41.0f},
                    {2664.0f, 1199.0f, 108.0f, 55.0f},
                    {2720.0f, 1252.0f, 382.0f, 53.0f},
                    {1717.0001f, 1033.0f, 66.0f, 91.0f},
                    {1778.0001f, 1084.0f, 61.0f, 83.0f},
                    {1838.0001f, 1132.0f, 59.0f, 111.0f},
                    {1894.0001f, 1235.0f, 57.0f, 81.0f},
                    {1951.0001f, 1284.0f, 57.0f, 101.0f},
                    {2000.0002f, 1334.9999f, 65.0f, 171.0f},
                    {2061.0002f, 1431.9999f, 59.0f, 118.0f},
                    {2117.0002f, 1483.9999f, 62.0f, 119.0f},
                    {2176.0002f, 1534.9999f, 60.0f, 164.00012f},
                    {1842.9999f, 862.00006f, 105.0f, 16.0f},
                    {2011.9999f, 941.00006f, 93.99988f, 14.0f},
                    {1860.9999f, 1040.0001f, 98.0f, 13.999878f},
                    {2039.9999f, 1118.0f, 105.00012f, 15.0f},
                    {2204.9998f, 1291.0f, 348.0f, 23.0f},
                    {2608.9998f, 1363.0f, 100.0f, 13.0f},
                    {2818.0f, 1304.9999f, 213.0f, 132.0f},
                    {3030.0f, 1410.9999f, 122.000244f, 145.0f},
                    {3030.0002f, 1360.9999f, 105.0f, 53.0f},
                    {3028.0002f, 1305.9999f, 56.0f, 54.0f},
                    {2228.9998f, 1635.0f, 63.0f, 105.0f},
                    {2287.9998f, 1683.0f, 58.0f, 112.0f},
                    {2343.9998f, 1735.0f, 309.0f, 277.99988f},
                    {2646.9998f, 1777.9999f, 60.0f, 111.0f},
                    {2704.9998f, 1825.9999f, 58.0f, 138.0f},
                    {2758.9998f, 1875.9999f, 62.0f, 143.0f},
                    {2818.9998f, 1932.9999f, 62.0f, 129.99988f},
                    {2875.9998f, 1975.9999f, 59.0f, 86.99988f},
                    {2924.9998f, 2024.0f, 62.0f, 132.0f},
                    {2983.9998f, 2090.0f, 244.0f, 107.0f},
                    {3216.9998f, 2132.0f, 570.00024f, 24.999756f},
                    {3764.0f, 2082.9998f, 74.0f, 62.0f},
                    {3823.0f, 2034.9998f, 64.0f, 49.0f},
                    {3876.0f, 1991.9998f, 62.0f, 40.0f},
                    {3932.0f, 1933.9998f, 86.0f, 59.0f},
                    {3994.0f, 1883.9998f, 68.0f, 56.0f},
                    {4049.0002f, 1835.0001f, 61.999756f, 50.0f},
                    {4103.0f, 1794.0001f, 261.0f, 41.0f},
                    {4359.0f, 1832.0001f, 57.99951f, 108.99988f},
                    {4411.9995f, 1883.0f, 61.00049f, 68.0f},
                    {4471.0f, 1935.0f, 61.0f, 95.999756f},
                    {4529.0f, 1991.9998f, 64.0f, 96.0f},
                    {4587.0f, 2033.9998f, 60.0f, 112.0f},
                    {4644.0f, 2083.9998f, 59.0f, 165.0f},
                    {4697.0f, 2181.9998f, 60.0f, 100.0f},
                    {4752.0f, 2227.9998f, 65.0f, 130.0f},
                    {4811.0f, 2326.9998f, 73.0f, 168.0f},
                    {4864.9995f, 2378.0002f, 57.0f, 168.0f},
                    {4920.9995f, 2472.0002f, 362.0005f, 85.99951f},
                    {5276.0f, 2428.0f, 185.0f, 47.0f},
                    {5458.0f, 2378.0f, 166.0f, 51.0f},
                    {5624.0f, 2378.0f, 0.0f, 0.0f},
                    {3135.0f, 1359.0001f, 90.0f, 49.0f},
                    {3192.0f, 1403.0001f, 188.0f, 55.0f},
                    {3354.0f, 1458.0001f, 152.0f, 47.0f},
                    {3497.0f, 1396.0001f, 77.0f, 64.0f},
                    {3561.0f, 1359.0001f, 71.0f, 38.0f},
                    {3621.0f, 1303.0001f, 61.0f, 57.0f},
                    {3612.0f, 1394.0001f, 125.0f, 149.0f},
                    {3740.0f, 1304.0001f, 216.0f, 123.0f},
                    {3682.0f, 1302.0001f, 60.0f, 58.0f},
                    {3240.0f, 1459.9999f, 112.0f, 53.0f},
                    {3956.0f, 1266.0f, 63.0f, 39.0f},
                    {4016.0f, 1305.0f, 240.0f, 56.0f},
                    {4233.0f, 1359.0f, 162.0f, 50.0f},
                    {4376.9995f, 1408.0f, 168.0f, 52.0f},
                    {4512.9995f, 1456.0f, 280.0f, 44.999878f},
                    {4610.9995f, 1494.9999f, 71.0f, 58.0f},
                    {4670.9995f, 1550.9999f, 118.0f, 51.0f},
                    {4039.0005f, 1365.0f, 405.0f, 131.0f},
                    {4632.0f, 1554.0f, 187.0f, 230.0f},
                    {4820.0f, 1782.9998f, 83.0f, 88.0f},
                    {4871.0f, 1868.9998f, 85.0f, 79.0f},
                    {4924.0f, 1943.9998f, 78.0f, 64.0f},
                    {4890.0f, 1950.9998f, 87.0f, 123.0f},
                    {4972.0f, 2070.0f, 119.0f, 22.0f},
                    {5027.0f, 2090.0f, 59.0f, 72.0f},
                    {5082.0f, 2158.0f, 256.0f, 59.0f},
                    {5277.0f, 2117.0f, 59.0f, 40.0f},
                    {5190.0f, 2069.0f, 89.0f, 47.0f},
                    {5040.0f, 1972.0f, 186.0f, 99.0f},
                    {5046.0f, 1923.0f, 126.0f, 48.0f},
                    {5031.0f, 1833.0f, 87.0f, 90.0f},
                    {5114.0f, 1653.9999f, 110.0f, 183.00012f},
                    {5217.0f, 1595.9999f, 15.0f, 58.0f},
                    {5229.0f, 1465.9999f, 199.99951f, 133.0f},
                    {5423.9995f, 1450.9999f, 230.00049f, 97.0f},
                    {5505.0f, 1404.0f, 92.0f, 48.0f},
                    {5469.0f, 1229.0f, 72.0f, 178.0f},
                    {5533.0f, 1142.0f, 59.0f, 90.0f},
                    {5587.0f, 1091.0f, 60.0f, 51.0f},
                    {5647.0f, 1043.0f, 57.0f, 48.0f},
                    {5699.9995f, 987.0f, 123.0f, 59.0f},
                    {5821.9995f, 975.0f, 309.0f, 32.0f},
                    {6129.9995f, 943.0f, 28.0f, 42.0f},
                    {6156.9995f, 919.0f, 27.0f, 38.0f},
                    {6185.9995f, 862.0f, 219.0f, 72.0f},
                    {6278.9995f, 932.0f, 31.0f, 278.0f},
                    {6105.9995f, 1185.0f, 174.0f, 45.0f},
                    {6213.9995f, 1131.0f, 49.0f, 44.0f},
                    {6245.9995f, 1004.0f, 14.0f, 120.0f},
                    {6221.9995f, 946.0f, 39.0f, 46.0f},
                    {5952.9995f, 1131.0f, 153.0f, 59.0f},
                    {5954.9995f, 1178.0f, 0.0f, 1.0f},
                    {5781.9995f, 1177.0f, 172.0f, 273.00012f},
                    {5730.9995f, 1226.0001f, 51.0f, 90.0f},
                    {5837.9995f, 1449.0001f, 121.0f, 49.0f},
                    {5889.9995f, 1494.0001f, 115.0f, 136.0f},
                    {5836.9995f, 1627.0001f, 55.0f, 74.0f},
                    {5779.9995f, 1678.0f, 59.0f, 164.0f},
                    {5648.0f, 1726.0f, 131.99951f, 267.0f},
                    {5474.0f, 1776.0f, 179.0f, 49.0f},
                    {5528.0f, 1820.0f, 123.0f, 91.0f},
                    {5584.0f, 1906.0f, 65.0f, 89.0f},
                    {5487.0f, 1825.0f, 96.0f, 115.0f},
                    {5636.0f, 1992.0f, 180.0f, 77.0f},
                    {5745.0f, 2063.0f, 117.0f, 89.0f},
                    {5798.0f, 2148.0f, 193.0f, 148.0f},
                    {5599.0005f, 2336.0f, 244.0f, 47.0f},
                    {5701.0005f, 2277.0f, 109.0f, 61.0f},
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

        blackplayer = new Player(600,640);
        blackplayer.maxjump = 250;

        Pad = new Bouncepad(2057,1135);
        Pad2 = new Bouncepad(2936,1447);


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

       if(Pad.bounce(blackplayer)){
            blackplayer.jumpvelocity = 350;

        }if(Pad2.bounce(blackplayer)){
            blackplayer.jumpvelocity = 350;

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


        if(GameMode == true)
        {
            Tutorial.draw(myBatch);
            music.play();
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
        Pad.draw(myBatch);
        Pad2.draw(myBatch);
        myBatch.end();

    }

    @Override
    public void dispose () {
        myBatch.dispose();

    }
}




