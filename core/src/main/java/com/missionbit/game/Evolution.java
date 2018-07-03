package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Evolution extends ApplicationAdapter {
    private static final int GRAVITY = -5;
    private OrthographicCamera camera;
    private Random randomSource;
    private Sprite BlackPlayer;
    private Sprite Tutorial;

    private SpriteBatch myBatch;
    private Vector2 velocity;
    private float Speed;
    private boolean showDebug = true;
    private Platforms floor;

    private ShapeRenderer debugRenderer;

    @Override
    public void create() {
        randomSource = new Random();

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //TODO: Load our image
        BlackPlayer = new Sprite( new Texture(Gdx.files.internal("images/BlackPlayer.png")));
        Tutorial = new Sprite( new Texture(Gdx.files.internal("images/Tutorial.png")));
        floor = new Platforms(0,0,800,20);
        BlackPlayer.setX(200);
        BlackPlayer.setY(200);
        velocity = new Vector2(0, 0);
        Speed = 700.0f;

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

        float xPos = BlackPlayer.getX() + velocity.x * Gdx.graphics.getDeltaTime();
        float yPos = BlackPlayer.getY() + velocity.y * Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            velocity.x -= Gdx.graphics.getDeltaTime() * Speed;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            velocity.x += Gdx.graphics.getDeltaTime() * Speed;
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
            velocity.y += Gdx.graphics.getDeltaTime() * Speed;

       // if(velocity.y>0){
            velocity.add(0, GRAVITY);
        //}

        if (floor.hit(BlackPlayer.getBoundingRectangle())){
            velocity.y=floor.getTop();
        }

        camera.position.set(BlackPlayer.getX(), BlackPlayer.getY(), 0);
        camera.update();


        myBatch.begin();
        Tutorial.draw(myBatch);
        myBatch.end();

        myBatch.begin();
        myBatch.draw(BlackPlayer,(int)velocity.x,(int)velocity.y);
        myBatch.end();


        if(showDebug){
            debugRenderer.setProjectionMatrix(camera.combined);
            debugRenderer.begin(ShapeRenderer.ShapeType.Line);
            debugRenderer.setColor(0, 1, 0, 1);
            floor.drawDebug(debugRenderer);
            debugRenderer.end();
        }
    }


    @Override
    public void dispose() {
        myBatch.dispose();
    }
}