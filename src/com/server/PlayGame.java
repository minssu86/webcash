package com.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class PlayGame {
    int closeCount = 0;
    int readyCount;
    boolean isGameOn;
    int gameOverCount;
    boolean isCountDownOn;
    GameOver gameOver;
    int passBlock;
    int startCount;
    PlayGame(){
        closeCount = 0;
        startCount = 0;
        isGameOn = false;
        readyCount = 0;
        gameOverCount = 0;
        gameOver = new GameOver();
        isCountDownOn = false;
        passBlock = 0;
    }
    public static void main(String[] args) {
        PlayGame playGame = new PlayGame();
        Blocks blocks = new Blocks();
        PlayTime playTime = new PlayTime();
        Thread playTimeThread = new Thread(playTime);
        // 게임 시작 & 시간 셋팅
        ServerSocket serve = null;
        try{
            serve = new ServerSocket(8080);
            while(true){
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
                                // 플레이어 각자 접속 하여 게임준비
                                if(!isReady){
                                    Object object=ois.readObject();
                                    if(object instanceof Boolean){
                                        if(Boolean.TRUE.equals(object)){
                                            isReady = true;
                                            playGame.readyCount++;
                                            // 준비된 플레이어 카운트
                                            if(playGame.readyCount==2){
                                                playGame.isCountDownOn = true;
                                                blocks.makeBlock();
                                            }
                                            oos.writeObject(blocks.getBlockList());
                                            oos.flush();
                                        }
                                    }
                                }

                                // 각 유저에게 카운트다운 보내기
                                if (playGame.isCountDownOn){
                                    int countNum = 5;
                                    while (countNum>-1){
                                        oos.writeObject(new CountDownStart(countNum--));
                                        Thread.sleep(1000);
                                        oos.flush();
                                    }

                                    if(playGame.passBlock++ < 2){
                                        oos.writeObject(blocks.getBlockList());
                                        oos.flush();
                                    }


                                    // 카운트 다운이 끝나면 게임 실행
                                    if(playGame.isCountDownOn){
                                        playTimeThread.start();
                                    }
                                    playGame.isCountDownOn =false;
                                    playGame.isGameOn = true;
                                }

                                // 게임 실행중일때 사용하는 조건문
                                if(playGame.isGameOn && playGame.passBlock >1) {
                                    String temp = playTime.responseTime;
                                    System.out.println(temp);
                                    oos.writeObject(temp);
                                    oos.flush();
                                    Object object = ois.readObject();
                                    if(object instanceof GameOver){
                                        playGame.gameOver.isGameOver=true;
                                    }
                                    if(playGame.gameOver.isGameOver && playGame.gameOverCount++ <2){
                                        GameOver setGameOver = new GameOver();
                                        oos.writeObject(setGameOver);
                                        oos.flush();
                                        if(playGame.gameOverCount == 2){
                                            playGame.isGameOn=false;
                                        }
                                    }
                                }
                                Thread.sleep(300);
                                if(playGame.gameOverCount == 2)break;
                            }
                        } catch (SocketException e){
                            e.printStackTrace();
                        } catch (InterruptedException | IOException | ClassNotFoundException e){
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        } finally {
                            try {
                                if(!playGame.isGameOn){
                                    playGame.closeCount++;
                                    if(os!=null)os.close();
                                    if(is!=null)is.close();
                                    if(socket!=null){
                                        socket.close();
                                    }
                                    if(playGame.gameOver.isGameOver && playGame.closeCount>1) {
                                        playGame.closeCount = 0;
                                        playGame.isGameOn = false;
                                        playGame.readyCount = 0;
                                        playGame.gameOverCount = 0;
                                        playGame.gameOver = new GameOver();
                                        playGame.isCountDownOn = false;
                                        playGame.passBlock = 0;
                                    };
                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
                gameStartThread.start();
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
