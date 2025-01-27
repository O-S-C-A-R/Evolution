package com.missionbit.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;


/**
 * Created by missionbit on 6/20/17.
 */

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }

    public void clear()
    {
        while (!states.empty())
        {
            State tmp = states.pop();
            tmp.dispose();
        }
    }

}

