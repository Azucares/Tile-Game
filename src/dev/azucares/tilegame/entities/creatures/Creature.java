package dev.azucares.tilegame.entities.creatures;

import dev.azucares.tilegame.Handler;
import dev.azucares.tilegame.entities.Entity;
import dev.azucares.tilegame.tiles.Tile;

public abstract class Creature extends Entity {
	public static final int DEFAULT_HEALTH = 10 ;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 32,
							DEFAULT_CREATURE_HEIGHT = 32;
	protected int health ;
	protected float speed ;
	protected float xMove ;
	protected float yMove ;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH ;
		speed = DEFAULT_SPEED ;
		xMove = 0 ;
		yMove = 0 ;
	}

	public void move(){
		moveX() ;
		moveY() ;		
	}
	
	public void moveX(){
		if(xMove > 0){//moving right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH ;
			
			if(!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				x += xMove ;
			}
		}else if(xMove < 0){//moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH ;
			
			if(!collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				x += xMove ;
			}
		}
	}
	
	public void moveY(){
		if(yMove < 0){//moving up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT ;
			y += yMove ;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)){
				
			}
		}else if(yMove > 0){//moving down
			
		}
			
		
	}
	
	protected boolean collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid() ;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
}
