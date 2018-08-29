package dev.azucares.tilegame.gfx;

import dev.azucares.tilegame.Game;
import dev.azucares.tilegame.Handler;
import dev.azucares.tilegame.entities.Entity;
import dev.azucares.tilegame.tiles.Tile;

public class GameCamera {
	private Handler handler ;
	private float xOffset, yOffset ;
	
	public GameCamera(Handler handler, float xOffset, float yOffset){
		this.xOffset = xOffset ;
		this.yOffset = yOffset ;
		this.handler = handler ;
	}
	
	public void checkBlankSpace(){
		if(xOffset < 0){
			xOffset = 0 ;
		}else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth() ;
		}
		if(yOffset < 0){
			yOffset = 0 ;
		}else if(yOffset > handler.getWorld().getHeight() * Tile.TILEWIDTH - handler.getHeight()){
			yOffset = handler.getWorld().getHeight() * Tile.TILEWIDTH - handler.getHeight() ;
		}

	}

	public void centerOnEntity(Entity e){
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2 ;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace() ;
	}
	
	public void move(float xAmount, float yAmount){
		xOffset += xAmount ;
		yOffset += yAmount ;
		checkBlankSpace() ;
	}
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}
