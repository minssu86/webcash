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
        readyCount = 1;
        gameOver = new GameOver();
    }
    public static void main(String[] args) {
        PlayGame playGame = new PlayGame();
        Blocks blocks = new Blocks();
        PlayTime playTime = new PlayTime();
        Thread playTimeTread = new Thread(playTime);

        // 게임 시작 & 시간 셋팅
        ServerSocket serve = null;
        try{
            serve = new ServerSocket(8080);
            while(true){
                System.out.println("계속 도나?");
                final Socket socket = serve.accept();
                Thread gameStartThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
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
                                System.out.println("여긴 왔음");
                                Object object = null;
                                // 플레이어 각자 접속 하여 게임준비
                                if(!isReady){
                                    object=ois.readObject();
                                    if(object instanceof Boolean){
                                        if(Boolean.TRUE.equals(object)){
                                            isReady = true;
                                            playGame.readyCount++;
                                            // 준비된 플레이어 카운트
                                            if(playGame.readyCount==2){
                                                playGame.isGameOn = true;
                                                playTimeTread.start();
//                                                Thread timeThread = new Thread(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        try {
//                                                            playTime.timeStart();
//                                                        } catch (InterruptedException e) {
//                                                            throw new RuntimeException(e);
//                                                        }
//                                                    }
//                                                });
//                                                timeThread.start();
                                            }
                                            blocks.makeBlock();
                                            System.out.println(blocks.getBlockList());
                                            oos.writeObject(blocks.getBlockList());
                                        }
                                    }
                                }
                                if(playGame.isGameOn) {
                                    String temp = playTime.responseTime;
                                    oos.writeObject(temp);
                                    oos.flush();
                                }
                                Thread.sleep(300);
                                if(playGame.gameOver.isGameOver)break;
                            }
                        } catch (InterruptedException | IOException | ClassNotFoundException e){
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        } finally {
                            try {
                                if(os!=null)os.close();
                                if(is!=null)is.close();
                                if(socket!=null)socket.close();
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                    }
                });
                gameStartThread.start();

                System.out.println("끝난다");
                if(playGame.gameOver.isGameOver)break;
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(serve!=null)serve.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }







    }

}
