package dev.azucares.tilegame.states;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import dev.azucares.tilegame.Handler;
import dev.azucares.tilegame.entities.creatures.Player;
import dev.azucares.tilegame.gfx.Assets;
import dev.azucares.tilegame.networking.Client;
import dev.azucares.tilegame.networking.Server;
import dev.azucares.tilegame.ui.ClickListener;
import dev.azucares.tilegame.ui.UIImageButton;
import dev.azucares.tilegame.ui.UIManager;

public class MultiplayerMenuState extends State {
	private static Font monoFont ;
	private UIManager uiManager ;
	private int buttonX = 190, hostY = 100, joinY = 175 ;
	
	public MultiplayerMenuState(Handler handler) {
		super(handler);
		uiManager = handler.getMouseManager().getUIManager() ;
		
		uiManager.addMultiplayerObject(new UIImageButton(buttonX, hostY, 128, 64, Assets.menuButtons, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getGame().setServer(new Thread(new Server(handler)));
				handler.getGame().getServer().start();
				State.setState(handler.getGame().gameState);
			}
		}));
		
		uiManager.addMultiplayerObject(new UIImageButton(buttonX, joinY, 128, 64, Assets.menuButtons, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getWorld().getEntityManager().addPlayer(new Player(handler, 400, 400));
				handler.getWorld().getEntityManager().setCurrentPlayer(handler.getWorld().getEntityManager().getPlayer2());
				handler.getGame().setClient(new Thread(new Client(handler, 36000, "127.0.0.1")));
				handler.getGame().getClient().start();
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
	    g.drawString("Host", buttonX + 5, hostY + 45);
	    g.drawString("Join", buttonX + 5, joinY + 45);
	}

}
