package com.client;

import java.awt.Color;
import java.awt.Label;

public class CountDown {

	Label[][] playGroundLabel;
	MainGUI mainGUI;

	public CountDown(MainGUI mainGUI){
		this.playGroundLabel = mainGUI.playGroundLabel;
		this.mainGUI = mainGUI;
		getNum0();
	}

	public void setCount(int countNum){
		switch (countNum){
			case 5 : getNum5(); break;
			case 4 : getNum4(); break;
			case 3 : getNum3(); break;
			case 2 : getNum2(); break;
			case 1 : getNum1(); break;
			case 0 : getNum0(); mainGUI.startButton.setLabel("running");break;
		}
	}
	
	private void getNum5() {
		for (int i = 3; i < 10; i++) {
			for (int j = 2; j < 8; j++) {
				mainGUI.playGroundLabel[i][j].setBackground(Color.white);
			}
		}
		for (int i = 4; i < 9; i+=2) {
			for (int j = 3; j < 7; j++) {
				playGroundLabel[i][j].setBackground(Color.gray);
			}
		}
		playGroundLabel[5][3].setBackground(Color.gray);
		playGroundLabel[7][6].setBackground(Color.gray);
	}
	private void getNum4() {
		for (int i = 3; i < 10; i++) {
			for (int j = 2; j < 8; j++) {
				playGroundLabel[i][j].setBackground(Color.white);
			}
		}
		for (int i = 4; i < 9; i++) {
			playGroundLabel[i][6].setBackground(Color.gray);
		}
		playGroundLabel[4][3].setBackground(Color.gray);
		playGroundLabel[5][3].setBackground(Color.gray);
		playGroundLabel[6][3].setBackground(Color.gray);
		playGroundLabel[6][4].setBackground(Color.gray);
		playGroundLabel[6][5].setBackground(Color.gray);
	}
	private void getNum3() {
		for (int i = 3; i < 10; i++) {
			for (int j = 2; j < 8; j++) {
				playGroundLabel[i][j].setBackground(Color.white);
			}
		}
		for (int i = 4; i < 9; i+=2) {
			for (int j = 3; j < 7; j++) {
				playGroundLabel[i][j].setBackground(Color.gray);
			}
		}
		playGroundLabel[5][6].setBackground(Color.gray);
		playGroundLabel[7][6].setBackground(Color.gray);
	}
	private void getNum2() {
		for (int i = 3; i < 10; i++) {
			for (int j = 2; j < 8; j++) {
				playGroundLabel[i][j].setBackground(Color.white);
			}
		}
		for (int i = 4; i < 9; i+=2) {
			for (int j = 3; j < 7; j++) {
				playGroundLabel[i][j].setBackground(Color.gray);
			}
		}
		playGroundLabel[5][6].setBackground(Color.gray);
		playGroundLabel[7][3].setBackground(Color.gray);
	}
	private void getNum1() {
		for (int i = 3; i < 10; i++) {
			for (int j = 2; j < 8; j++) {
				playGroundLabel[i][j].setBackground(Color.white);
			}
		}
		for (int i = 4; i < 9; i++) {
			playGroundLabel[i][5].setBackground(Color.gray);
		}
		playGroundLabel[4][4].setBackground(Color.gray);
		playGroundLabel[8][3].setBackground(Color.gray);
		playGroundLabel[8][4].setBackground(Color.gray);
		playGroundLabel[8][6].setBackground(Color.gray);
	}
	private void getNum0() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				playGroundLabel[i][j].setBackground(new Color(12,6,6));
			}
		}
	}
}
