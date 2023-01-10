package com.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class PlayGame {
    Boolean isReady;
    int readyCount;
    boolean isGameOn;
    GameOver gameOver;
    PlayGame(){
        isReady = false;
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

                InetAddress inet = null;
                OutputStream os = null;
                ObjectOutputStream oos = null;
                InputStream is = null;
                ObjectInputStream ois = null;

                try{
                inet = socket.getInetAddress();
                is = socket.getInputStream();
                os = socket.getOutputStream();
                ois = new ObjectInputStream(is);
                oos = new ObjectOutputStream(os);
                while(true){
                    Object object = null;
                    if(!playGame.isReady){
                        object=ois.readObject();
                        System.out.println(object.toString());
                        if(object instanceof Boolean){
                            if((Boolean) object){
                                playGame.isReady = true;
                                playGame.readyCount++;
                                continue;
                            } else {
                                break;
                            }
                        }
                    }

                    if(playGame.isGameOn) {
                        while(true){
                            Thread.sleep(1000);
                            String temp = playTime.timeStart();
                            System.out.println(temp);
                            oos.writeObject(temp);
                            oos.flush();
                        }
                    }
                    if(playGame.readyCount==2){
                        playGame.isGameOn = true;
                    }
                }

                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    try {
                        if(os!=null)os.close();
                        if(is!=null)is.close();
                        socket.close();
                        serve.close();
                    } catch (Exception e){
                        e.printStackTrace();
                        System.out.println("while 문 안");
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("while 문 밖");
        }







    }

}
