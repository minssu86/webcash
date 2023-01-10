package com.server;

import com.client.block.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Blocks {

    private final Block[] blockArr;
    private List<Block> blockList;

    public Blocks(){
        blockArr = new Block[]{new Block1(), new Block2(), new Block3(),
                new Block4(), new Block5(), new Block6(), new Block7()};
    }

    public void makeBlock(){
        blockList = new LinkedList<>();
        for (int i = 0; i<50;i++) {
            Block block = blockArr[new Random().nextInt(6)];
            blockList.add(block);
        }
    }

    public List<Block> getBlockList(){
        return blockList;
    }

}
