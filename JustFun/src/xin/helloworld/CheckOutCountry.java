package xin.helloworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class CheckOutCountry {
	public static void main(String[] args) {
		new Thread(new Sender()).start();
		new Thread(new Receiver()).start();
	}
}

class Sender implements Runnable {
	private DatagramSocket ds;
	private DatagramPacket dp;
	private Scanner sca;
	
	{
		try {
			ds = new DatagramSocket();
			sca = new Scanner(System.in);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			byte[] bs = sca.nextLine().getBytes();
			dp = new DatagramPacket(bs, bs.length, new InetSocketAddress("localhost", 8090));
			try {
				ds.send(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
}

class Receiver implements Runnable {
	private DatagramSocket ds;
	private DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
	private final int MAX = 10000;
	private String[] table = new String[MAX];
	
	{
		try {
			ds = new DatagramSocket(8090);
			fun1();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				ds.receive(dp);
				String year = new String(dp.getData(), 0, dp.getLength());
				if(table[Integer.parseInt(year)] != null) {
					System.out.println(table[Integer.parseInt(year)]);
				} else {
					System.out.println("没有");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fun1() {
		File file = new File("information.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while( (line = br.readLine()) != null) {
				String strs[] = line.split("\\\\");
				table[Integer.parseInt(strs[0])] = strs[1];
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
