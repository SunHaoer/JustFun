package xin.newshooter;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.JFrame;

public class ShootJFrame extends JFrame {
	
	public static int WIDTH = 400;		// 窗体宽高
	public static int HEIGHT = 600;
	
	public ShootJFrame() {
		ShootJPanel game = new ShootJPanel();
		int pw = Toolkit.getDefaultToolkit().getScreenSize().width;			// 屏幕宽度
		int ph = Toolkit.getDefaultToolkit().getScreenSize().height;		// 屏幕高度
		this.setTitle("Shoot It"); 				// 设置标题
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 				// 线程默认关闭
		this.setBounds( (pw - WIDTH) / 2, (ph - HEIGHT) / 2, WIDTH, HEIGHT);	// 把窗口放在屏幕中间
		this.setResizable(false); 				// 禁止窗口最大化
		this.add(game);					// 加入游戏画面
		this.setVisible(true); 					// 窗体可见
//		this.Music();			// 此处有bug
	}
	
	private File file;
	private URI uri;
	private URL url;
	
	public void Music() {						// 添加背景音乐
		try {
			file = new File("I:\\Javaspace\\JustFun\\src\\xin\\newshooter\\music4.wav");					// 只识别wav
			uri = file.toURI();
			url = uri.toURL();
			AudioClip auu;
			auu = Applet.newAudioClip(url);
			auu.loop();		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		new ShootJFrame();
	}

}
