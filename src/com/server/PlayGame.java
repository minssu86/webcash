package com.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayGame {
    int readyCount;
    boolean isGameOn;
    GameOver gameOver;
    PlayGame(){
        isGameOn = false;
        readyCount = 0;
    }
    public static void main(String[] args) {
        PlayGame playGame = new PlayGame();
        Blocks blocks = new Blocks();
        PlayTime playTime = new PlayTime();

        // 게임 시작 & 시간 셋팅
        ServerSocket serve = null;
        try{
            serve = new ServerSocket(8080);
            while (true){
                final Socket socket = serve.accept();
                Thread gameStartThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("현재 쓰레드 : " + Thread.currentThread());
                        InetAddress inet = null;
                        OutputStream os = null;
                        ObjectOutputStream oos = null;
                        InputStream is = null;
                        ObjectInputStream ois = null;
                        boolean isReady = false;
                        try{
                            inet = socket.getInetAddress();
                            is = socket.getInputStream();
                            os = socket.getOutputStream();
                            ois = new ObjectInputStream(is);
                            oos = new ObjectOutputStream(os);
                            while(true){
//                                System.out.println("준비 인원 : " + playGame.readyCount);
                                Object object = null;

                                // 플레이어 각자 접속 하여 게임준비
                                if(!isReady){
                                    object=ois.readObject();
                                    System.out.println(object.toString());
                                    if(object instanceof Boolean){
                                        if((Boolean) object){
                                            isReady = true;
                                            playGame.readyCount++;
                                            // 준비된 플레이어 카운트
                                            if(playGame.readyCount==2){
                                                System.out.println("여기는 게임시작 셋팅");
                                                playGame.isGameOn = true;
                                                Thread timeThread = new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            playTime.timeStart();
                                                        } catch (InterruptedException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    }
                                                });
                                                timeThread.start();
                                            }
                                            blocks.makeBlock();
                                            oos.writeObject(blocks.getBlockList());
                                            continue;
                                        } else {
                                            break;
                                        }
                                    }
                                }

                                if(playGame.isGameOn) {
                                    String temp = playTime.responseTime;
                                    oos.writeObject(temp);
                                    oos.flush();
                                }

                                Thread.sleep(300);

                            }

                        } catch (Exception e){
                            e.printStackTrace();
                        } finally {
                            try {
                                if(os!=null)os.close();
                                if(is!=null)is.close();
                                if(socket!=null)socket.close();
                            } catch (Exception e){
                                e.printStackTrace();
                                System.out.println("while 문 안");
                            }
                        }

                    }
                });
                System.out.println("여기는 몇번 오나?");
                gameStartThread.start();
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("while 문 밖");
        } finally {
            try {
                if(serve!=null)serve.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }







    }

}
