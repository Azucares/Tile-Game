package dev.azucares.tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.azucares.tilegame.Handler;
import dev.azucares.tilegame.gfx.Animation;
import dev.azucares.tilegame.gfx.Assets;

public class Player extends Creature {
	private Animation animDown ;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 8 ;
		bounds.y = 16 ;
		bounds.width = 16 ;
		bounds.height = 16 ;
		
		animDown = new Animation(500, Assets.warrior_down) ;
	}

	@Override
	public void update() {
		animDown.update();
		getInput();
		move();
	}

	private void getInput(){
		xMove = 0 ;
		yMove = 0 ;
		
		if(handler.getKeyManager().up)
			yMove = -speed ;
		if(handler.getKeyManager().down)
			yMove = speed ;
		if(handler.getKeyManager().left)
			xMove = -speed ;
		if(handler.getKeyManager().right)
			xMove = speed ;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	public BufferedImage getCurrentAnimationFrame(){
		if(xMove < 0){
			return Assets.warrior_down[1] ;
		}else if(xMove > 0){
			return Assets.warrior_down[1] ;
		}else if(yMove < 0){
			return Assets.warrior_down[1] ;
		}else if(yMove > 0){
			return animDown.getCurrentFrame() ;
		}else{
			return Assets.warrior_down[1] ;
		}
	}
}
