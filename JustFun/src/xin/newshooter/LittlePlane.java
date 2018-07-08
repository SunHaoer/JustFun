package xin.newshooter;

public class LittlePlane extends FlyingPlane{
	
	public LittlePlane() {				// С�ɻ�
		super();
		this.isEnemy = true;			// ����
		this.isBoss = false;
		this.speed = 2;
		this.life = 1;
		this.image = ShootJPanel.littlePlane[(int)(Math.random() * 5)];
		this.ember = ShootJPanel.littleEmber;
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		this.ember = ShootJPanel.littleEmber;
	}

	@Override
	public int getScore() {				// ��÷���
		return 5;
	}

	@Override
	public void step() {				// �ƶ�
		y += speed;
	}

	@Override
	public int getType() {				
		return -1;
	}

}
