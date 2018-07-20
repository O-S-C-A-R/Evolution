package com.missionbit.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.missionbit.game.Evolution;
import com.missionbit.game.Utils;


public class RestartState extends State
{
    private Texture RestartScreen;
    private Rectangle Restart;
    private Rectangle Exit;
    public Vector3 touchPos;
    private Animation<TextureRegion> DeathAnimation;
    float DeathAnimationTime = 0;


    public RestartState(GameStateManager gsm)
    {
        super(gsm);
        RestartScreen = new Texture("images/ui/DeathMenuTwo.png");
        Restart = new Rectangle(291, 250, 375, 71);
        Exit = new Rectangle(292, 171, 374, 71);
        cam.setToOrtho(false, Evolution.VIEWPORT_WIDTH, Evolution.VIEWPORT_HEIGHT);
        DeathAnimation = Utils.LoadAnimation("images/ui/DeathMenu.png", 7, 8, 52, 0.1f);
        DeathAnimationTime += Gdx.graphics.getDeltaTime();
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
                Levelmaker.GameMode = true;
            }
            else if (Exit.contains(touchPos.x, touchPos.y))
            {
                gsm.set(new TitleState(gsm));
                Levelmaker.GameMode = false;
            }


        }
    }

    public void update(float dt)
    {
        handleInput();
    }
    public void render(SpriteBatch sb)
    {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        DeathAnimationTime += Gdx.graphics.getDeltaTime();
        TextureRegion drawFrame = DeathAnimation.getKeyFrame(DeathAnimationTime, false);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(drawFrame,Gdx.graphics.getWidth() / 2 - drawFrame.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - drawFrame.getRegionHeight() / 2);
        sb.end();
        if(DeathAnimation.isAnimationFinished(DeathAnimationTime))
        {
            sb.begin();
            sb.draw(RestartScreen, 0, 0);
            sb.end();
        }

    }
    public void dispose()
    {
        RestartScreen.dispose();
        System.out.println("Disposing of Menu State");
    }
}

//{291.0, 250.0, 375.0, 77.0},
//        {292.0, 171.0, 374.0, 71.0},

