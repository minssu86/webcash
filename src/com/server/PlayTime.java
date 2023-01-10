package com.server;

import java.awt.*;

public class PlayTime {
    int timeCount;
    public PlayTime(){

    }

    public String timeStart(){
        timeCount++;
        String min = "0" + (timeCount/60);
        String sec = "";
        if(timeCount%60 < 10) {
            sec = "0" + (timeCount%60);
        } else {
            sec = timeCount%60 + "";
        }
        return min + ":" + sec;
    }

}
