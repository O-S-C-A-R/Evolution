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

import sun.font.TrueTypeFont;

public class Evolution extends ApplicationAdapter {
    private static final int GRAVITY = -5;
    private OrthographicCamera camera;
    private Random randomSource;
    private Sprite BlackPlayer;
    private Sprite Tutorial;

    private SpriteBatch myBatch;
    //private Vector2 velocity;
    private float Speed;
    private boolean showDebug = false;
    private Platforms floor;
    private boolean touchplatform = true;

    private ShapeRenderer debugRenderer;

    @Override
    public void create() {
        randomSource = new Random();

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 600);

        // Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        debugRenderer = new ShapeRenderer();

        //TODO: Load our image
        BlackPlayer = new Sprite( new Texture(Gdx.files.internal("images/BlackPlayer.png")));
        Tutorial = new Sprite( new Texture(Gdx.files.internal("images/Tutorial.png")));
        floor = new Platforms(0,0,800,20);
        BlackPlayer.setX(0);
        BlackPlayer.setY(0);
       // velocity = new Vector2(0, 0);
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

        //todo: Draw our image!

        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            BlackPlayer.setX(BlackPlayer.getX()-Gdx.graphics.getDeltaTime() * Speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            BlackPlayer.setX(BlackPlayer.getX()+Gdx.graphics.getDeltaTime() * Speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            BlackPlayer.setY(BlackPlayer.getY() + Gdx.graphics.getDeltaTime() * Speed);
        }
            BlackPlayer.setY(BlackPlayer.getY()+GRAVITY);


        if (floor.hit(BlackPlayer.getBoundingRectangle())){
           // velocity.y=floor.getTop();
            BlackPlayer.setY(floor.getTop());
            //System.out.println("hit "+floor.getTop() + " " + BlackPlayer.getY());

            touchplatform = true;

        }




        myBatch.begin();
        Tutorial.draw(myBatch);
        myBatch.end();

        myBatch.begin();
       // myBatch.draw(BlackPlayer,(int)velocity.x,(int)velocity.y);
        BlackPlayer.draw(myBatch);
        myBatch.end();


        if(showDebug){
            debugRenderer.setProjectionMatrix(camera.combined);
            debugRenderer.begin(ShapeRenderer.ShapeType.Line);
            debugRenderer.setColor(0, 0, 0, 0);
            floor.drawDebug(debugRenderer);
            debugRenderer.rect(BlackPlayer.getX(), BlackPlayer.getY(), BlackPlayer.getWidth(), BlackPlayer.getHeight());
            debugRenderer.end();
        }

    }


    @Override
    public void dispose() {
        myBatch.dispose();
    }
}