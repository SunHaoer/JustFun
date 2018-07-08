package xin.newshooter;

import java.awt.Image;
import java.util.Random;

public abstract class FlyingPlane extends FlyingObject{

	protected int speed;
	protected int life = 10;
	protected int awardType;
	protected boolean isEnemy;
	protected Image ember;
	protected boolean isBoss;
	protected boolean isExist;
	
	public FlyingPlane() {
		Random ran = new Random();
		this.y = -height;
		this.x = ran.nextInt(ShootJFrame.WIDTH - 2 * width);
		this.awardType = ran.nextInt(2);
		this.isExist = true;
	}

	@Override
	public boolean outOfBounds() {
		return y > ShootJFrame.HEIGHT;
	}

	@Override
	public abstract void step(); 
	
	public abstract int getScore();
	
	public abstract int getType();
	
	public boolean shootBy(Bullet bullet) {					// 判断是否击中
		if(bullet.isBomb() || !isEnemy) {
			return false;
		}
		int x = bullet.x;
		int y = bullet.y;
		boolean shoot = (x > this.x) && (x < this.x + width) && (y > this.y) && (y < this.y + height);
		if(shoot) {							// 标记子弹为击中
			bullet.setBomb(true);
			life--;
		}
		return life <= 0; 
	}

}
