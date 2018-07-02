package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Evolution extends ApplicationAdapter {
    private OrthographicCamera camera;
    private Random randomSource;
    private Sprite myImage;
    private SpriteBatch myBatch;
    private Vector2 velocity;

    @Override
    public void create() {
        randomSource = new Random();

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        //TODO: Load our image
        myImage = new Sprite( new Texture(Gdx.files.internal("images/GreenPlayer.png")));
        myImage.setX(200);
        myImage.setY(200);
        velocity = new Vector2(randomSource.nextFloat() * 300, randomSource.nextFloat() * 300);
    }

    @Override
    public void render() {



        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);

        //TODO: Draw our image!
        myBatch.begin();
        myImage.draw(myBatch);
        myBatch.end();
        float xPos = myImage.getX() + velocity.x * Gdx.graphics.getDeltaTime();
        float yPos = myImage.getY() + velocity.y * Gdx.graphics.getDeltaTime();

        myImage.setX(xPos);
        myImage.setY(yPos);
    }

    @Override
    public void dispose() {
        myBatch.dispose();
    }
}