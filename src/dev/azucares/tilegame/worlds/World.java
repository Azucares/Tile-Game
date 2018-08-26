package dev.azucares.tilegame.worlds;

import java.awt.Graphics;

import dev.azucares.tilegame.Game;
import dev.azucares.tilegame.tiles.Tile;
import dev.azucares.tilegame.utils.Utils;

public class World {
	private Game game ;
	private int width, height, spawnX, spawnY ;
	private int[][] tiles ;
	
	public World(Game game, String path){
		loadWorld(path) ;
		this.game = game ;
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
			}
		}
	}
	
	public Tile getTile(int x, int y){
		Tile t = Tile.tiles[tiles[x][y]] ;
		if(t==null)
			return Tile.rockTile ;
		return t ;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path) ;
		String[] tokens = file.split(",") ;
		
		width = Utils.parseInt(tokens[0]) ;
		height = Utils.parseInt(tokens[1]) ;
		spawnX = Utils.parseInt(tokens[2]) ;
		spawnY = Utils.parseInt(tokens[3]) ;
		
		tiles = new int[width][height] ;
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]) ;
			}
		}
	}
}
