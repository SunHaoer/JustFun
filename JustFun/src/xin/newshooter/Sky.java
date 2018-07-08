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
	public void step() {				// ������ƶ�
		ShootJPanel.skyY += speed;
		if(ShootJPanel.bossExist) {					// boss����, ֹͣǰ��
			this.speed = 0;
		} else {
			this.speed = 1;
		}
		if(ShootJPanel.skyY == 0 && ShootJPanel.skyIndex != 3) {
			ShootJPanel.skyY = -5400;
			this.image = ShootJPanel.background[++ShootJPanel.skyIndex];		// �ɵ���, ����ͼ
		} else if(ShootJPanel.skyY == 0 && ShootJPanel.skyIndex == 3) {
			speed = 0;
		}
	}
}
