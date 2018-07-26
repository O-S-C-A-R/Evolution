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
public class FinalLevel extends Levelmaker {

    /* game constants */
    private static final int CAMERA_OFFSET_X = 0;
    private static final int CAMERA_OFFSET_Y = 150;
    private static final int VIEWPORT_WIDTH = 960;
    private static final int VIEWPORT_HEIGHT = 540;
    //private Particles Portal;
    private OrthographicCamera camera;
//    private Particles Portal;

    private Player blackplayer;
    private Random randomSource;
    private Sprite LVLDrawer;
    private Sprite LVLDrawer2;
    private Sprite LVLDrawer3;
    private Sprite LVLDrawer4;


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
            {409, 545, 8640, 138},
            {510, 608, 0, 0},
            {418, 608, 92, 5524},
            {407, 5973, 8635, 147},
            {9017, 542, 29, 5579},
            {6163, 5332, 418, 46},
            {5256, 5478, 542, 46},
            {6107, 5843, 532, 41},
            {7133, 5548, 486, 50},
            {7151, 4899, 352, 52},
            {7736, 5036, 419, 50},
            {7668, 4511, 390, 49},
            {6907, 4288, 483, 60},
            {7277, 4034, 571, 55},
            {6621, 3695, 559, 40},
            {6197, 3940, 446, 49},
            {8611, 4813, 434, 411},
            {8518, 3575, 249, 68},
            {8180, 2924, 366, 71},
            {7815, 2420, 240, 177},
            {8028, 2554, 29, 125},
            {7773, 2417, 115, 125},
            {7732, 2419, 146, 67},
            {6993, 2417, 745, 35},
            {4836, 2419, 1593, 30},
            {5106, 2421, 410, 229},
            {4835, 2420, 416, 352},
            {5238, 3186, 28, 440},
            {5232, 3414, 295, 215},
            {5474, 3599, 2585, 29},
            {7582, 3418, 478, 213},
            {8039, 3187, 22, 442},
            {8854, 2021, 44, 560},
            {8849, 1375, 48, 422},
            {8529, 1126, 264, 69},
            {8853, 754, 42, 409},
            {8969, 749, 21, 3318},
            {8554, 5116, 490, 577},
            {8476, 5552, 571, 420},
            {8316, 5810, 199, 165},
            {4108, 5440, 509, 620},
            {3896, 4786, 248, 233},
            {1317, 4789, 2599, 26},
            {1322, 4789, 273, 242},
            {718, 2427, 40, 2971},
            {506, 2427, 252, 63},
            {502, 5397, 841, 531},
            {1336, 5709, 288, 292},
            {4618, 5734, 144, 256},
            {4761, 5812, 110, 210},
            {4620, 5447, 55, 350},
            {4813, 4803, 533, 55},
            {5506, 4914, 419, 56},
            {8813, 4252, 234, 579},
            {6450, 2123, 476, 74},
            {4734, 1823, 1691, 59},
            {6990, 1846, 1057, 37},
            {8288, 2338, 47, 307},
            {8287, 1982, 51, 267},
            {8290, 1751, 42, 148},
            {8131, 1681, 379, 73},
            {7854, 681, 477, 139},
            {8314, 667, 64, 140},
            {8367, 670, 47, 71},
            {7850, 661, 21, 130},
            {7793, 662, 68, 94},
            {6893, 664, 641, 230},
            {5752, 670, 691, 224},
            {4735, 1046, 498, 835},
            {5232, 1613, 314, 268},
            {5223, 1044, 38, 586},
            {4057, 1031, 392, 791},
            {840, 1596, 2987, 210},
            {1212, 1812, 1403, 167},
            {2612, 1808, 50, 111},
            {2663, 1807, 32, 70},
            {2866, 1804, 285, 88},
            {3141, 1804, 41, 66},
            {3176, 1790, 23, 36},
            {2845, 1839, 29, 54},
            {2923, 2152, 1252, 34},
            {4400, 2427, 168, 48},
            {4021, 2400, 373, 351},
            {3714, 2400, 310, 265},
            {3028, 2416, 690, 147},
            {1976, 2401, 1052, 57},
            {1230, 2398, 242, 53},
            {1080, 2432, 24, 591},
            {1078, 2432, 124, 27},
            {1216, 2761, 36, 891},
            {1240, 3363, 378, 291},
            {1603, 3502, 778, 151},
            {2380, 3623, 323, 29},
            {1045, 4050, 450, 73},
            {741, 4800, 295, 54},
            {1082, 3472, 135, 41},
            {752, 3470, 144, 48},
            {751, 2889, 186, 47},
            {1076, 2187, 210, 45},
            {675, 1635, 39, 698},
            {485, 1520, 176, 80},
            {579, 1848, 35, 467},
            {570, 1244, 45, 270},
            {1441, 928, 309, 56},
            {2716, 672, 1108, 83},
            {2173, 1217, 1167, 46},
            {6435, 1375, 450, 75},
            {8525, 2282, 286, 67},
            {4684, 1869, 25, 96},
            {4682, 1950, 123, 16},
            {4884, 1949, 145, 20},
            {5104, 1949, 152, 20},
            {3814, 4787, 206, 138},
            {5325, 3753, 566, 50},
            {5291, 4100, 483, 55},
            {5439, 4484, 393, 45},
            {4827, 4806, 509, 47},
            {4405, 3703, 465, 81},
            {6195, 3949, 446, 43},
            {6628, 3684, 549, 59},
            {7284, 4032, 565, 59},
            {6920, 4293, 456, 52},
            {7681, 4511, 381, 54},
            {8519, 3575, 244, 74},
            {8202, 2923, 324, 71},
            {8526, 2276, 284, 79},
            {8135, 1675, 370, 78},
            {720, 4805, 320, 47},

