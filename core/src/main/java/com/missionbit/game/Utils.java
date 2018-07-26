package com.missionbit.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Utils {
    public static Animation<TextureRegion> LoadAnimation(String Path, int Cols, int Rows, int frame, float Duration) {
        Texture Sheet = new Texture(Gdx.files.internal(Path));
        TextureRegion[][] tmp = TextureRegion.split(Sheet,
                Sheet.getWidth() / Cols,
                Sheet.getHeight() / Rows);

        TextureRegion[] Frames = new TextureRegion[frame];
        int index = 0;
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Cols; j++) {
                if (index < frame) {
                    Frames[index++] = tmp[i][j];

                }
            }

        }

        return new Animation<TextureRegion>(Duration, Frames);
    }

    public static void disposeAnimation(Animation<TextureRegion> animation){
        TextureRegion[] frames = animation.getKeyFrames();
        for(int i = 0; i < frames.length; i++){
            frames[i].getTexture().dispose();
        }
    }
}