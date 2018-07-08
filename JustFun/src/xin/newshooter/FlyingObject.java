package xin.newshooter;

import java.awt.Image;

public abstract class FlyingObject {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Image image;
	protected Image ember;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Image getImage() {
		return image;
	}
	public void setImage() {}
	
	public Image getEmber() {
		return ember;
	}
	public void setEmber(Image ember) {
		this.ember = ember;
	}
	
	public abstract boolean outOfBounds(); 			// 检查飞行物是否出界
	
	public abstract void step();					// 飞行物移动
	
}
