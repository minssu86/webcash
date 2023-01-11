package com.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class PlayGame {
    int readyCount;
    boolean isGameOn;
    boolean isCountDownOn;
    GameOver gameOver;
    PlayGame(){
        isGameOn = false;
        readyCount = 1;
        gameOver = new GameOver();
        isCountDownOn = false;
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
                                // 플레이어 각자 접속 하여 게임준비
                                if(!isReady){
                                    System.out.println("게임 준비 전");
                                    Object object=ois.readObject();
                                    if(object instanceof Boolean){
                                        if(Boolean.TRUE.equals(object)){
                                            System.out.println("게임 준비 됨");
                                            isReady = true;
                                            playGame.readyCount++;
                                            // 준비된 플레이어 카운트
                                            if(playGame.readyCount==2){
                                                System.out.println("2명의 플레이어 게임 준비 됨");
                                                playGame.isCountDownOn = true;
                                            }
                                            blocks.makeBlock();
                                            oos.writeObject(blocks.getBlockList());
                                            oos.flush();
                                        }
                                    }
                                }

                                // 각 유저에게 카운트다운 보내기
                                if (playGame.isCountDownOn){
                                    System.out.println("카운트 다운 보내기");
                                    int countNum = 5;
                                    while (countNum>-1){
                                        oos.writeObject(new CountDownStart(countNum--));
                                        Thread.sleep(1000);
                                        oos.flush();
                                    }
                                    // 카운트 다운이 끝나면 게임 실행
                                    if(playGame.isCountDownOn){
                                        System.out.println("게임 시작 쓰레드");
                                        playTimeTread.start();
                                    }
                                    playGame.isCountDownOn =false;
                                    playGame.isGameOn = true;
                                    System.out.println("카운트 다운 보내기 완료");
                                }

                                // 게임 실행중일때 사용하는 조건문
                                if(playGame.isGameOn) {
                                    System.out.println("게임 실행 중");
                                    String temp = playTime.responseTime;
                                    System.out.println(temp);
                                    oos.writeObject(temp);
                                    oos.flush();
                                    System.out.println("1");

                                    Object object = ois.readObject();
                                    if(object instanceof GameOver){
                                        System.out.println("한쪽 게임 종료");
                                        oos.writeObject(playGame.gameOver);
                                        oos.flush();
                                        System.out.println("반대쪽도 게임 종료 시키기");
                                        playGame.gameOver.isGameOver=true;
                                    }
                                }



                                Thread.sleep(500);
                                if(playGame.gameOver.isGameOver)break;
                            }
                        } catch (SocketException e){
                            System.out.println("소켓 예외 처리 필요");
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
