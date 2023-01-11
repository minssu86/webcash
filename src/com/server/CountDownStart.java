package com.server;

import java.io.Serializable;

public class CountDownStart implements Serializable {
    public final int timeCount;
    public CountDownStart(int num){
        timeCount = num;
    }
}
