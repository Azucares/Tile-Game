package dev.azucares.tilegame.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.azucares.tilegame.Handler;

public class UIManager {
	private Handler handler ;
	private String currentMenu = "main" ;
	private ArrayList<UIObject> mainMenu ;
	private ArrayList<UIObject> multMenu ;
	
	
	public UIManager(Handler handler){
		this.handler = handler ;
		mainMenu = new ArrayList<UIObject>() ;
		multMenu = new ArrayList<UIObject>() ;
	}
	
	public void update(){
		if(currentMenu == "main"){
			for(UIObject o : mainMenu)
				o.update();
		}
		else if(currentMenu == "multiplayer"){
			for(UIObject o : multMenu)
				o.update();
		}
	}
	
	public void render(Graphics g){
		if(currentMenu == "main"){
			for(UIObject o : mainMenu)
				o.render(g);
		}
		else if(currentMenu == "multiplayer"){
			for(UIObject o : multMenu)
				o.render(g);
		}
			
	}
	
	public void onMouseMove(MouseEvent e){
		if(currentMenu == "main"){
			for(UIObject o : mainMenu)
				o.onMouseMove(e);
		}
		else if(currentMenu == "multiplayer"){
			for(UIObject o : multMenu)
				o.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e){
		if(currentMenu == "main"){
			for(UIObject o : mainMenu)
				o.onMouseRelease(e);
		}
		else if(currentMenu == "multiplayer"){
			for(UIObject o : multMenu)
				o.onMouseRelease(e);
		} 
	}
	
	public void addMainMenuObject(UIObject o){
		mainMenu.add(o) ;
	}
	
	public void removeMainMenuObject(UIObject o){
		mainMenu.remove(o) ;
	}
	
	public void addMultiplayerObject(UIObject o){
		multMenu.add(o) ;
	}
	
	public void removeMultiplayerObject(UIObject o){
		multMenu.remove(o) ;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return mainMenu;
	}

	public void setMainMenuObjects(ArrayList<UIObject> mainObjects) {
		this.mainMenu = mainObjects;
	}
	
	public void setMultMenuObjects(ArrayList<UIObject> multObjects){
		this.multMenu = multObjects ;
	}

	public String getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(String currentMenu) {
		this.currentMenu = currentMenu;
	}
}
