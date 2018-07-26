package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.States.GameStateManager;
import com.missionbit.game.States.LevelTwo;
import com.missionbit.game.States.TitleState;
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
        gsm.push(new TitleState(gsm));
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