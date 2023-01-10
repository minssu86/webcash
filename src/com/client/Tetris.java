package com.client;

import com.server.GameOver;

import java.awt.Frame;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Tetris extends Frame{

	public static void main(String[] args) {
		
		MainGUI mainGUI = new MainGUI();

		// 화면 셋팅
		mainGUI.setBounds(800, 100, 780, 690);
		mainGUI.setResizable(false);
		mainGUI.setVisible(true);
		mainGUI.requestFocus();
		mainGUI.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mainGUI.dispose();
			}
		});

		mainGUI.startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainGUI.isGameOn = false;
				mainGUI.isWin = false;
				mainGUI.speedLevel = 0;
				mainGUI.isReady = false;
				mainGUI.isBeforeBlockStart = true;
				new CountDown(mainGUI.playGroundLabel);

				// 게임 스타트 및 타임 셋팅
				Thread gameStartThread = new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("gameStartThread");
						// socket 연결
//						byte[] arr = {(byte)192,(byte)168,(byte)240,127};
						byte[] arr = {(byte)192,(byte)168,(byte)35,99};
						InetAddress addr = null;
						int port = 8080;
						InputStream is = null;
						ObjectInputStream ois = null;
						OutputStream os= null;
						ObjectOutputStream oos = null;
						Socket socket = null;
						Object object;

						try {
							addr = InetAddress.getByAddress(arr);
							socket = new Socket(addr, port);
							os = socket.getOutputStream();
							is = socket.getInputStream();
							oos = new ObjectOutputStream(os);
							ois = new ObjectInputStream(is);

							do {
								if (!mainGUI.isReady) {
									oos.writeObject(true);
									mainGUI.isReady = true;
									oos.flush();
								}
								object = ois.readObject();
								if (mainGUI.isReady && object instanceof String) {
									System.out.println("왔나?3");
									String temp = object.toString();
									System.out.println(temp);
									mainGUI.timeLabel.setText(temp);
									if (!mainGUI.isGameOn) mainGUI.isGameOn = true;
								}
//						if(isGameOn && isBeforeBlockStart){
//							isBeforeBlockStart = false;
//							requestFocusInWindow();
//							Thread palyGameThread = new Thread(new Runnable() {
//								@Override
//								public void run() {
//									playGame();
//								}
//							});
//							palyGameThread.start();
//						}
							} while (!(object instanceof GameOver));

						} catch (IOException | ClassNotFoundException ex) {
							ex.printStackTrace();
						} finally {
							try {
								if(oos!=null)oos.close();
								if(ois!=null)ois.close();
								if(os!=null)os.close();
								if(is!=null)is.close();
								if(socket!=null)socket.close();
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}

					}
				});
				gameStartThread.start();

				// 게임 시작 -------------------------------------------
				mainGUI.requestFocusInWindow();
				mainGUI.playGame();

			}
		});

		mainGUI.endButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 게임 종료
				mainGUI.isGameOn = false;
			}
		});

		mainGUI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				/*
				  블럭 모션

				  1. 블럭이 ""초 마다 한칸씩 아래로 떨어짐
				  2. 방향키 ↑ 클릭(38) : 시계 방향으로 블럭 회전
				  3. 방향키 → 클릭(39) : 오른쪽으로 한 칸 이동
				  4. 방향키 ← 클릭(37) : 왼쪽으로 한 칸 이동
				  5. 방향키 ↓ 클릭(40) : 아래로 한 칸 이동
				  6. space bar 클릭 (32) : 가장 아래로 이동
				 */
				if(mainGUI.isGameOn) {
					switch (e.getKeyCode()) {
						case 32: mainGUI.block.pressSpaceKey(); break;
						case 37: mainGUI.block.pressLeftKey(); break;
						case 38: mainGUI.block.pressUpKey(); break;
						case 39: mainGUI.block.pressRightKey(); break;
						case 40: mainGUI.block.pressDownKey(); break;
						default: break;
					}
				}
			}
		});
		System.out.println("끝");
	}
	
}
