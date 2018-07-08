package xin.newshooter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class ShootJPanel extends JPanel {
	
	private int state = 0;			// 游戏状态
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	
	public static int skyY;			// 天空位置
	public static int skyIndex;		// 关卡
	public static boolean bossExist = false;	// boss存在
	private int score  = 0;			// 得分
	private Timer timer;			// 计时器
	private int intervel = 1000 / 100;		// 时间间隔(毫秒)
	
	public static Image[] background = new Image[4];		// 背景图
	public static Image start;				// 开始界面
	public static Image pause;				// 暂停界面
	public static Image gameover;			// 游戏结束
	public static Image[] bullet = new Image[5];			// 子弹图片
	public static Image[] littlePlane = new Image[5];		// 小飞机图片
	public static Image[] bee = new Image[3];				// 奖励图片
	public static Image[] planeImage = new Image[3];				// 战斗机图片
	public static Image[] bigPlane = new Image[3];			// 大飞机图片
	public static Image littleEmber;				// 小飞机爆炸
	public static Image bigEmber;					// 大飞机爆炸
	
	private Sky sky = new Sky();				// 天空
	private Plane fighter = new Plane();			// 战斗机
	private Boss boss;								// boss
	private List<Bullet> bullets = new ArrayList<Bullet>();			// 子弹集合
	private List<FlyingPlane> flyings = new ArrayList<FlyingPlane>();		// 飞行物集合
	private List<Ember> embers = new ArrayList<Ember>();			// 尸体集合
	

	
	static {
		try {
			for(int i = 0; i < background.length; i++) {		// 载入背景图
				background[i] = ImageIO.read(new File("images/newshooter/background/background_" + i + ".png"));
			}
			start = ImageIO.read(new File("images/newshooter/GameInterface/start.png"));			// 游戏开始图片
			pause = ImageIO.read(new File("images/newshooter/GameInterface/pause.png")); 			// 游戏暂停图片
			gameover = ImageIO.read(new File("images/newshooter/GameInterface/gameover.png"));			// 游戏结束图片
			for(int i = 0; i < bullet.length; i++) {						// 载入子弹图片
				bullet[i] = ImageIO.read(new File("images/newshooter/bullet/bullet_" + i + ".png"));
			}
			for(int i = 0; i < littlePlane.length; i++) {					// 载入小飞机图片
				littlePlane[i] = ImageIO.read(new File("images/newshooter/LittlePlane/plane" + i + ".png"));
			}
			for(int i = 0; i < bee.length; i++) {							// 载入奖励图片
				bee[i] = ImageIO.read(new File("images/newshooter/award/award_" + i + ".png"));
			}
			for(int i = 0; i < planeImage.length; i++) {							// 载入战斗机图片
				planeImage[i] = ImageIO.read(new File("images/newshooter/HeroPlane/plane_" + i + ".gif"));
				//planeImage[i] =  new ImageIcon("images/HeroPlane/plane_" + i + ".gif");
			}
			for(int i = 0; i < bigPlane.length; i++) {						// 载入大飞机图片
				bigPlane[i] = ImageIO.read(new File("images/newshooter/BossPlane/boss_" + i + ".png"));
			}
			littleEmber = ImageIO.read(new File("images/newshooter/blast/blast_0.png")); 		// 载入小飞机爆炸图片
			bigEmber = ImageIO.read(new File("images/newshooter/blast/blast_1.png"));  		// 载入大飞机爆炸图片
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	
	@Override
	public void paint(Graphics g) {
		paintSky(g);				// 画天空
		paintPlane(g);				// 画战斗机
		paintBullet(g);				// 画子弹
		paintFlyingPlane(g);		// 画飞行物
		paintEmber(g);				// 画尸体
		paintScore(g);				// 画战绩
		paintState(g);				// 画状态
	}
	
	public void paintSky(Graphics g) {				// 画天空
		g.drawImage(sky.getImage(), 0, skyY, null);
	}
	
	public void paintPlane(Graphics g) {			// 画战斗机
		g.drawImage(fighter.getImage(), fighter.getX(), fighter.getY(), null);
	}
	
	public void paintBullet(Graphics g) {			// 画子弹
		for(Bullet bullet : bullets) {
			if(bullet.isBomb());
			g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), null);
		}
	}
	
	public void paintFlyingPlane(Graphics g) {		// 画敌机
		for(FlyingPlane plane : flyings) {
			g.drawImage(plane.getImage(), plane.getX(), plane.getY(), null);
		}
	}
	
	public void paintEmber(Graphics g) {		// 画尸体
		for(int i = 0; i < embers.size(); i++) {
			Ember ember = embers.get(i);
			g.drawImage(ember.image, ember.x, ember.y, null);
			embers.remove(ember);
		}
	}
	
	public void paintScore(Graphics g) {		// 画战绩
		int x = 10;
		int y = 25;
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 16 );
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("SCORE" + score, x, y);
		y += 20;
		g.drawString("LIFE" + fighter.getLife(), x, y);
		y += 20;
		g.drawString("DOUBLEFIRE" + fighter.getDoubleFire(), x, y);
	}
	
	public void paintState(Graphics g) {		// 画状态
		switch (state) {
		case START:						// 游戏开始界面
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		default:
			break;
		}
	}
	
	public ShootJPanel() {				// 构造方法
		action();
	}
	
	public void action() {				// 执行方法启动游戏

		MouseAdapter l = new MouseAdapter() {		// 添加鼠标事件
			
			@Override
			public void mouseClicked(MouseEvent e) {		// 鼠标点击事件
				if( (state == START) && (e.getModifiers() == InputEvent.BUTTON1_MASK) && (e.getX() >= 130) && (e.getX() <= 265) && (e.getY() >= 390) && (e.getY() <= 435) ) {		// 鼠标左键					
						state = RUNNING;		// 开始界面 - 开始游戏
				} else if( (state == GAME_OVER) && (e.getModifiers() == InputEvent.BUTTON1_MASK) && (e.getX() >= 115) && (e.getX() <= 305) && (e.getY() >= 310) && (e.getY() <= 370)) {
					score = 0;
					skyIndex = 0;
					skyY = -5400;		
					bossExist = false;
					sky = new Sky();
					flyings = new ArrayList<FlyingPlane>();
					bullets = new ArrayList<Bullet>();
					fighter = new Plane();
					state = START;				// 重新开始
				}
				if(e.getModifiers() == InputEvent.BUTTON3_MASK) {				// 右键暂停
					switch (state) {
					case RUNNING:
						state = PAUSE;
						break;
					case PAUSE:
						state = RUNNING;
						break;
					default:
						break;
					}
					
				}		
			}
			
			public void mouseMoved(MouseEvent e) {			// 鼠标移动事件
				if(state == RUNNING) {
					fighter.moveTo(e.getX(), e.getY());
				}
			}
			
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);	
		
		timer = new Timer();			// 主流程控制
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(state == RUNNING) {		// 游戏状态为进行中
					shootAction();			// 射击
					bossShootAction();			// boss射击
					enterAction();			// 飞行物入场
					stepAction();			// 移动
					bangAction();			// 判断击中
					outOfBoundsAction();		// 删除越界飞行物
					checkGameOverAction();		// 判断游戏结束
					
				}
				repaint();				// 重绘
			}
			
		}, intervel, intervel);
	}
	
	int shootIndex = 0;
	public void shootAction() {				// 射击
		shootIndex++;
		if(shootIndex % (60 / (skyIndex + 2)) == 0) {
			bullets.addAll(fighter.shoot());
		}
	}
	
	int bossShootIndex = 0;
	public void bossShootAction() {				// boss射击
		if(bossExist) {
			bossShootIndex++;
			if(shootIndex % (60 / (skyIndex + 2)) == 0) {
				flyings.addAll(boss.shoot());
			}			
		}

	}	
	
	int flyingEnteredIndex = 0;

	public void enterAction() { // 生产飞行物
		flyingEnteredIndex++;
		if ((skyIndex == 3) && (flyingEnteredIndex % 5 == 0)) { // 第三关死都不让过
			FlyingPlane plane = nextOne();
			flyings.add(plane);
		} else {
			if (flyingEnteredIndex % (80 / (skyIndex + 2)) == 0) {
				FlyingPlane plane = nextOne();
				flyings.add(plane);
			}
			if (skyY == -ShootJFrame.HEIGHT && bossExist == false && skyIndex != 3) { // boss来了
				bossExist = true;
				boss = new Boss();
				flyings.add(boss);
			}
		}
	}
	
	public FlyingPlane nextOne() {			// 随机生产飞行物
		Random ran = new Random();
		int type = ran.nextInt(20);
		if(type < 1) {
			return new Bee();
		} else {
			return new LittlePlane();
		}
	}
	
	public void stepAction() {				// 移动
		sky.step(); 				// 天空在移动
		fighter.setImage();			// 更新战斗机图片
		for(Bullet bullet : bullets) {			// 子弹在移动
			bullet.step();
			bullet.setImage();
		}
		for(FlyingPlane plane : flyings) {		// 飞行物移动
			plane.step();
		}
	}
	
	public void bangAction() {				// 判断击中
		for(Bullet bullet : bullets) {
			for(FlyingPlane plane : flyings) {
				if(plane.shootBy(bullet)) {
					score += plane.getScore();
					Ember ember = new Ember(plane.ember, plane.x, plane.y);
					embers.add(ember);
				}
			}
		}
	}
	
	public void outOfBoundsAction() {				// 删除越界飞行物
		for(int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if(bullet.outOfBounds() || bullet.isBomb()) {
				bullets.remove(bullet);
			}
		}
		for(int i = 0; i < flyings.size(); i++) {
			FlyingPlane plane = flyings.get(i);
			if(plane.outOfBounds() || plane.life <= 0 || !plane.isExist) {
				flyings.remove(plane);
			}
		}
	}
	
	public void checkGameOverAction() {				// 判断游戏结束
		if(isGameOver()) {
			state = GAME_OVER;
		}
	}
	
	public boolean isGameOver() {					// 判断碰撞
		for(FlyingPlane plane : flyings) {
			if(fighter.hit(plane) && plane.isEnemy && plane.isExist) {						// 敌人
				fighter.sublife();
				fighter.setDoubleFire(0);
				Ember ember = new Ember(plane.getImage(), plane.getX(), plane.getY());
				embers.add(ember);
				plane.isExist = false;
			} else if(fighter.hit(plane) && !plane.isEnemy && plane.isExist) {								// Bee 
				switch (plane.awardType) {
				case Award.doubleFIRE:
					fighter.addDoubleFire();
					break;
				case Award.LIFE:
					fighter.addLife();
					break;
				default:
					break;
				}
				plane.isExist = false;
			}
		}
		
		return fighter.getLife() <= 0;
	}
	
}
