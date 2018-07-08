package xin.newshooter;

import java.util.ArrayList;
import java.util.List;

public class Boss extends FlyingPlane {
	
	private int xSpeed = 2;
	private int ySpeed = (int)(Math.random() * 2 + 1);
	
	public Boss() {
		super();
		this.x = 100;
		this.isBoss = true;
		this.isEnemy = true;
		this.life = (ShootJPanel.skyIndex + 1) * 10;
		this.image = ShootJPanel.bigPlane[ShootJPanel.skyIndex];
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}
	
	public List<BossBullet> shoot() {
		List<BossBullet> bullets = new ArrayList<BossBullet>();
		bullets.add(new BossBullet(x + width / 2, y - height / 2));
		return bullets;
	}

	@Override
	public void step() {
		x += xSpeed;
		y += ySpeed;
		if( (x > ShootJFrame.WIDTH - width) || (x < 0) ) {
			xSpeed = -xSpeed;
		}
		if( (y > ShootJFrame.HEIGHT / 2) || y < 0) {
			ySpeed = -ySpeed;
		}
	}

	@Override
	public int getScore() {
		return 50;
	}

	@Override
	public int getType() {
		return awardType;
	}
	
	@Override
	public boolean shootBy(Bullet bullet) {
		if(super.shootBy(bullet)) {
			ShootJPanel.bossExist = false;
		}
		return super.shootBy(bullet);
	}

}
