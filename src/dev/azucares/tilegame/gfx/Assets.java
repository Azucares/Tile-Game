package dev.azucares.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	public static BufferedImage parchment, axe, silverAxe, scroll, staff, pack, bow, sword, tools;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/weaps.png")) ;
		parchment = sheet.crop(15, 56, 46, 54) ;
		axe = sheet.crop(81, 52, 58, 64);
		silverAxe = sheet.crop(156, 52, 58, 64);
		scroll = sheet.crop(25, 131, 38, 39);
		staff = sheet.crop(92, 128, 50, 52);
		pack = sheet.crop(160, 120, 46, 52);
		bow = sheet.crop(11, 180, 49, 58);
		sword = sheet.crop(96, 189, 45, 46);
		tools = sheet.crop(165, 180, 51, 49);
	}
}
