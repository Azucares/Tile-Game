package dev.azucares.tilegame.states;

import java.awt.Graphics;

import dev.azucares.tilegame.Game;

public abstract class State {
	protected Game game;
	private static State currentState = null ;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState ;
	}
	
	public State(Game game){
		this.game = game ;
	}
	public abstract void update();
	public abstract void render(Graphics g);
}
