package dev.azucares.tilegame.states;

import java.awt.Graphics;

import dev.azucares.tilegame.Handler;
import dev.azucares.tilegame.entities.creatures.Player;
import dev.azucares.tilegame.worlds.World;

public class GameState extends State {
	private Player player ;
	private World world ;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/world1.txt") ;
		handler.setWorld(world) ;
		player = new Player(handler, 50, 50) ;
		
	}
	
	@Override
	public void update() {
		world.update();
		player.update();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}

}
