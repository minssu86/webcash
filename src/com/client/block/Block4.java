package com.client.block;

import com.client.Tetris;

import java.awt.*;

//box type
public class Block4 extends Block {

    public Block4(){
        color = new Color(186, 189, 37);
        blockNum = new int[]{0,0,0,1,1,0,1,1};
    }

    @Override
    public boolean setBlock(Label[][] playGroundArr) {

        this.playGroundArr = playGroundArr;
        a[0] = 0; a[1] = 4;
        b[0] = 0; b[1] = 5;
        c[0] = 1; c[1] = 4;
        d[0] = 1; d[1] = 5;
        changeColor();
        isStop = false;
        while (!isStop) {
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
        // 해당 블럭의 경우 해당 키 반은 없음
    }


}
