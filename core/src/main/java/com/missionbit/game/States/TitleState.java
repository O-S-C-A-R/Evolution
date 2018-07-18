package com.missionbit.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.Buttons;
import com.missionbit.game.Evolution;

public class TitleState extends State
{
    public Texture TitleScreen;
    public Buttons Start;
    public Vector3 touchPos;

    public TitleState(GameStateManager gsm)
    {
        super(gsm);
        TitleScreen = new Texture(Gdx.files.internal("images/ui/TitleScreen.png"));
        Start = new Buttons(400,44, "images/ui/Start.png");
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
    }
    public void dispose()
    {
        TitleScreen.dispose();
        System.out.println("Disposing of Title State");
    }
}

//412.0, 84.0, 147.0, 30.000015},
//        {908.0, 20.0, 40.0, 36.0},
