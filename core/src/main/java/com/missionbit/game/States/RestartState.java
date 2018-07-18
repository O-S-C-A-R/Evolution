package com.missionbit.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.Evolution;



public class RestartState extends State
{
    private Texture RestartScreen;
    private Rectangle Restart;
    private Rectangle Exit;
    public Vector3 touchPos;


    public RestartState(GameStateManager gsm)
    {
        super(gsm);
        RestartScreen = new Texture("images/ui/DeathMenuTwo.png");
        Restart = new Rectangle(291, 250, 375, 71);
        Exit = new Rectangle(292, 171, 374, 71);
        cam.setToOrtho(false, Evolution.VIEWPORT_WIDTH, Evolution.VIEWPORT_HEIGHT);
    }

    protected void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(touchPos);

            if (Restart.contains(touchPos.x, touchPos.y))
            {
                gsm.pop();
                TutorialState.GameMode = true;
            }
            else if (Exit.contains(touchPos.x, touchPos.y))
            {
                gsm.set(new TitleState(gsm));
                TutorialState.GameMode = false;
            }


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
        sb.draw(RestartScreen, 0, 0);
        sb.end();
    }
    public void dispose()
    {
        RestartScreen.dispose();
        System.out.println("Disposing of Menu State");
    }
}

//{291.0, 250.0, 375.0, 77.0},
//        {292.0, 171.0, 374.0, 71.0},

