package xin.newshooter;

public class Bee extends FlyingPlane implements Award{
	
	private int xSpeed = (int)(Math.random() * 3) - 1;			// 随机的方向
	private int ySpeed = 2;
	
	public Bee() {
		super();
		this.isEnemy = false;
		this.y = -(int)(Math.random() * (height));
		this.image = ShootJPanel.bee[(int)(Math.random() * 3)];
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
		return awardType;
	}

}
