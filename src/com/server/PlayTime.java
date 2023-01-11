package com.server;

public class PlayTime implements Runnable {
    int timeCount;
    String responseTime;
    int endTime;
    public PlayTime(){
        responseTime = "00:00";
        endTime = 600;
    }

    public void timeStart() throws InterruptedException {

        do {
            Thread.sleep(1000);
            timeCount++;
            String min = "0" + (timeCount / 60);
            String sec = "";
            if (timeCount % 60 < 10) {
                sec = "0" + (timeCount % 60);
            } else {
                sec = timeCount % 60 + "";
            }
            responseTime = min + ":" + sec;
        } while (timeCount != endTime);
    }

    @Override
    public void run() {
        try {
            timeStart();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
