package com.client.block;

import com.client.Tetris;

import java.awt.*;
import java.util.Objects;

public class Block1 extends Block {

    public Block1(){
        color = new Color(9, 158, 168);
        blockNum = new int[]{1, 0, 1, 1, 1, 2, 1, 3};
        blockDirection = 1;
    }

    @Override
    public boolean setBlock(Label[][] playGroundArr) {
        this.playGroundArr = playGroundArr;
        a[0] = 0; a[1] = 3;
        b[0] = 0; b[1] = 4;
        c[0] = 0; c[1] = 5;
        d[0] = 0; d[1] = 6;
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
        switch (blockDirection%2){
            case 0: direction0(); break;
            case 1: direction1(); break;
        }
        changeColor();
        blockDirection++;
    }

    private void direction1() {
        a[0] -=1;
        a[1] +=1;
        c[0] +=1;
        c[1] -=1;
        d[0] +=2;
        d[1] -=2;
//        int a0 = a[0] -1;
//        int a1 = a[1] +1;
//        int c0 = c[0] +1;
//        int c1 = c[1] -1;
//        int d0 = d[0] +2;
//        int d1 = d[1] -2;
//        boolean boo = checkSide(d1);

    }

//    private boolean checkSide(int d1) {
//        if(d1 == 9){
//            a[1] -= 2;
//            b[1] -= 2;
//            c[1] -= 2;
//            d[1] -= 2;
//            return false;
//        }else if(d1 == 8){
//            a[1] -= 1;
//            b[1] -= 1;
//            c[1] -= 1;
//            d[1] -= 1;
//            return false;
//        }else if(d1 == 0) {
//            a[1] += 1;
//            b[1] += 1;
//            c[1] += 1;
//            d[1] += 1;
//            return false;
//        }
//        return true;
//    }

    private boolean checkBelowBlock(int i, int j) {
        boolean isOk = true;
        isOk = Objects.equals(playGroundArr[a[0]+i][a[1]+j].getBackground(), new Color(12, 6, 6));
        isOk = Objects.equals(playGroundArr[b[0]+i][b[1]+j].getBackground(), new Color(12, 6, 6));
        isOk = Objects.equals(playGroundArr[c[0]+i][c[1]+j].getBackground(), new Color(12, 6, 6));
        isOk = Objects.equals(playGroundArr[d[0]+i][d[1]+j].getBackground(), new Color(12, 6, 6));

        return isOk;
    }

    private void direction0() {
        a[0] +=1;
        a[1] -=1;
        c[0] -=1;
        c[1] +=1;
        d[0] -=2;
        d[1] +=2;
    }

}


