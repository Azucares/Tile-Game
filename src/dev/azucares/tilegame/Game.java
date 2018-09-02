package dev.azucares.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.azucares.tilegame.display.Display;
import dev.azucares.tilegame.gfx.Assets;
import dev.azucares.tilegame.gfx.GameCamera;
import dev.azucares.tilegame.gfx.SpriteSheet;
import dev.azucares.tilegame.input.KeyManager;
import dev.azucares.tilegame.input.MouseManager;
import dev.azucares.tilegame.states.GameState;
import dev.azucares.tilegame.states.MenuState;
import dev.azucares.tilegame.states.State;

public class Game implements Runnable{
	private Display display;
	private int width, height;
	private String title;
	
	private boolean running = false ;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g ;
	
	private BufferedImage backgroundImage ;
	private BufferedImage testSheet;
	private SpriteSheet sheet;
	
	//states
	public State gameState;
	public State menuState;
	
	//input
	private KeyManager keyManager ;
	private MouseManager mouseManager ;
	
	//camera
	private GameCamera gameCamera ;
	
	//handler
	private Handler handler ;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager() ;
	}

	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this) ;
		gameCamera = new GameCamera(handler, 0, 0) ;
		
		
		gameState = new GameState(handler) ;
		menuState = new MenuState(handler) ;
		State.setState(menuState) ;
	}
	
	private void update(){
		keyManager.update() ;
		
		if(State.getState() != null)
			State.getState().update();
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		if(State.getState() != null)
			State.getState().render(g);
		
		
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		init();
		
		int fps = 60 ;
		double timePerTick = 1000000000 / fps ;
		double delta = 0 ;
		long now ;
		long lastTime = System.nanoTime() ;
		long timer = 0;
		long ticks = 0 ;
		
		//game loop
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick ;
			timer += now - lastTime ;
			lastTime = now ;
			
			if(delta >= 1){
				update();
				render();
				ticks++ ;
				delta-- ;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames " + ticks);
				ticks = 0 ;
				timer = 0 ;
			}
		}
		
		stop();
	}
	
	public MouseManager getMouseManager(){
		return mouseManager ;
	}
	
	public KeyManager getKeyManager(){
		return keyManager ;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera ;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public synchronized void start(){
		if(running)
			return;
		running = true ;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
