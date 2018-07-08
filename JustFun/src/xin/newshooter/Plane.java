package xin.newshooter;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Plane extends FlyingObject {
	
	protected Image[] images = {};
	protected int index = 0;
	private int doubleFire;
	private int life;
	
	public Plane() {
		this.life = 3;
		this.doubleFire = 0;
		this.image = ShootJPanel.planeImage[ShootJPanel.skyIndex];
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		this.x = 150;
		this.y = 400;
	}
	
	public int isDouble() {			// 是否加强火力
		return doubleFire;
	}
	
	public void addDoubleFire() {			// 进入加强模式
		this.doubleFire += 40;
	}
	
	public void setDoubleFire(int doubleFire) {			// 设置火力
		this.doubleFire = doubleFire;
	}
	
	public int getDoubleFire() {					// 获取火力值
		return this.doubleFire;
	}
	
	public void setImage() {						// 改变飞机状态
		if(ShootJPanel.skyIndex < 3) {
			this.image = ShootJPanel.planeImage[ShootJPanel.skyIndex];
		}
	}
	
	public Image getImg() {	// 获得图片
		return this.image;
	}
	
	public void addLife() {			// 加命
		life++;
	}
	
	public void sublife() {			// 减命
		life--;
	}
	
	public int getLife() {
		return life;
	}
	
	public void moveTo(int x, int y) {		// 飞机移动
		this.x = x - width / 2;
		this.y = y - height / 2;
	}
	
	public List<Bullet> shoot() {			// 射击
		int xStep = width / 4;
		int yStep = 20;
		List<Bullet> bullets = new ArrayList<Bullet>();
		if(doubleFire > 0) {
			bullets.add(new Bullet(x + xStep, y - yStep));
			bullets.add(new Bullet(x + 3 * xStep, y - yStep));
			doubleFire -= 2;
		} else {
			bullets.add(new Bullet(x + 2 * xStep, y - yStep));
		}
		return bullets;
	}
	
	public boolean hit(FlyingPlane other) {				// 碰撞
		int x1 = other.x - this.width / 2;
		int x2 = other.x + other.width + this.width / 2;
		int y1 = other.y - this.height / 2;
		int y2 = other.y + other.height + this.height / 2;
		boolean flag = (!other.isBoss) &&  (this.x + this.width / 2 > x1) && (this.x + this.width / 2 < x2)
					  				   && (this.y + this.height / 2 > y1) && (this.y + this.height / 2 < y2);
		if(flag) {
			other.life--;
		}
		return flag;
	}

	@Override
	public boolean outOfBounds() {
		return false;
	}

	@Override
	public void step() {
	}

}
