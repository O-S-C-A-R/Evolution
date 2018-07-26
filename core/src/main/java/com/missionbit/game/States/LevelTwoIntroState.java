package com.missionbit.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.Evolution;
import com.missionbit.game.Utils;


public class LevelTwoIntroState extends State
{
    private Animation<TextureRegion> LevelTwoAnimation;
    public Vector3 touchPos;
    float LevelTwoAnimationTime = 0;

    public LevelTwoIntroState(GameStateManager gsm)
    {
        super(gsm);
        LevelTwoAnimation = Utils.LoadAnimation("images/Transitions/Level2Transition.png", 5, 9, 45, 0.1f);
        LevelTwoAnimationTime += Gdx.graphics.getDeltaTime();
        cam.setToOrtho(false, Evolution.VIEWPORT_WIDTH, Evolution.VIEWPORT_HEIGHT);

    }

    protected void handleInput()
    {

    }

    public void update(float dt)
    {
        handleInput();
    }

    public void render(SpriteBatch sb)
    {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        LevelTwoAnimationTime += Gdx.graphics.getDeltaTime();
        TextureRegion drawFrame = LevelTwoAnimation.getKeyFrame(LevelTwoAnimationTime, false);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(drawFrame,0,0);
        sb.end();
        if(LevelTwoAnimation.isAnimationFinished(LevelTwoAnimationTime))
        {
            sb.begin();
            gsm.clear();
            gsm.push(new LevelTwo(gsm));
            sb.end();
        }
    }
    public void dispose()
    {
        Utils.disposeAnimation(LevelTwoAnimation);
    }
}
