package dev.azucares.tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import dev.azucares.tilegame.Handler;
import dev.azucares.tilegame.entities.creatures.Player;

public class EntityManager {
	private Handler handler ;
	private Player player ;
	private Player player2 ;
	private Player currentPlayer ;
	private ArrayList<Entity> entities ;
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		public int compare(Entity a, Entity b){
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()){
				return -1 ;
			}
			return 1 ;
		}
	};
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler ;
		this.player = player ;
		currentPlayer = player ;
		entities = new ArrayList<Entity>() ;
		addEntity(player) ;
	}
	
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i) ;
			e.update() ;
		}
		entities.sort(renderSorter); 
		handler.getGameCamera().centerOnEntity(currentPlayer);
	}
	
	public void render(Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
	}
	
	public void addEntity(Entity e){
		entities.add(e) ;
	}
	
	public void addPlayer(Player player){
		this.player2 = player ;
		addEntity(player) ;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
