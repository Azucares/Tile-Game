package dev.azucares.tilegame.states;

import java.awt.Graphics;

import dev.azucares.tilegame.Game;
import dev.azucares.tilegame.entities.creatures.Player;

public class GameState extends State {
	private Player player ;
	
	public GameState(Game game){
		super(game);
		player = new Player(game, 200, 200) ;
	}
	
	@Override
	public void update() {
		player.update();
	}

	@Override
	public void render(Graphics g) {
		player.render(g);
	}

}
