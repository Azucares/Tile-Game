package dev.azucares.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	public static BufferedImage grass, flowers, tree, trees, water, rock, castle, mountain;
	public static BufferedImage blueButton, greyButton ;
	public static BufferedImage[] warrior_down ;
	public static BufferedImage[] menuButtons ;
	
	public static void init(){
		SpriteSheet players = new SpriteSheet(ImageLoader.loadImage("/textures/charsetsByAntifarea.png")) ;
		SpriteSheet worldTiles = new SpriteSheet(ImageLoader.loadImage("/textures/basictiles.png")) ;
		SpriteSheet uiBlue = new SpriteSheet(ImageLoader.loadImage("/textures/blueSheet.png")) ;
		SpriteSheet uiGrey = new SpriteSheet(ImageLoader.loadImage("/textures/greySheet.png")) ;
		
		warrior_down = new BufferedImage[3] ;
		
		warrior_down[0] = players.crop(16, 216, 15, 19) ;
		warrior_down[1] = players.crop(32, 215, 16, 19) ;
		warrior_down[2] = players.crop(48, 216, 16, 19) ;
		
		menuButtons = new BufferedImage[2] ;
		
		menuButtons[0] = uiBlue.crop(190, 49, 190, 45) ;
		menuButtons[1] = uiGrey.crop(0, 143, 190, 45) ;
		
		grass = worldTiles.crop(48, 16, 16, 16) ;
		flowers = worldTiles.crop(64, 16, 16, 16) ;
		tree = worldTiles.crop(64, 144, 16, 16) ;
		trees = worldTiles.crop(80, 144, 16, 16) ;
		water = worldTiles.crop(80, 32, 16, 16) ;
		rock = worldTiles.crop(112, 16, 16, 16) ;
		castle = worldTiles.crop(48, 79, 16, 16) ;
		mountain = worldTiles.crop(97, 112, 16, 16) ;
	}
}
