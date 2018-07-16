package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bouncepad {
    private Sprite Bouncepad;
public  Bouncepad(int x, int y){
    Bouncepad = new Sprite(new Texture(Gdx.files.internal("images/Enemies/BouncePad.png")));
    Bouncepad.setX(x);
    Bouncepad.setY(y);
    }
    public void draw(SpriteBatch Bouncedraw){
    Bouncepad.draw(Bouncedraw);
    }
    public boolean hit(Rectangle other){
        boolean flag = Bouncepad.getBoundingRectangle().overlaps(other);
        System.out.println(flag);

        return flag;
    }
    public boolean bounce(Player other) {

        boolean flag = Bouncepad.getBoundingRectangle().overlaps(other.getBounding());
        return flag;

    }




}


