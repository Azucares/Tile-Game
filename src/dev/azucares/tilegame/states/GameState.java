package dev.azucares.tilegame.states;

import java.awt.Graphics;

import dev.azucares.tilegame.Game;
import dev.azucares.tilegame.entities.creatures.Player;
import dev.azucares.tilegame.tiles.Tile;
import dev.azucares.tilegame.worlds.World;

public class GameState extends State {
	private Player player ;
	private World world ;
	
	public GameState(Game game){
		super(game);
		player = new Player(game, 200, 200) ;
		world = new World(game, "res/worlds/world1.txt") ;
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
