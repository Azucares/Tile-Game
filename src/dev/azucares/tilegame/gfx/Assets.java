package dev.azucares.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	public static BufferedImage grass, flowers, tree, trees, water, rock, castle, mountain;
	
	public static void init(){
		//SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/weaps.png")) ;
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/basictiles.png")) ;
		grass = sheet.crop(48, 16, 16, 16) ;
		flowers = sheet.crop(64, 16, 16, 16) ;
		tree = sheet.crop(64, 144, 16, 16) ;
		trees = sheet.crop(80, 144, 16, 16) ;
		water = sheet.crop(80, 32, 16, 16) ;
		rock = sheet.crop(112, 16, 16, 16) ;
		castle = sheet.crop(48, 79, 16, 16) ;
		mountain = sheet.crop(97, 112, 16, 16) ;
	}
}
