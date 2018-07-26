package com.missionbit.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.Evolution;
import com.missionbit.game.Utils;

public class LevelOneIntroState extends State
{
    private Animation<TextureRegion> LevelOneAnimation;
    public Vector3 touchPos;
    float LevelOneAnimationTime = 0;

    public LevelOneIntroState(GameStateManager gsm)
    {
        super(gsm);
        LevelOneAnimation = Utils.LoadAnimation("images/Transitions/LevelOneTransition.png", 5, 9, 43, 0.1f);
        LevelOneAnimationTime += Gdx.graphics.getDeltaTime();
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
        LevelOneAnimationTime += Gdx.graphics.getDeltaTime();
        TextureRegion drawFrame = LevelOneAnimation.getKeyFrame(LevelOneAnimationTime, false);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(drawFrame,0,0);
        sb.end();
        if(LevelOneAnimation.isAnimationFinished(LevelOneAnimationTime))
        {
            sb.begin();
            gsm.clear();
            gsm.push(new LevelOne(gsm));
            sb.end();
        }
    }
    public void dispose()
    {
        Utils.disposeAnimation(LevelOneAnimation);
    }
}
