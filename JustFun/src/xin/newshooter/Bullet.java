package xin.newshooter;

import xin.newshooter.FlyingObject;

public class Bullet extends FlyingObject {
	
	private int speed = 3;
	private boolean bomb;
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.image = ShootJPanel.bullet[0];
	}
	
	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}
	
	public boolean isBomb() {
		return bomb;
	}
	
	public void setImage() {
		this.image = ShootJPanel.bullet[ShootJPanel.skyIndex / 2];
	}

	@Override
	public boolean outOfBounds() {
		return y < height;
	}

	@Override
	public void step() {
		y -= speed;
	}

}
