package com.client.block;

import java.awt.*;

public class Block1 extends Block {

    public Block1(){
        color = new Color(9, 158, 168);
        blockNum = new int[]{1, 0, 1, 1, 1, 2, 1, 3};
    }

    @Override
    public boolean setBlock(int speedLevel, Label[][] playGroundArr) {
        this.playGroundArr = playGroundArr;
        this.speedLevel = speedLevel;
        a[0] = 0; a[1] = 3;
        b[0] = 0; b[1] = 4;
        c[0] = 0; c[1] = 5;
        d[0] = 0; d[1] = 6;
        changeColor();
        isStop = false;
        while (!isStop) {
            try {
                Thread.sleep(1000 - this.speedLevel * 100L);
                pressDownKey();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return isGameOn;
    }

    @Override
    public void pressUpKey() {
    	
    }

}


