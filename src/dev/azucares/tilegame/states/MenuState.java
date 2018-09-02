package dev.azucares.tilegame.states;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import dev.azucares.tilegame.Handler;
import dev.azucares.tilegame.gfx.Assets;
import dev.azucares.tilegame.ui.ClickListener;
import dev.azucares.tilegame.ui.UIImageButton;
import dev.azucares.tilegame.ui.UIManager;

public class MenuState extends State {
	private static Font monoFont ;
	private UIManager uiManager ;
	private int buttonX = 190, startY = 100, multY = 175 ;
	
	public MenuState(Handler handler){
		super(handler);
		uiManager = new UIManager(handler) ;
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(buttonX, startY, 128, 64, Assets.menuButtons, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
		
		uiManager.addObject(new UIImageButton(buttonX, multY, 256, 64, Assets.menuButtons, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
		monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 36);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		uiManager.update();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		uiManager.render(g);
		g.setFont(monoFont);
	    FontMetrics fm = g.getFontMetrics();
	    g.drawString("Start", buttonX + 5, startY + 45);
	    g.drawString("Multiplayer", buttonX + 5, multY + 45);
	}

}
