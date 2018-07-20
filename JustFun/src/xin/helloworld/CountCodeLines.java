package xin.helloworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 统计某工作空间的代码数量
 * @author 隼耗
 *
 */

public class CountCodeLines {
	public static final int MAX = 10;
	public static int table[] = new int[MAX]; 			// table下标对应语言种类, value对应代码数量
	
	public static void fun2(File file, int[] table, int index) {		// 统计代码数量
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			try {
				while(reader.readLine() != null) {
					table[index]++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();						// 关闭reader
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void fun1(File file) {			// 遍历文件夹
		if(file.isDirectory()) {		// 目录
			for(File f : file.listFiles()) {
				fun1(f);
			}
		} else if(file.getName().endsWith(".cpp") || file.getName().endsWith(".c")) {		// C/C++
			fun2(file, table, 0);
		} else if(file.getName().endsWith(".java")) {		// Java
			fun2(file, table, 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sca = new Scanner(System.in);
		String path = sca.next();		// 输入目标工作空间的绝对路径
		sca.close();
		File file = new File(path);
		if(file.exists()) {
			fun1(file);
			System.out.println("C/C++: " + table[0]);
			System.out.println("Java : " + table[1]);			
		} else {
			System.out.println("该文件夹不存在");
		}
	}
}
