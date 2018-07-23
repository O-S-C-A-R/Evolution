package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.States.GameStateManager;
import com.missionbit.game.States.LevelOne;
import com.missionbit.game.States.LevelTwo;
import com.missionbit.game.States.RestartState;
import com.missionbit.game.States.TitleState;
import com.missionbit.game.States.TutorialState;
import com.missionbit.game.States.TutorialTwo;

public class Evolution extends ApplicationAdapter
{

    public static final int VIEWPORT_WIDTH = 960;
    public static final int VIEWPORT_HEIGHT = 540;
    public static final String TITLE = "Evolution";
    private GameStateManager gsm;
    private SpriteBatch batch;

    @Override
    public void create ()
    {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
<<<<<<< HEAD
        gsm.push(new LevelTwo(gsm));
=======
        gsm.push(new TitleState( gsm));
>>>>>>> 68de1b1e9b3eaa5039c54cc06b486c7790751858
        Gdx.gl.glClearColor(1, 1, 1, 1);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose ()
    {
        super.dispose();
        batch.dispose();
    }
}