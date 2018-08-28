package dev.azucares.tilegame.states;

import java.awt.Graphics;

import dev.azucares.tilegame.Handler;

public abstract class State {
	protected Handler handler;
	private static State currentState = null ;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState ;
	}
	
	public State(Handler handler){
		this.handler = handler ;
	}
	public abstract void update();
	public abstract void render(Graphics g);
}
