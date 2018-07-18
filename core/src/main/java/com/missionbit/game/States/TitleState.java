package com.missionbit.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.Buttons;
import com.missionbit.game.Evolution;
import com.missionbit.game.Rumble;

public class TitleState extends State
{
    public Texture TitleScreen;
    public Buttons Start;
    public Vector3 touchPos;
    private Rumble rumble;
    private long Time;

    public TitleState(GameStateManager gsm)
    {
        super(gsm);
        Time = System.currentTimeMillis();
        TitleScreen = new Texture(Gdx.files.internal("images/ui/TitleScreen.png"));
        Start = new Buttons(400,44, "images/ui/Start.png");
        rumble = new Rumble();
        cam.setToOrtho(false, Evolution.VIEWPORT_WIDTH, Evolution.VIEWPORT_HEIGHT);

    }

    protected void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            if (Start.HandleClick(touchPos))
            {
                gsm.set(new TutorialState(gsm));
                TutorialState.GameMode = true;
            }

            //gsm.pop();

        }
    }

    public void update(float dt)
    {
        handleInput();
    }
    public void render(SpriteBatch sb)
    {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(TitleScreen, 0, 0);
        sb.end();

//        for (int i = 100; i < System.currentTimeMillis() - Time; i+=100){
            rumble.rumble(2f, 3f);
            if (Rumble.getRumbleTimeLeft() > 0) {
                Rumble.tick(Gdx.graphics.getDeltaTime());
                cam.translate(Rumble.getPos());
            }
            Time = System.currentTimeMillis();

        cam.update();
//        }
//        if (System.currentTimeMillis() - Time > 500) {
//            rumble.rumble(2f, 1f);
//            if (Rumble.getRumbleTimeLeft() > 0) {
//                Rumble.tick(Gdx.graphics.getDeltaTime());
//                cam.translate(Rumble.getPos());
//            }
//            Time = System.currentTimeMillis();
//        }
//        cam.update();
//        else {
//            rumble.setCurrentPower(0f);
//        }
    }
    public void dispose()
    {
        TitleScreen.dispose();
        System.out.println("Disposing of Title State");
    }
}

//412.0, 84.0, 147.0, 30.000015},
//        {908.0, 20.0, 40.0, 36.0},
