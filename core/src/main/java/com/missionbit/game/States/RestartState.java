package com.missionbit.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.Evolution;

public class RestartState extends State
{
    private Texture RestartScreen;


    public RestartState(GameStateManager gsm)
    {
        super(gsm);
        RestartScreen = new Texture("images/ui/DeathMenuTwo.png");
        cam.setToOrtho(false, Evolution.VIEWPORT_WIDTH, Evolution.VIEWPORT_HEIGHT);
    }

    protected void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            gsm.set(new TutorialState(gsm));
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
        RestartScreen.dispose();;
        System.out.println("Disposing of Menu State");
    }
}
