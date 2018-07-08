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
	
	private int state = 0;			// ��Ϸ״̬
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	
	public static int skyY;			// ���λ��
	public static int skyIndex;		// �ؿ�
	public static boolean bossExist = false;	// boss����
	private int score  = 0;			// �÷�
	private Timer timer;			// ��ʱ��
	private int intervel = 1000 / 100;		// ʱ����(����)
	
	public static Image[] background = new Image[4];		// ����ͼ
	public static Image start;				// ��ʼ����
	public static Image pause;				// ��ͣ����
	public static Image gameover;			// ��Ϸ����
	public static Image[] bullet = new Image[5];			// �ӵ�ͼƬ
	public static Image[] littlePlane = new Image[5];		// С�ɻ�ͼƬ
	public static Image[] bee = new Image[3];				// ����ͼƬ
	public static Image[] planeImage = new Image[3];				// ս����ͼƬ
	public static Image[] bigPlane = new Image[3];			// ��ɻ�ͼƬ
	public static Image littleEmber;				// С�ɻ���ը
	public static Image bigEmber;					// ��ɻ���ը
	
	private Sky sky = new Sky();				// ���
	private Plane fighter = new Plane();			// ս����
	private Boss boss;								// boss
	private List<Bullet> bullets = new ArrayList<Bullet>();			// �ӵ�����
	private List<FlyingPlane> flyings = new ArrayList<FlyingPlane>();		// �����Ｏ��
	private List<Ember> embers = new ArrayList<Ember>();			// ʬ�弯��
	

	
	static {
		try {
			for(int i = 0; i < background.length; i++) {		// ���뱳��ͼ
				background[i] = ImageIO.read(new File("images/newshooter/background/background_" + i + ".png"));
			}
			start = ImageIO.read(new File("images/newshooter/GameInterface/start.png"));			// ��Ϸ��ʼͼƬ
			pause = ImageIO.read(new File("images/newshooter/GameInterface/pause.png")); 			// ��Ϸ��ͣͼƬ
			gameover = ImageIO.read(new File("images/newshooter/GameInterface/gameover.png"));			// ��Ϸ����ͼƬ
			for(int i = 0; i < bullet.length; i++) {						// �����ӵ�ͼƬ
				bullet[i] = ImageIO.read(new File("images/newshooter/bullet/bullet_" + i + ".png"));
			}
			for(int i = 0; i < littlePlane.length; i++) {					// ����С�ɻ�ͼƬ
				littlePlane[i] = ImageIO.read(new File("images/newshooter/LittlePlane/plane" + i + ".png"));
			}
			for(int i = 0; i < bee.length; i++) {							// ���뽱��ͼƬ
				bee[i] = ImageIO.read(new File("images/newshooter/award/award_" + i + ".png"));
			}
			for(int i = 0; i < planeImage.length; i++) {							// ����ս����ͼƬ
				planeImage[i] = ImageIO.read(new File("images/newshooter/HeroPlane/plane_" + i + ".gif"));
				//planeImage[i] =  new ImageIcon("images/HeroPlane/plane_" + i + ".gif");
			}
			for(int i = 0; i < bigPlane.length; i++) {						// �����ɻ�ͼƬ
				bigPlane[i] = ImageIO.read(new File("images/newshooter/BossPlane/boss_" + i + ".png"));
			}
			littleEmber = ImageIO.read(new File("images/newshooter/blast/blast_0.png")); 		// ����С�ɻ���ըͼƬ
			bigEmber = ImageIO.read(new File("images/newshooter/blast/blast_1.png"));  		// �����ɻ���ըͼƬ
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	
	@Override
	public void paint(Graphics g) {
		paintSky(g);				// �����
		paintPlane(g);				// ��ս����
		paintBullet(g);				// ���ӵ�
		paintFlyingPlane(g);		// ��������
		paintEmber(g);				// ��ʬ��
		paintScore(g);				// ��ս��
		paintState(g);				// ��״̬
	}
	
	public void paintSky(Graphics g) {				// �����
		g.drawImage(sky.getImage(), 0, skyY, null);
	}
	
	public void paintPlane(Graphics g) {			// ��ս����
		g.drawImage(fighter.getImage(), fighter.getX(), fighter.getY(), null);
	}
	
	public void paintBullet(Graphics g) {			// ���ӵ�
		for(Bullet bullet : bullets) {
			if(bullet.isBomb());
			g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), null);
		}
	}
	
	public void paintFlyingPlane(Graphics g) {		// ���л�
		for(FlyingPlane plane : flyings) {
			g.drawImage(plane.getImage(), plane.getX(), plane.getY(), null);
		}
	}
	
	public void paintEmber(Graphics g) {		// ��ʬ��
		for(int i = 0; i < embers.size(); i++) {
			Ember ember = embers.get(i);
			g.drawImage(ember.image, ember.x, ember.y, null);
			embers.remove(ember);
		}
	}
	
	public void paintScore(Graphics g) {		// ��ս��
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
	
	public void paintState(Graphics g) {		// ��״̬
		switch (state) {
		case START:						// ��Ϸ��ʼ����
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
	
	public ShootJPanel() {				// ���췽��
		action();
	}
	
	public void action() {				// ִ�з���������Ϸ

		MouseAdapter l = new MouseAdapter() {		// �������¼�
			
			@Override
			public void mouseClicked(MouseEvent e) {		// ������¼�
				if( (state == START) && (e.getModifiers() == InputEvent.BUTTON1_MASK) && (e.getX() >= 130) && (e.getX() <= 265) && (e.getY() >= 390) && (e.getY() <= 435) ) {		// ������					
						state = RUNNING;		// ��ʼ���� - ��ʼ��Ϸ
				} else if( (state == GAME_OVER) && (e.getModifiers() == InputEvent.BUTTON1_MASK) && (e.getX() >= 115) && (e.getX() <= 305) && (e.getY() >= 310) && (e.getY() <= 370)) {
					score = 0;
					skyIndex = 0;
					skyY = -5400;		
					bossExist = false;
					sky = new Sky();
					flyings = new ArrayList<FlyingPlane>();
					bullets = new ArrayList<Bullet>();
					fighter = new Plane();
					state = START;				// ���¿�ʼ
				}
				if(e.getModifiers() == InputEvent.BUTTON3_MASK) {				// �Ҽ���ͣ
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
			
			public void mouseMoved(MouseEvent e) {			// ����ƶ��¼�
				if(state == RUNNING) {
					fighter.moveTo(e.getX(), e.getY());
				}
			}
			
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);	
		
		timer = new Timer();			// �����̿���
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(state == RUNNING) {		// ��Ϸ״̬Ϊ������
					shootAction();			// ���
					bossShootAction();			// boss���
					enterAction();			// �������볡
					stepAction();			// �ƶ�
					bangAction();			// �жϻ���
					outOfBoundsAction();		// ɾ��Խ�������
					checkGameOverAction();		// �ж���Ϸ����
					
				}
				repaint();				// �ػ�
			}
			
		}, intervel, intervel);
	}
	
	int shootIndex = 0;
	public void shootAction() {				// ���
		shootIndex++;
		if(shootIndex % (60 / (skyIndex + 2)) == 0) {
			bullets.addAll(fighter.shoot());
		}
	}
	
	int bossShootIndex = 0;
	public void bossShootAction() {				// boss���
		if(bossExist) {
			bossShootIndex++;
			if(shootIndex % (60 / (skyIndex + 2)) == 0) {
				flyings.addAll(boss.shoot());
			}			
		}

	}	
	
	int flyingEnteredIndex = 0;

	public void enterAction() { // ����������
		flyingEnteredIndex++;
		if ((skyIndex == 3) && (flyingEnteredIndex % 5 == 0)) { // �������������ù�
			FlyingPlane plane = nextOne();
			flyings.add(plane);
		} else {
			if (flyingEnteredIndex % (80 / (skyIndex + 2)) == 0) {
				FlyingPlane plane = nextOne();
				flyings.add(plane);
			}
			if (skyY == -ShootJFrame.HEIGHT && bossExist == false && skyIndex != 3) { // boss����
				bossExist = true;
				boss = new Boss();
				flyings.add(boss);
			}
		}
	}
	
	public FlyingPlane nextOne() {			// �������������
		Random ran = new Random();
		int type = ran.nextInt(20);
		if(type < 1) {
			return new Bee();
		} else {
			return new LittlePlane();
		}
	}
	
	public void stepAction() {				// �ƶ�
		sky.step(); 				// ������ƶ�
		fighter.setImage();			// ����ս����ͼƬ
		for(Bullet bullet : bullets) {			// �ӵ����ƶ�
			bullet.step();
			bullet.setImage();
		}
		for(FlyingPlane plane : flyings) {		// �������ƶ�
			plane.step();
		}
	}
	
	public void bangAction() {				// �жϻ���
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
	
	public void outOfBoundsAction() {				// ɾ��Խ�������
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
	
	public void checkGameOverAction() {				// �ж���Ϸ����
		if(isGameOver()) {
			state = GAME_OVER;
		}
	}
	
	public boolean isGameOver() {					// �ж���ײ
		for(FlyingPlane plane : flyings) {
			if(fighter.hit(plane) && plane.isEnemy && plane.isExist) {						// ����
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
