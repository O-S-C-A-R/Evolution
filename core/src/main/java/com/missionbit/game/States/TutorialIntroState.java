package com.missionbit.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.Evolution;
import com.missionbit.game.Utils;

public class TutorialIntroState extends State
{
    private Animation<TextureRegion> TutorialAnimation;
    public Vector3 touchPos;
    float TutorialAnimationTime = 0;

    public TutorialIntroState(GameStateManager gsm)
    {
       super(gsm);
       TutorialAnimation = Utils.LoadAnimation("images/Transitions/TutorialTransition.png", 5, 8, 38, 0.1f);
       TutorialAnimationTime += Gdx.graphics.getDeltaTime();
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
        TutorialAnimationTime += Gdx.graphics.getDeltaTime();
        TextureRegion drawFrame = TutorialAnimation.getKeyFrame(TutorialAnimationTime, false);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(drawFrame,0,0);
        sb.end();
        if(TutorialAnimation.isAnimationFinished(TutorialAnimationTime))
        {
            sb.begin();
            gsm.clear();
            gsm.push(new TutorialState(gsm));
            sb.end();
        }
    }
    public void dispose()
    {
        Utils.disposeAnimation(TutorialAnimation);
    }
}
