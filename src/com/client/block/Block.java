package com.client.block;

import com.client.Tetris;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;


public abstract class Block implements Serializable {
	int[] blockNum;
	Label[][] topMenuLabel;
	
	boolean isGameOn = true;
    public Color color;
    Label[][] playGroundArr;
    boolean isStop;
    public int[] a = new int[2];
    public int[] b = new int[2];
    public int[] c = new int[2];
    public int[] d = new int[2];
	int blockDirection;
    
    // idx 1 = 가로
    // idx 0 = 세로

    public abstract boolean setBlock(Label[][] playGroundArr);
    
    public void pressSpaceKey() {
		resetColor();
		int moveConut = checkTheLowestPositon();
		a[0] += moveConut;
		b[0] += moveConut;
		c[0] += moveConut;
		d[0] += moveConut;
		changeColor();
    }


	public void pressRightKey(){
    	if(!checkIsLastRightSide() && !checkIsBlockRightSide()) {
			resetColor();
			++a[1];
			++b[1];
			++c[1];
			++d[1];
			changeColor();
    	}
    }

    public void pressLeftKey(){
    	if(!checkIsLastLeftSide() && !checkIsBlockLeftSide()) {
			resetColor();
			--a[1];
			--b[1];
			--c[1];
			--d[1];
			changeColor();
    	}
    }

    public abstract void pressUpKey();

    public void pressDownKey(){
    	if(!checkIsBottom()) {
    		if(!checkIsBlockBelow()) {
    			resetColor();
    			++a[0];
    			++b[0];
    			++c[0];
    			++d[0];
    			changeColor();
    		} else {
    			isStop = true;
    			checkGameOver();
    		}
    	} else {
    		isStop = true;
    		checkGameOver();
    	}
    }

    protected void resetColor(){
        this.playGroundArr[a[0]][a[1]].setBackground(new Color(12, 6, 6));
        this.playGroundArr[b[0]][b[1]].setBackground(new Color(12, 6, 6));
        this.playGroundArr[c[0]][c[1]].setBackground(new Color(12, 6, 6));
        this.playGroundArr[d[0]][d[1]].setBackground(new Color(12, 6, 6));
    }

    protected void changeColor(){
        this.playGroundArr[a[0]][a[1]].setBackground(color);
        this.playGroundArr[b[0]][b[1]].setBackground(color);
        this.playGroundArr[c[0]][c[1]].setBackground(color);
        this.playGroundArr[d[0]][d[1]].setBackground(color);
    }
    

    // 가장 아래 빈 공간 확인 =================================================
    private int checkTheLowestPositon() {
		playGroundArr[a[0]][a[1]].getBackground();
		return 4;
	}
    
    // 왼쪽 벽 확인 =================================================
    private boolean checkIsLastLeftSide() {
    	return a[1]==0 || b[1]==0 || c[1]==0 || d[1]==0;
	}
    
