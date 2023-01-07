package com.client;

import java.awt.Color;
import java.awt.Label;

public class CountDown {

	int countDownTemp = 100;
	Label[][] playGroundArr;

	public CountDown(Label[][] playGroundArr){
		this.playGroundArr = playGroundArr;
		getNum0();
		
		getNum5();
		getNum4();
		getNum3();
		getNum2();
		getNum1();
		getNum0();
	}
	
	private void getNum5() {
		try {
			for (int i = 3; i < 10; i++) {
				for (int j = 2; j < 8; j++) {
					playGroundArr[i][j].setBackground(Color.white);
				}
			}
			for (int i = 4; i < 9; i+=2) {
				for (int j = 3; j < 7; j++) {
					playGroundArr[i][j].setBackground(Color.gray);
				}
			}
			playGroundArr[5][3].setBackground(Color.gray);
			playGroundArr[7][6].setBackground(Color.gray);
			Thread.sleep(countDownTemp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	private void getNum4() {
		try {
			for (int i = 3; i < 10; i++) {
				for (int j = 2; j < 8; j++) {
					playGroundArr[i][j].setBackground(Color.white);
				}
			}
			for (int i = 4; i < 9; i++) {
				playGroundArr[i][6].setBackground(Color.gray);
			}
			playGroundArr[4][3].setBackground(Color.gray);
			playGroundArr[5][3].setBackground(Color.gray);
			playGroundArr[6][3].setBackground(Color.gray);
			playGroundArr[6][4].setBackground(Color.gray);
			playGroundArr[6][5].setBackground(Color.gray);	
			Thread.sleep(countDownTemp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void getNum3() {
		try {
			for (int i = 3; i < 10; i++) {
				for (int j = 2; j < 8; j++) {
					playGroundArr[i][j].setBackground(Color.white);
				}
			}
			for (int i = 4; i < 9; i+=2) {
				for (int j = 3; j < 7; j++) {
					playGroundArr[i][j].setBackground(Color.gray);
				}
			}
			playGroundArr[5][6].setBackground(Color.gray);
			playGroundArr[7][6].setBackground(Color.gray);	
			Thread.sleep(countDownTemp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void getNum2() {
		try {
			for (int i = 3; i < 10; i++) {
				for (int j = 2; j < 8; j++) {
					playGroundArr[i][j].setBackground(Color.white);
				}
			}
			for (int i = 4; i < 9; i+=2) {
				for (int j = 3; j < 7; j++) {
					playGroundArr[i][j].setBackground(Color.gray);
				}
			}
			playGroundArr[5][6].setBackground(Color.gray);
			playGroundArr[7][3].setBackground(Color.gray);	
			Thread.sleep(countDownTemp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void getNum1() {
		try {
			for (int i = 3; i < 10; i++) {
				for (int j = 2; j < 8; j++) {
					playGroundArr[i][j].setBackground(Color.white);
				}
			}
			for (int i = 4; i < 9; i++) {
				playGroundArr[i][5].setBackground(Color.gray);
			}
			playGroundArr[4][4].setBackground(Color.gray);
			playGroundArr[8][3].setBackground(Color.gray);
			playGroundArr[8][4].setBackground(Color.gray);
			playGroundArr[8][6].setBackground(Color.gray);
			Thread.sleep(countDownTemp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void getNum0() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				playGroundArr[i][j].setBackground(new Color(12,6,6));
			}
		}
	}
}
