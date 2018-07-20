package com.missionbit.game.States;

public abstract class Levelmaker extends State{
    protected static boolean GameMode = true;



    public Levelmaker(GameStateManager gsm){
        super(gsm);
    }
}
