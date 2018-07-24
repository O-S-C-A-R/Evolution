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
    private Buttons SuperJump;
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
<<<<<<< HEAD
    private Buttons SuperJump;
    private Buttons SuperSpeed;
=======
>>>>>>> bb1dcb301a95053423bd28e753e6de4b77d5d023
    Vector3 touchPos;


    private boolean platformcheck = false;


    private static final int[][] PLAT_LOCS = new int[][]{

            {1773, 1566, 603, 22},
            {2721, 1357, 974, 1328},
            {772, 2040, 912, 45},
            {1306, 2333, 141, 10},
            {1351, 2690, 326, 118},
            {1915, 2695, 334, 307},
            {4831, 337, 164, 11},
            {4993, 353, 144, 14},
            {4978, 348, 27, 10},
            {5022, 368, 148, 22},
            {5045, 389, 153, 22},
            {5098, 414, 158, 363},
            {4993, 724, 104, 85},
            {4742, 730, 93, 78},
            {3053, 1083, 34, 298},
            {4588, 416, 153, 361},
            {4730, 370, 48, 39},
            {4778, 333, 52, 37},
            {3835, 616, 1309, 256},
            {742, 778, 126, 60},
            {741, 777, 164, 61},
            {1633, 900, 416, 461},
            {2722, 1359, 948, 1336},
            {2722, 1287, 363, 376},
            {2624, 1002, 154, 355},
            {3915, 3069, 739, 132},
            {4655, 2980, 123, 143},
            {4779, 2902, 109, 138},
            {5974, 2989, 310, 56},
            {5941, 2942, 300, 54},
            {5909, 2856, 287, 101},
            {5842, 2802, 294, 67},
            {5804, 2696, 255, 113},
            {5722, 2605, 275, 112},
            {5730, 2713, 107, 27},
            {5741, 2728, 142, 27},
            {5756, 2749, 151, 33},
            {5772, 2782, 205, 26},
            {5793, 2807, 199, 13},
            {5802, 2818, 168, 24},
            {5816, 2837, 147, 23},
            {5833, 2860, 135, 23},
            {5852, 2882, 181, 26},
            {5872, 2905, 179, 25},
            {5894, 2931, 153, 28},
            {5908, 2959, 153, 17},
            {5924, 2979, 110, 29},
            {5950, 3007, 77, 35},
            {5457, 2149, 444, 299},
            {5259, 1947, 397, 225},
            {4974, 1697, 437, 253},
            {6434, 3127, 256, 21},
            {6719, 2806, 236, 31},
            {6578, 3341, 456, 282},
            {6883, 3033, 429, 413},
            {6795, 2490, 414, 47},
            {6820, 2431, 597, 55},
            {6960, 2381, 557, 56},
            {7030, 2341, 547, 46},
            {7080, 2294, 548, 51},
            {6286, 1288, 400, 773},
            {6674, 1232, 457, 357},
            {6876, 1584, 860, 302},
            {5118, 1143, 850, 324},
            {4995, 1165, 178, 155},
            {5035, 1311, 143, 47},
            {5059, 1350, 215, 63},
            {5096, 1411, 192, 54},


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
            {2470, 3211, 168, 19},
            {2759, 3347, 954, 25},
            {950, 2464, 4707, 230},
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
<<<<<<< HEAD
=======
        SuperJump = new Buttons(690, -50, "images/ui/SuperJump.png");
>>>>>>> bb1dcb301a95053423bd28e753e6de4b77d5d023

        blackplayer.maxjump = 450;

        Pad = new Bouncepad(1531, 934);
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
        Spider = new Enemies(2770, 3380, 2770, 3100);
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
                        blackplayer.jumpvelocity = 700;
                    }
                }
                if (touchPos.x > LeftButton.getX() && touchPos.x < LeftButton.getX() + LeftButton.getWidth()) {
                    if (touchPos.y > LeftButton.getY() && touchPos.y < LeftButton.getY() + LeftButton.getHeight()) {
                        blackplayer.Moveleft();
                    }

                }
                if (touchPos.x > SuperJump.getX() && touchPos.x < SuperJump.getX() + SuperJump.getWidth()&& blackplayer.jumpvelocity==0) {
                    if (touchPos.y > SuperJump.getY() && touchPos.y < SuperJump.getY() + SuperJump.getHeight() && blackplayer.touchplatform) {
                        blackplayer.jumpvelocity = 690;

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
            System.out.println(blackplayer.jumpvelocity);
            if (Gdx.input.isKeyPressed(Input.Keys.L) && blackplayer.touchplatform || Gdx.input.isKeyPressed(Input.Keys.E) && !blackplayer.nojump) {
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

            if (Pad.bounce(blackplayer)) {
                blackplayer.jumpvelocity = 650;
                pad.play();
            }
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

<<<<<<< HEAD
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
=======
            blackplayer.UpdateLast();

            camera.position.set(CAMERA_OFFSET_X, CAMERA_OFFSET_Y, 0);
            camera.update();
            myBatch.setProjectionMatrix(camera.combined);
            myBatch.begin();

            LeftButton.draw(myBatch);
            RightButton.draw(myBatch);
            UpButton.draw(myBatch);
            SuperJump.draw(myBatch);
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
>>>>>>> bb1dcb301a95053423bd28e753e6de4b77d5d023

            myBatch.end();


        }

        @Override
        public void dispose () {
            myBatch.dispose();
            music.stop();
            music.dispose();
        }
    }





