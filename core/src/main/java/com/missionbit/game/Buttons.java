package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Buttons
{


    private Sprite Image;
    public Buttons(float X, float Y, String ImagePath)
    {
        Image = new Sprite(new Texture(Gdx.files.internal(ImagePath)));
        Image.setX(X);
        Image.setY(Y);
    }

    public boolean HandleClick(Vector3 touchPos)
    {
        boolean hit = Image.getBoundingRectangle().contains(touchPos.x, touchPos.y);
        return hit;
    }

    public void draw(SpriteBatch b)
    {
        Image.draw(b);
    }

    public float getX()
    {
        return Image.getX();
    }

    public float getY()
    {
        return Image.getY();
    }

    public void setX(float x)
    {
        Image.setX(x);
    }

    public void setY(float y)
    {
        Image.setY(y);
    }

    public float getWidth()
    {
        return Image.getWidth();
    }

    public float getHeight()
    {
        return Image.getHeight();
    }
}
