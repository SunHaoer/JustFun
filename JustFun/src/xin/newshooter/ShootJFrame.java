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
	
	public static int WIDTH = 400;		// ������
	public static int HEIGHT = 600;
	
	public ShootJFrame() {
		ShootJPanel game = new ShootJPanel();
		int pw = Toolkit.getDefaultToolkit().getScreenSize().width;			// ��Ļ���
		int ph = Toolkit.getDefaultToolkit().getScreenSize().height;		// ��Ļ�߶�
		this.setTitle("Shoot It"); 				// ���ñ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 				// �߳�Ĭ�Ϲر�
		this.setBounds( (pw - WIDTH) / 2, (ph - HEIGHT) / 2, WIDTH, HEIGHT);	// �Ѵ��ڷ�����Ļ�м�
		this.setResizable(false); 				// ��ֹ�������
		this.add(game);					// ������Ϸ����
		this.setVisible(true); 					// ����ɼ�
//		this.Music();			// �˴���bug
	}
	
	private File file;
	private URI uri;
	private URL url;
	
	public void Music() {						// ��ӱ�������
		try {
			file = new File("I:\\Javaspace\\JustFun\\src\\xin\\newshooter\\music4.wav");					// ֻʶ��wav
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
