package com.server;

import com.client.block.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Blocks implements Serializable {

    private final Block[] blockArr;
    private List<Block> blockList;

    public Blocks(){
        blockArr = new Block[]{new Block1(), new Block2(), new Block3(),
                new Block4(), new Block5(), new Block6(), new Block7()};
    }

    public void makeBlock(){
        blockList = new LinkedList<>();
        for (int i = 0; i<300;i++) {
            Block block = blockArr[new Random().nextInt(6)];
            blockList.add(block);
        }
    }

    public List<Block> getBlockList(){
        return blockList;
    }

}
