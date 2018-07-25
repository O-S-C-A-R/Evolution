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
    private static final int CAMERA_OFFSET_X = 0;
    private static final int CAMERA_OFFSET_Y = 150;
    private static final int VIEWPORT_WIDTH = 960;
    private static final int VIEWPORT_HEIGHT = 540;
    //private Particles Portal;
    private OrthographicCamera camera;
    private Particles Portal;

    private Player blackplayer;
    private Random randomSource;
    private Sprite LVLDrawer;
    private Sprite LVLDrawer2;
    private Sound JumpSound;
    private Sound pad;
    private Sprite Bouncepad;
    private Music music;

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
    private boolean showDebug = false;


    private ArrayList<Spikes> spikes = new ArrayList<Spikes>();
    //protected static boolean GameMode = true;

    private Buttons LeftButton;
    private Buttons RightButton;
    private Buttons UpButton;
    private Buttons SuperJump;
    private Buttons SuperSpeed;
    Vector3 touchPos;


    private boolean platformcheck = false;


    private static final int[][] PLAT_LOCS = new int[][]{
            {741, 725, 161, 113},
            {780, 792, 182, 87},
            {820, 848, 117, 66},
            {895, 784, 259, 108},
            {1083, 874, 749, 62},
            {1636, 919, 368, 438},
            {427, 723, 334, 65},
            {403, 766, 106, 449},
            {476, 1148, 410, 38},
            {822, 1099, 400, 70},
            {1068, 1062, 417, 63},
            {1428, 1102, 52, 774},
            {1468, 1742, 1035, 298},
            {1771, 1560, 601, 30},
            {1959, 931, 764, 431},
            {2719, 1341, 85, 1353},
            {953, 2463, 1784, 227},
            {1300, 2332, 151, 12},
            {2674, 1341, 83, 19},
            {3101, 730, 1733, 583},
            {577, 2019, 1109, 68},
            {547, 2076, 225, 1658},
            {1354, 2657, 321, 152},
            {1915, 2656, 333, 348},
            {2467, 3210, 169, 26},
            {2759, 3358, 947, 11},
            {3912, 2623, 744, 581},
            {4655, 2620, 135, 506},
            {4777, 2589, 116, 455},
            {4872, 2589, 151, 369},
            {4657, 2550, 1049, 146},
            {5978, 2978, 302, 65},
            {5951, 2869, 38, 151},
            {5928, 2944, 324, 39},
            {5898, 2865, 308, 81},
            {5844, 2797, 343, 82},
            {5791, 2727, 331, 87},
            {5741, 2644, 280, 98},
            {5696, 2568, 265, 137},
            {5860, 2872, 58, 41},
            {5804, 2804, 63, 30},
            {5757, 2738, 105, 48},
            {5635, 2431, 294, 138},
            {5201, 2159, 701, 297},
            {5252, 1951, 402, 255},
            {2993, 1705, 2416, 284},
            {3131, 1385, 138, 357},
            {3097, 1449, 34, 66},
            {3057, 1488, 54, 65},
            {2870, 1524, 196, 29},
            {2824, 1440, 68, 118},
            {2803, 1294, 68, 171},
            {2856, 1274, 82, 51},
            {2927, 1242, 185, 48},
            {6273, 1281, 403, 772},
            {6677, 1351, 370, 239},
            {6876, 1560, 389, 325},
            {7204, 1883, 401, 220},
            {7191, 2075, 465, 174},
            {7166, 2165, 64, 332},
            {7088, 2207, 83, 293},
            {7052, 2311, 39, 199},
            {6996, 2360, 67, 177},
            {6801, 2464, 652, 70},
            {7204, 2516, 429, 903},
            {6874, 3036, 351, 568},
            {6580, 3329, 362, 511},
            {6322, 3616, 275, 421},
            {5535, 3814, 840, 203},
            {5309, 3110, 344, 735},
            {6426, 3130, 263, 20},
            {6710, 2810, 257, 29},
            {6045, 1251, 261, 101},
            {5736, 1338, 322, 52},
            {5115, 1373, 848, 97},
            {5065, 1303, 71, 101},
            {5029, 1286, 49, 74},
            {4994, 725, 48, 610},
            {4742, 309, 324, 38},
            {5057, 341, 93, 86},
            {5100, 415, 260, 370},
            {4992, 699, 112, 106},
            {4582, 694, 253, 104},
            {1101, 1061, 380, 964},
            {1422, 1738, 1084, 302},
            {2720, 1362, 154, 378},
            {2715, 1308, 87, 19},
            {2701, 1306, 175, 78},
            {2721, 1628, 211, 947},
            {953, 2457, 1802, 236},
            {769, 1900, 913, 185},
            {761, 1737, 1744, 301},
            {1633, 934, 1204, 429},

            {4637, 334, 151, 62},
            {2716, 1339, 155, 66},
            {4637, 381, 102, 395},
    };






    private static final float[][] spike_locs = new float[][]{
            {3980, 2715, 4154, 2723, 4189, 2768, 4235, 2770, 4260, 2728, 4400, 2705, 4454, 3027, 4463, 2767, 4548, 2704, 4620, 2704, 4619, 2690, 4441, 2692, 3986, 2694, 3981, 2717,},
            {4706, 2699, 4775, 2709, 4866, 2745, 4892, 2790, 4890, 2689, 4698, 2689, 4715, 2702,},
            {6284, 3006, 6289, 2587, 6252, 2587, 6249, 2903, 6210, 2884, 6205, 2727, 6175, 2727, 6171, 2822, 6155, 2807, 6146, 2728, 6117, 2762, 6107, 2744, 6101, 2561, 6064, 2561, 6063, 2670, 6038, 2653, 6041, 2580, 6010, 2579, 6001, 2596, 5982, 2577, 5980, 2295, 5946, 2304, 5943, 2516, 5930, 2493, 5935, 2390, 5905, 2373, 5909, 2487, 6284, 3001,},
            {6432, 3136, 6524, 3061, 6561, 3116, 6667, 3101, 6690, 3133, 6443, 3136,},
            {6713, 2812, 6839, 2752, 6951, 2813,},
            {5971, 1459, 6026, 1426, 6143, 1409, 6189, 1406, 6241, 1432, 6258, 1459, 6239, 1405, 6176, 1332, 6128, 1322, 6036, 1381, 5970, 1459,},

            {1680, 2755, 1726, 2750, 1765, 2758, 1803, 2750, 1840, 2750, 1868, 2759, 1894, 2752, 1898, 2746, 1914, 2749, 1914, 2689, 1677, 2696, 1678, 2757,},
            {2322, 2705, 2402, 2777, 2429, 2860, 2578, 2904, 2603, 2898, 2500, 2741, 2505, 2693, 2326, 2704,},
            {1972, 1362, 1993, 1410, 2042, 1359, 2087, 1434, 2128, 1352, 2148, 1396, 2183, 1360, 2223, 1412, 2267, 1358, 2284, 1382, 2313, 1358, 2355, 1412, 2371, 1378, 2386, 1395, 2399, 1379, 2435, 1434, 2487, 1359, 2508, 1390, 2534, 1364, 2537, 1403, 2585, 1373, 2631, 1367, 2637, 1359, 1973, 1362,},
            {1782, 1571, 1793, 1554, 2330, 1561, 2330, 1576,},
            {1878, 1552, 1892, 1532, 1905, 1556, 1876, 1553, 1885, 1544,},
            {2152, 2038, 2131, 2062, 2100, 2128, 2098, 2143, 2090, 2144, 2073, 2177, 2051, 2132, 2040, 2127, 2039, 2112, 2015, 2042,},
            {1308, 2328, 1444, 2330, 1424, 2319, 1314, 2319, 1310, 2328,},
    };

    private static ArrayList<Platform> platforms;

    private ShapeRenderer debugRenderer;

    public LevelTwo(GameStateManager gsm) {
        super(gsm);
        music = Gdx.audio.newMusic(Gdx.files.internal("music/Howling-wind.mp3"));
        music.setLooping(true);
        music.setVolume(0.4f);
        //music.play();
        JumpSound = Gdx.audio.newSound(Gdx.files.internal("music/Swooshing.mp3"));
        JumpSound.setLooping(1, false);
        JumpSound.setVolume(1, 0.01f);
        tooclose = new Color(1, 1, 1, 1);
        pad = Gdx.audio.newSound(Gdx.files.internal("music/My Song 4.mp3"));
        pad.setLooping(1, false);
        pad.setVolume(1, 0.01f);

        blackplayer = new Player(700, 867);
        SuperJump = new Buttons(690, -50, "images/ui/SuperJump.png");

        blackplayer.maxjump = 450;

        Pad2 = new Bouncepad(1617, 1095);
        Pad3 = new Bouncepad(1489, 1185);
        Pad4 = new Bouncepad(1601, 1797);
        Pad5 = new Bouncepad(1479, 1406);
        Pad6 = new Bouncepad(2557, 1365);
        Pad7 = new Bouncepad(2697, 1763);
        Pad8 = new Bouncepad(2510, 1934);
        Pad9 = new Bouncepad(2691, 2068);
        Pad10 = new Bouncepad(820, 2045);
        Pad12 = new Bouncepad(776, 2389);
        Pad11 = new Bouncepad(929, 2547);
        Pad13 = new Bouncepad(775, 2676);


        LeftButton = new Buttons(-420, -100, "images/ui/LeftButtonPurple.png");
        RightButton = new Buttons(-320, -100, "images/ui/RightButtonPurple.png");
        UpButton = new Buttons(340, -100, "images/ui/UpButtonPurple.png");
        SuperJump = new Buttons(340,-30 ,"images/ui/SuperJump.png");
        SuperSpeed = new Buttons(340, 40, "images/ui/SuperSpeed.png");


        FullLivesBlue = new Buttons(-490, 350, "images/ui/FullLivesPurple.png");
        TwoLivesBlue = new Buttons(-490, 350, "images/ui/TwoLivesPurple.png");
        OneLifeBlue = new Buttons(-490, 350, "images/ui/OneLivePurple.png");
        SuperJump = new Buttons(340,100,"images/ui/SuperJump.png");


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

        LVLDrawer = new Sprite(new Texture(Gdx.files.internal("images/map/Level 2Full.png")));
        LVLDrawer.setX(4200);

        LVLDrawer2 = new Sprite(new Texture(Gdx.files.internal("images/map/Level 2otherhalf.png")));


        // Initialize platforms
        platforms = new ArrayList<Platform>();

        for (int[] loc : PLAT_LOCS) {
            platforms.add(new Platform(loc[0], loc[1], loc[2], loc[3]));
        }

        for (float[] loc : spike_locs) {
            spikes.add(new Spikes(loc));
        }


        // velocity = new Vector2(0, 0);
        Spider = new Enemies(2770, 3360, 2770, 3100);
    }

    @Override

    protected void handleInput() {
        for (int i = 0; i < 10; i++) {

            if (Gdx.input.isTouched(i)) {
                touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touchPos);
                System.out.println(touchPos);
                System.out.println(blackplayer.nojump);
                if (touchPos.x > SuperJump.getX() && touchPos.x < SuperJump.getX() + SuperJump.getWidth()&&blackplayer.jumpvelocity==0) {
                    if (touchPos.y > SuperJump.getY() && touchPos.y < SuperJump.getY() + SuperJump.getHeight() && blackplayer.touchplatform) {
                        pad.play();
                        blackplayer.jumpvelocity = 720;
                    }
                }
                if (touchPos.x > LeftButton.getX() && touchPos.x < LeftButton.getX() + LeftButton.getWidth()) {
                    if (touchPos.y > LeftButton.getY() && touchPos.y < LeftButton.getY() + LeftButton.getHeight()) {
                        blackplayer.Moveleft();
                    }

                }
                if (touchPos.x > SuperJump.getX() && touchPos.x < SuperJump.getX() + SuperJump.getWidth()&& blackplayer.jumpvelocity==0) {
                    if (touchPos.y > SuperJump.getY() && touchPos.y < SuperJump.getY() + SuperJump.getHeight() && blackplayer.touchplatform) {
                        blackplayer.jumpvelocity = 710;

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

            if (Gdx.input.isKeyPressed(Input.Keys.L) && blackplayer.touchplatform || Gdx.input.isKeyPressed(Input.Keys.E) && !blackplayer.nojump) {
                System.out.println("test3 " + blackplayer.nojump);
                blackplayer.jumpvelocity = 700;
                pad.play();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                blackplayer.Xvelocity = -600;
                blackplayer.DRAG = 20;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                blackplayer.Xvelocity = 600;
                blackplayer.DRAG = -20;
            }

        }

        @Override
        public void update ( float dt)
        {
            handleInput();
            camera.update();
            blackplayer.Update();

        }
        @Override
        public void render (SpriteBatch myBatch){
            // Clear the screen
            Gdx.gl.glClearColor(tooclose.r, tooclose.g, tooclose.b, tooclose.a);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            //Set up our camera
            myBatch.setProjectionMatrix(camera.combined);


            if (Pad2.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
                pad.play();
            }
            if (Pad3.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
                pad.play();
            }
            if (Pad4.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
                pad.play();
            }
            if (Pad5.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
                pad.play();
            }
            if (Pad6.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 850;
                pad.play();
            }
            if (Pad7.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
                pad.play();
            }
            if (Pad8.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
                pad.play();
            }
            if (Pad9.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
                pad.play();
            }
            if (Pad10.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 750;
                pad.play();
            }
            if (Pad11.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
                pad.play();
            }
            if (Pad12.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 550;
                pad.play();
            }
            if (Pad13.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
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
            if (Portal.hit(blackplayer.getBounding())) {
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

            if (GameMode == true) {
                System.out.println("Playing");
                LVLDrawer.draw(myBatch);
                LVLDrawer2.draw(myBatch);




                //music.play();
            } else {
                System.out.println("stopping");
                blackplayer.reset(720, 868);
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
            if(!platformcheck){
    blackplayer.nojump = true;

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
            blackplayer.UpdateLast();
            myBatch.end();
            camera.position.set(CAMERA_OFFSET_X, CAMERA_OFFSET_Y, 0);
            camera.update();
            myBatch.setProjectionMatrix(camera.combined);
            myBatch.begin();

            LeftButton.draw(myBatch);
            RightButton.draw(myBatch);
            UpButton.draw(myBatch);
            SuperJump.draw(myBatch);
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
            } else if (blackplayer.Lives == 2) {
                TwoLivesBlue.draw(myBatch);
                tooclose.r = 0;
                tooclose.g = 0;
                tooclose.b = 0;
                tooclose.a = 0;

            } else if (blackplayer.Lives == 1) {
                OneLifeBlue.draw(myBatch);
                tooclose.r = 0;
                tooclose.g = 0;
                tooclose.b = 0;
                tooclose.a = 0;

            } else if (blackplayer.Lives == 0 && blackplayer.deathanimationfin()) {

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





