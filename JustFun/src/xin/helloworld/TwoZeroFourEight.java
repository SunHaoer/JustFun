package xin.helloworld;

import java.util.Scanner;

public class TwoZeroFourEight {

	static final int MAX = 4; // �߳�
	static int table[][] = new int[MAX][MAX]; // table
	static boolean isBegin = false; // �ж���Ϸ��ʼisBegin
	static boolean isEnd = true; // �ж���Ϸ����isEnd
	static int score = 0;
	static char ch;

	void firstPrint() {
		System.out.println("W��ʾ����;");
		System.out.println("S��ʾ����;");
		System.out.println("A��ʾ����;");
		System.out.println("D��ʾ����;");
		return;
	}

	void print() {
		if (isBegin == false)
			System.out.println("------��Ϸ��ʼ----------");
		else
			System.out.println("------�����------------");
		isBegin = true;
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				System.out.printf("%5d", table[i][j]);
			}
			System.out.println("\n");
		}
		System.out.println("------score = " + score + " -----\n");
	}

	int isZero() {             // �ж��������2��0
	    int x = (int)(Math.random() * 10);
	    if(x % 8 != 0) return 0;
	    else return 2;
	}

	void createTable() {      // ��ʼ������
	    for(int i = 0; i < MAX; i++) {
	        for(int j = 0; j < MAX; j++) {
	            table[i][j] = isZero();
	        }
	    }
	    return;
	}

	void reCreate() {
	    if(ch == 'w' || ch == 'W') {                  // ����0����
	        for(int j = 0; j < MAX; j++) {
	            for(int k = 0; k < 3; k++) {
	                if(table[0][j] == 0) {
	                    isEnd = false;
	                    table[0][j] = table[1][j];
	                    table[1][j] = 0;
	                }
	                if(table[1][j] == 0) {
	                    isEnd = false;
	                    table[1][j] = table[2][j];
	                    table[2][j] = 0;
	                }
	                if(table[2][j] == 0) {
	                    isEnd = false;
	                    table[2][j] = table[3][j];
	                    table[3][j] = 0;
	                }

	            }
	        }
	    } else if(ch == 's' || ch == 'S') {
	       for(int j = 0; j < MAX; j++) {
	            for(int k = 0; k < 3; k++) {
	                if(table[3][j] == 0) {
	                    isEnd = false;
	                    table[3][j] = table[2][j];
	                    table[2][j] = 0;
	                }
	                if(table[2][j] == 0) {
	                    isEnd = false;
	                    table[2][j] = table[1][j];
	                    table[1][j] = 0;
	                }
	                if(table[1][j] == 0) {
	                    isEnd = false;
	                    table[1][j] = table[0][j];
	                    table[0][j] = 0;
	                }

	            }
	       }
	    } else if(ch == 'd' || ch == 'D') {
	        for(int i = 0; i < MAX; i++) {
	            for(int k = 0; k < 3; k++) {
	                if(table[i][3] == 0) {
	                    isEnd = false;
	                    table[i][3] = table[i][2];
	                    table[i][2] = 0;
	                }
	                if(table[i][2] == 0) {
	                    isEnd = false;
	                    table[i][2] = table[i][1];
	                    table[i][1] = 0;
	                }
	                if(table[i][1] == 0) {
	                    isEnd = false;
	                    table[i][1] = table[i][0];
	                    table[i][0] = 0;
	                }

	            }
	       }
	    } else if(ch == 'a' || ch == 'A') {
	        for(int i = 0; i < MAX; i++) {
	            for(int k = 0; k < 3; k++) {
	                if(table[i][0] == 0) {
	                    isEnd = false;
	                    table[i][0] = table[i][1];
	                    table[i][1] = 0;
	                }
	                if(table[i][1] == 0) {
	                    isEnd = false;
	                    table[i][1] = table[i][2];
	                    table[i][2] = 0;
	                }
	                if(table[i][2] == 0) {
	                    isEnd = false;
	                    table[i][2] = table[i][3];
	                    table[i][3] = 0;
	                }

	            }
	        }
	    }


	    for(int i = 0; i < MAX; i++) {
	        for(int j = 0; j < MAX; j++) {
	            isEnd = false;
	            if(table[i][j] == 0) table[i][j] = isZero();
	        }
	    }
	    return;
	}

	void changeW() {                    // �����ƶ�w
	    isEnd = true;
	    for(int j = 0; j < MAX; j++) {
	        for(int i = 0; i < MAX - 1; i++) {
	            if( (table[i][j] == table[i + 1][j]) && (table[i][j] != 0) ) {        // ������
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i + 1][j];
	                score += table[i][j];
	                table[i + 1][j] = 0;                    // ���������Ϊ0
	                continue;
	            } else if( (i < MAX - 2) && (table[i][j] == table[i + 2][j]) && (table[i][j] != 0) && (table[i + 1][j] == 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i + 2][j];
	                score += table[i][j];
	                table[i + 2][j] = 0;
	                continue;
	            } else if( (i < MAX - 3) && (table[i][j] == table[i + 3][j]) && (table[i][j] != 0 ) && (table[i + 1][j] == 0) && (table[i + 2][j] == 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i + 3][j];
	                score += table[i][j];
	                table[i + 3][j] = 0;
	                continue;
	            }
	        }
	    }
	    reCreate();             // �ؽ�table
	    print();
	    return;
	}

	void changeS() {            // �����ƶ�
	    isEnd = true;
	    for(int j = 0; j < MAX; j++) {
	        for(int i = MAX - 1; i >= 1; i--) {
	            if( (table[i - 1][j] == table[i][j]) && (table[i - 1][j] != 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i - 1][j];
	                score += table[i][j];
	                table[i - 1][j] = 0;
	                continue;
	            } else if( (i >= 2) && (table[i][j] == table[i - 2][j]) && (table[i][j] != 0) && (table[i - 1][j] == 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i - 2][j];
	                score += table[i][j];
	                table[i - 2][j] = 0;
	                continue;
	            } else if( (i >= 3) && (table[i][j] == table[i - 3][j]) && (table[i][j] != 0) && (table[i - 1][j] == 0) && (table[i - 2][j] == 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i - 3][j];
	                score += table[i][j];
	                table[i - 3][j] = 0;
	                continue;
	            }
	        }
	    }
	    reCreate();
	    print();
	    return;
	}

	void changeD() {            // �����ƶ�
	    isEnd = true;
	    for(int i = 0; i < MAX; i++) {
	        for(int j = MAX - 1; j >= 1; j--) {
	            if( (table[i][j - 1] == table[i][j]) && (table[i][j - 1] != 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i][j - 1];
	                score += table[i][j];
	                table[i][j - 1] = 0;
	                continue;
	            } else if( (j >= 2) && (table[i][j] == table[i][j - 2]) && (table[i][j] != 0) && (table[i][j - 1] == 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i][j - 2];
	                score += table[i][j];
	                table[i][j - 2] = 0;
	                continue;
	            } else if( (j >= 3) && (table[i][j] == table[i][j - 3]) && (table[i][j] != 0) && (table[i][j - 1] == 0) && (table[i][j - 2] == 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i][j - 3];
	                score += table[i][j];
	                table[i][j - 3] = 0;
	                continue;
	            }
	        }
	    }
	    reCreate();
	    print();
	    return;
	}

	void changeA() {                    // �����ƶ�w
	    isEnd = true;
	    for(int i = 0; i < MAX; i++) {
	        for(int j  = 0; j < MAX - 1; j++) {
	            if( (table[i][j] == table[i][j + 1]) && (table[i][j] != 0) ) {        // ������
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i][j + 1];
	                score += table[i][j];
	                table[i][j + 1] = 0;
	                continue;
	            } else if( (j < MAX - 2) && (table[i][j] == table[i][j + 2]) && (table[i][j] != 0) && (table[i][j + 1] == 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i][j + 2];
	                score += table[i][j];
	                table[i][j + 2] = 0;
	                continue;
	            } else if( (j < MAX - 3) && (table[i][j] == table[i][j + 3]) && (table[i][j] != 0) && (table[i][j + 1] == 0) && (table[i][j + 2] == 0) ) {
	                isEnd = false;
	                table[i][j] = table[i][j] + table[i][j + 3];
	                score += table[i][j];
	                table[i][j + 3] = 0;
	                continue;
	            }
	        }
	    }
	    reCreate();             // ������2/0
	    print();
	    return;
	}

	public static void main(String[] args) {
		Scanner sca = new Scanner(System.in);
		TwoZeroFourEight test = new TwoZeroFourEight();

		test.firstPrint();
		test.createTable();
		test.print();
		ch = sca.next().charAt(0);
		while (true) {
			if (ch == 'w' || ch == 'W') { // �����ƶ�
				test.changeW();
			} else if (ch == 's' || ch == 'S') { // �����ƶ�
				test.changeS();
			} else if (ch == 'd' || ch == 'D') {
				test.changeD();
			} else if (ch == 'a' || ch == 'A') {
				test.changeA();
			}
			ch = sca.next().charAt(0);
			if(isEnd == true) break;
		}
		sca.close();
		System.out.println("------Game Over-----------");
		System.out.println("------���յ÷�:" + score + "-------");
	}

}
