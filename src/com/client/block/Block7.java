package com.client.block;

import com.client.Tetris;

import java.awt.*;

public class Block7 extends Block{

    public Block7(){
        color = new Color(13, 126, 11);
        blockNum = new int[]{0,0,0,1,1,1,1,2};
        blockDirection = 1;
    }

    @Override
    public boolean setBlock(Label[][] playGroundArr) {
        this.playGroundArr = playGroundArr;
        a[0] = 0; a[1] = 4;
        b[0] = 0; b[1] = 5;
        c[0] = 1; c[1] = 5;
        d[0] = 1; d[1] = 6;
        changeColor();
        isStop = false;
        while (!isStop) {
            if(Tetris.isGameOff) {
                resetColor();
                break;
            }
            try {
                Thread.sleep(1000 - Tetris.speedLevel * 100L);
                pressDownKey();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return isGameOn;
    }

    @Override
    public void pressUpKey() {
        resetColor();
        switch (blockDirection%4){
            case 1: direction1(); break;
            case 2: direction2(); break;
            case 3: direction3(); break;
            case 0: direction0(); break;
        }
        changeColor();
        blockDirection++;
    }

    private void direction1() {
        a[0] -=1;
        a[1] +=1;
        c[0] -=1;
        c[1] -=1;
        d[1] -=2;
    }

    private void direction2() {
        a[0] +=1;
        a[1] +=1;
        c[0] -=1;
        c[1] +=1;
        d[0] -=2;
    }

    private void direction3() {
        a[0] +=1;
        a[1] -=1;
        c[0] +=1;
        c[1] +=1;
        d[1] +=2;
    }

    private void direction0() {
        a[0] -=1;
        a[1] -=1;
        c[0] +=1;
        c[1] -=1;
        d[0] +=2;
    }

}
