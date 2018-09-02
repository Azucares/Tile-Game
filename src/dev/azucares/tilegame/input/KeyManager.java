package dev.azucares.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;

public class KeyManager implements KeyListener {
	private boolean[] keys;
	public boolean up, down, left, right ;
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void update(){
		up = keys[KeyEvent.VK_W] ;
		down = keys[KeyEvent.VK_S] ;
		left = keys[KeyEvent.VK_A] ;
		right = keys[KeyEvent.VK_D] ;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = true ;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = false ;
	}

}
