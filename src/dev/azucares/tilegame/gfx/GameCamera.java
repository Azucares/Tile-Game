package dev.azucares.tilegame.gfx;

public class GameCamera {
	private float xOffset, yOffset ;
	
	public GameCamera(float xOffset, float yOffset){
		this.xOffset = xOffset ;
		this.yOffset = yOffset ;
		
	}

	public void move(float xAmount, float yAmount){
		xOffset += xAmount ;
		yOffset += yAmount ;
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