    // 왼쪽 블럭 확인
    private boolean checkIsBlockLeftSide() {
    	boolean boo = false;
    	// check a
    	int[] nowA = {a[0], a[1]};
    	int[] leftA = {a[0], a[1]-1};
    	int[] nowB = {b[0], b[1]};
    	int[] leftB = {b[0], b[1]-1};
    	int[] nowC = {c[0], c[1]};
    	int[] leftC = {c[0], c[1]-1};
    	int[] nowD = {d[0], d[1]};
    	int[] leftD = {d[0], d[1]-1};
    	
    	// check a
    	if(!Arrays.equals(leftA, nowB) && !Arrays.equals(leftA, nowC) && !Arrays.equals(leftA, nowD)) {
        	Color checkColor = playGroundArr[leftA[0]][leftA[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
        	if(boo) return true;
    	}
    	
    	// check b
    	if(!Arrays.equals(leftB, nowA) && !Arrays.equals(leftB, nowC) && !Arrays.equals(leftB, nowD)) {
        	Color checkColor = playGroundArr[leftB[0]][leftB[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
        	if(boo) return true;
    	}

    	// check c
    	if(!Arrays.equals(leftC, nowA) && !Arrays.equals(leftC, nowB) && !Arrays.equals(leftC, nowD)) {
        	Color checkColor = playGroundArr[leftC[0]][leftC[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
        	if(boo) return true;
    	}

    	// check d
    	if(!Arrays.equals(leftD, nowA) && !Arrays.equals(leftD, nowB) && !Arrays.equals(leftD, nowC)) {
        	Color checkColor = playGroundArr[leftD[0]][leftD[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
			return boo;
    	}
    	return false;
    }
    
    // 오른쪽 벽 확인 =================================================
    private boolean checkIsLastRightSide() {
    	return a[1]==9 || b[1]==9 || c[1]==9 || d[1]==9;
	}
    
    // 오른쪽 블럭 확인
    private boolean checkIsBlockRightSide() {
    	boolean boo = false;
    	// check a
    	int[] nowA = {a[0], a[1]};
    	int[] rightA = {a[0], a[1]+1};
    	int[] nowB = {b[0], b[1]};
    	int[] rightB = {b[0], b[1]+1};
    	int[] nowC = {c[0], c[1]};
    	int[] rightC = {c[0], c[1]+1};
    	int[] nowD = {d[0], d[1]};
    	int[] rightD = {d[0], d[1]+1};
    	
    	// check a
    	if(!Arrays.equals(rightA, nowB) && !Arrays.equals(rightA, nowC) && !Arrays.equals(rightA, nowD)) {
        	Color checkColor = playGroundArr[rightA[0]][rightA[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
        	if(boo) return true;
    	}
    	
    	// check b
    	if(!Arrays.equals(rightB, nowA) && !Arrays.equals(rightB, nowC) && !Arrays.equals(rightB, nowD)) {
        	Color checkColor = playGroundArr[rightB[0]][rightB[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
        	if(boo) return true;
    	}

    	// check c
    	if(!Arrays.equals(rightC, nowA) && !Arrays.equals(rightC, nowB) && !Arrays.equals(rightC, nowD)) {
        	Color checkColor = playGroundArr[rightC[0]][rightC[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
        	if(boo) return true;
    	}

    	// check d
    	if(!Arrays.equals(rightD, nowA) && !Arrays.equals(rightD, nowB) && !Arrays.equals(rightD, nowC)) {
        	Color checkColor = playGroundArr[rightD[0]][rightD[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
			return boo;
    	}
    	return false;
    }
    
    // 바닥 확인 =================================================
    private boolean checkIsBottom() {
		return a[0]==19 || b[0]==19 || c[0]==19 || d[0]==19;
	}
    
    // 아래 블럭 확인
    private boolean checkIsBlockBelow() {
    	boolean boo = false;
    	// check a
    	int[] nowA = {a[0], a[1]};
    	int[] belowA = {a[0]+1, a[1]};
    	int[] nowB = {b[0], b[1]};
    	int[] belowB = {b[0]+1, b[1]};
    	int[] nowC = {c[0], c[1]};
    	int[] belowC = {c[0]+1, c[1]};
    	int[] nowD = {d[0], d[1]};
    	int[] belowD = {d[0]+1, d[1]};
    	
    	// check a
    	if(!Arrays.equals(belowA, nowB) && !Arrays.equals(belowA, nowC) && !Arrays.equals(belowA, nowD)) {
        	Color checkColor = playGroundArr[belowA[0]][belowA[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
        	if(boo) return true;
    	}
    	
    	// check b
    	if(!Arrays.equals(belowB, nowA) && !Arrays.equals(belowB, nowC) && !Arrays.equals(belowB, nowD)) {
        	Color checkColor = playGroundArr[belowB[0]][belowB[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
        	if(boo) return true;
    	}

    	// check c
    	if(!Arrays.equals(belowC, nowA) && !Arrays.equals(belowC, nowB) && !Arrays.equals(belowC, nowD)) {
        	Color checkColor = playGroundArr[belowC[0]][belowC[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
        	if(boo) return true;
    	}

    	// check d
    	if(!Arrays.equals(belowD, nowA) && !Arrays.equals(belowD, nowB) && !Arrays.equals(belowD, nowC)) {
        	Color checkColor = playGroundArr[belowD[0]][belowD[1]].getBackground();
        	boo = !checkColor.equals(new Color(12, 6, 6));
			return boo;
    	}
    	return false;
    }
    
    // gameOver
    private void checkGameOver() {
    	if(a[0]<2 || b[0]<2 || c[0]<2 || d[0]<2) {
			System.out.println("game 끝");
    		isGameOn=false;
			Tetris.isGameOff=true;
    		for (int i = 3; i < 9; i++) {
				for (int j = 2; j < 8; j++) {
					playGroundArr[i][j].setBackground(Color.white);
				}
			}
    	}
	}

    public void setNextBlock(int i, Label[][] topMenuLabelParam) {
    	topMenuLabel = topMenuLabelParam;
    	this.topMenuLabel[blockNum[0]+i][blockNum[1]].setBackground(color);
    	this.topMenuLabel[blockNum[2]+i][blockNum[3]].setBackground(color);
    	this.topMenuLabel[blockNum[4]+i][blockNum[5]].setBackground(color);
    	this.topMenuLabel[blockNum[6]+i][blockNum[7]].setBackground(color);
	}
    
}
