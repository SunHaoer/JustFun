package xin.newshooter;

public class Sky extends FlyingObject{
	
	private int speed = 1;
	
	public Sky() {
		ShootJPanel.skyY = -5400;
		this.image = ShootJPanel.background[0];
	}

	@Override
	public boolean outOfBounds() {
		return false;
	}

	@Override
	public void step() {				// 天空在移动
		ShootJPanel.skyY += speed;
		if(ShootJPanel.bossExist) {					// boss来了, 停止前进
			this.speed = 0;
		} else {
			this.speed = 1;
		}
		if(ShootJPanel.skyY == 0 && ShootJPanel.skyIndex != 3) {
			ShootJPanel.skyY = -5400;
			this.image = ShootJPanel.background[++ShootJPanel.skyIndex];		// 飞到底, 换张图
		} else if(ShootJPanel.skyY == 0 && ShootJPanel.skyIndex == 3) {
			speed = 0;
		}
	}
}