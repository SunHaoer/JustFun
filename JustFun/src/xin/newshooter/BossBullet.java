package xin.newshooter;

public class BossBullet extends FlyingPlane implements Award{
	
	private int xSpeed = (int)(Math.random() * 3) - 1;			// 随机的方向
	private int ySpeed = 2;
	
	public BossBullet(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.life = 1;
		this.isEnemy = true;
		this.image = ShootJPanel.bullet[2];
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	@Override
	public void step() {
		x += xSpeed;
		y += ySpeed;
	}

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public int getType() {
		return 2;
	}

}
