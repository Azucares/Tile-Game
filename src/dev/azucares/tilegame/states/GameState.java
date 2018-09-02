package dev.azucares.tilegame.states;

import java.awt.Graphics;

import dev.azucares.tilegame.Handler;
import dev.azucares.tilegame.entities.Tree;
import dev.azucares.tilegame.entities.creatures.Player;
import dev.azucares.tilegame.worlds.World;

public class GameState extends State {
	private Player player ;
	private World world ;
	private Tree tree ;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/world1.txt") ;
		handler.setWorld(world) ;
		//player = new Player(handler, 50, 50) ;
		//tree = new Tree(handler, 100, 200) ;
	}
	
	@Override
	public void update() {
		world.update();
		//player.update();
		//tree.update() ;
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		//player.render(g);
		//tree.render(g);
	}
}
