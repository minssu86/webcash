package com.client;

import java.awt.Color;
import java.awt.Label;

public class CountDown {

	int countDownTemp = 100;
	Label[][] playGroundArr;

	public CountDown(Label[][] playGroundArr){
		this.playGroundArr = playGroundArr;
		getNum0();
	}

	public void setCount(int countNum){
		switch (countNum){
			case 5 : getNum5(); break;
			case 4 : getNum4(); break;
			case 3 : getNum3(); break;
			case 2 : getNum2(); break;
			case 1 : getNum1(); break;
			case 0 : getNum0(); break;
		}
	}
	
	private void getNum5() {
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
	}
	private void getNum4() {
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
	}
	private void getNum3() {
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
	}
	private void getNum2() {
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
	}
	private void getNum1() {
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
	}
	private void getNum0() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				playGroundArr[i][j].setBackground(new Color(12,6,6));
			}
		}
	}
}
