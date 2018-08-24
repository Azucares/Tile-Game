package dev.azucares.tilegame;

import dev.azucares.tilegame.display.Display;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("practice tilegame", 500, 400);
		game.start();
	}

}