            {3748, 4786, 129, 57},
            {3117, 4314, 987, 36},
            {3109, 4239, 326, 37},
            {3493, 4241, 302, 29},
            {3862, 4239, 255, 35},
            {4411, 3695, 432, 90},
            {4476, 3609, 297, 61},
            {3129, 3620, 906, 33},
            {3510, 3357, 525, 263},
            {4004, 2985, 34, 629},
            {4476, 3223, 417, 97},
            {409, 545, 8640, 138},
            {510, 608, 0, 0},
            {418, 608, 92, 5524},
            {407, 5973, 8635, 147},
            {9017, 542, 29, 5579},
            {6163, 5332, 418, 46},
            {5256, 5478, 542, 46},
            {6107, 5843, 532, 41},
            {7133, 5548, 486, 50},
            {7151, 4899, 352, 52},
            {7736, 5036, 419, 50},
            {7668, 4511, 390, 49},
            {6907, 4288, 483, 60},
            {7277, 4034, 571, 55},
            {6621, 3695, 559, 40},
            {6197, 3940, 446, 49},
            {8611, 4813, 434, 411},
            {8518, 3575, 249, 68},
            {8180, 2924, 366, 71},
            {7815, 2420, 240, 177},
            {8028, 2554, 29, 125},
            {7773, 2417, 115, 125},
            {7732, 2419, 146, 67},
            {6993, 2417, 745, 35},
            {4836, 2419, 1593, 30},
            {5106, 2421, 410, 229},
            {4835, 2420, 416, 352},
            {5238, 3186, 28, 440},
            {5232, 3414, 295, 215},
            {5474, 3599, 2585, 29},
            {7582, 3418, 478, 213},
            {8039, 3187, 22, 442},
            {8854, 2021, 44, 560},
            {8849, 1375, 48, 422},
            {8529, 1126, 264, 69},
            {8853, 754, 42, 409},
            {8969, 749, 21, 3318},
            {8554, 5116, 490, 577},
            {8476, 5552, 571, 420},
            {8316, 5810, 199, 165},
            {4108, 5440, 509, 620},
            {3896, 4786, 248, 233},
            {1317, 4789, 2599, 26},
            {1322, 4789, 273, 242},
            {718, 2427, 40, 2971},
            {506, 2427, 252, 63},
            {502, 5397, 841, 531},
            {1336, 5709, 288, 292},
            {4618, 5734, 144, 256},
            {4761, 5812, 110, 210},
            {4620, 5447, 55, 350},
            {4813, 4803, 533, 55},
            {5506, 4914, 419, 56},
            {8813, 4252, 234, 579},
            {6450, 2123, 476, 74},
            {4734, 1823, 1691, 59},
            {6990, 1846, 1057, 37},
            {8288, 2338, 47, 307},
            {8287, 1982, 51, 267},
            {8290, 1751, 42, 148},
            {8131, 1681, 379, 73},
            {7854, 681, 477, 139},
            {8314, 667, 64, 140},
            {8367, 670, 47, 71},
            {7850, 661, 21, 130},
            {7793, 662, 68, 94},
            {6893, 664, 641, 230},
            {5752, 670, 691, 224},
            {4735, 1046, 498, 835},
            {5232, 1613, 314, 268},
            {5223, 1044, 38, 586},
            {4057, 1031, 392, 791},
            {840, 1596, 2987, 210},
            {1212, 1812, 1403, 167},
            {2612, 1808, 50, 111},
            {2663, 1807, 32, 70},
            {2866, 1804, 285, 88},
            {3141, 1804, 41, 66},
            {3176, 1790, 23, 36},
            {2845, 1839, 29, 54},
            {2923, 2152, 1252, 34},
            {4400, 2427, 168, 48},
            {4021, 2400, 373, 351},
            {3714, 2400, 310, 265},
            {3028, 2416, 690, 147},
            {1976, 2401, 1052, 57},
            {1230, 2398, 242, 53},
            {1080, 2432, 24, 591},
            {1078, 2432, 124, 27},
            {1216, 2761, 36, 891},
            {1240, 3363, 378, 291},
            {1603, 3502, 778, 151},
            {2380, 3623, 323, 29},
            {1045, 4050, 450, 73},
            {741, 4800, 295, 54},
            {1082, 3472, 135, 41},
            {752, 3470, 144, 48},
            {751, 2889, 186, 47},
            {838, 1184, 441, 622},
            {1195, 1452, 376, 358},
            {4410, 1358, 148, 52},
            {4653, 1632, 138, 48},
            {6451, 2120, 461, 75},
            {2928, 2149, 1250, 34},
            {4357, 2426, 216, 49},
            {4464, 3230, 442, 87},
            {4402, 3706, 464, 77},
            {719, 2892, 217, 40},
            {723, 3472, 168, 39},
            {1077, 3473, 143, 39},
            {1050, 4050, 436, 68},
            {3898, 4789, 249, 233},
    {1076, 2187, 210, 45},
            {675, 1635, 39, 698},
            {485, 1520, 176, 80},
            {579, 1848, 35, 467},
            {570, 1244, 45, 270},
            {1441, 928, 309, 56},
            {2716, 672, 1108, 83},
            {2173, 1217, 1167, 46},
            {6435, 1375, 450, 75},
            {8525, 2282, 286, 67},
            {4684, 1869, 25, 96},
            {4682, 1950, 123, 16},
            {4884, 1949, 145, 20},
            {5104, 1949, 152, 20},
            {3814, 4787, 206, 138},
            {3748, 4786, 129, 57},
            {3117, 4314, 987, 36},
            {3109, 4239, 326, 37},
            {3493, 4241, 302, 29},
            {3862, 4239, 255, 35},
            {4411, 3695, 432, 90},
            {4476, 3609, 297, 61},
            {3129, 3620, 906, 33},
            {3510, 3357, 525, 263},
            {4004, 2985, 34, 629},
            {4476, 3223, 417, 97},
    };





    private static final float[][] spike_locs = new float[][]{
            {0, 0, 0, 0,0,0},
            {6446, 686, 6445, 817, 6896, 817, 6897, 682, 6446, 685, },
            {5594, 1887, 5362, 1921, 4808, 1908, 4763, 1901, 4761, 1879, 5572, 1879, },
            {4425, 1832, 4335, 1859, 4160, 1858, 4086, 1827, },


    };

    private static ArrayList<Platform> platforms;

    private ShapeRenderer debugRenderer;

    public FinalLevel(GameStateManager gsm) {
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

        blackplayer = new Player(700, 867,"images/player/GreenPlayer.png","images/player animation/GreenPlayerDeath.png");
        SuperJump = new Buttons(690, -50, "images/ui/SuperJump.png");

        blackplayer.maxjump = 450;

//        Pad2 = new Bouncepad(1617, 1095);
//        Pad3 = new Bouncepad(1489, 1185);
//        Pad4 = new Bouncepad(1601, 1797);
//        Pad5 = new Bouncepad(1479, 1406);
//        Pad6 = new Bouncepad(2557, 1365);
//        Pad7 = new Bouncepad(2697, 1763);
//        Pad8 = new Bouncepad(2510, 1934);
//        Pad9 = new Bouncepad(2691, 2068);
//        Pad10 = new Bouncepad(820, 2045);
//        Pad12 = new Bouncepad(776, 2389);
//        Pad11 = new Bouncepad(929, 2547);
//        Pad13 = new Bouncepad(775, 2676);


        LeftButton = new Buttons(-420, -100, "images/ui/LeftButtonPurple.png");
        RightButton = new Buttons(-320, -100, "images/ui/RightButtonBlue.png");
        UpButton = new Buttons(340, -100, "images/ui/UpButtonPurple.png");
        SuperJump = new Buttons(340,-30 ,"images/ui/SuperJump.png");
        SuperSpeed = new Buttons(340, 40, "images/ui/SuperSpeed.png");


        FullLivesBlue = new Buttons(-490, 350, "images/ui/FullLivesBlue.png");
        TwoLivesBlue = new Buttons(-490, 350, "images/ui/TwoLivesPurple.png");
        OneLifeBlue = new Buttons(-490, 350, "images/ui/OneLiveBlue.png");
        SuperJump = new Buttons(340,100,"images/ui/SuperJump.png");


        randomSource = new Random();
        // TODO Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        //TODO Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //LOAD IMAGES
        platforms = new ArrayList<Platform>();


        LVLDrawer = new Sprite(new Texture(Gdx.files.internal("images/map/FinalLevel1.png")));

        LVLDrawer3 = new Sprite(new Texture(Gdx.files.internal("images/map/FinalLevel3.png")));
        LVLDrawer3.setY(3250);
        LVLDrawer2 = new Sprite(new Texture(Gdx.files.internal("images/map/FinalLevel2.png")));
        LVLDrawer2.setX(4750);

        LVLDrawer4 = new Sprite(new Texture(Gdx.files.internal("images/map/FinalLevel4.png")));
        LVLDrawer4.setX(4750);
        LVLDrawer4.setY(3250);




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
                        blackplayer.jumpvelocity = 1200;

                    }
                }
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

        if (Gdx.input.isKeyPressed(Input.Keys.L) && blackplayer.touchplatform || Gdx.input.isKeyPressed(Input.Keys.E) && !blackplayer.nojump) {
            System.out.println("test3 " + blackplayer.nojump);
            blackplayer.jumpvelocity = 1200;
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

        if (GameMode == true) {
            System.out.println("Playing");
            LVLDrawer.draw(myBatch);
            LVLDrawer2.draw(myBatch);
            LVLDrawer3.draw(myBatch);
            LVLDrawer4.draw(myBatch);





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
//        Portal.draw(myBatch);

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
//            Portal.drawDebug(debugRenderer);

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





