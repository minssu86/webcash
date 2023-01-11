package com.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import com.client.block.Block;
import com.client.block.Block1;
import com.client.block.Block2;
import com.client.block.Block3;
import com.client.block.Block4;
import com.client.block.Block5;
import com.client.block.Block6;
import com.client.block.Block7;

public class MainGUI extends Frame{
	// Basic BackGround
	Panel chatPanel;
	Panel backGroundPanel;
	Panel menuPanel;
	
	// Menu BackGround
	Panel topMenuPanel;		
	Panel bottomMenuPanel;
	// Top Menu
	// Next blocks
	Panel topMenuGridPanel;
	Label[][] topMenuLabel;
	
	int speedLevel = 0;
	Label timeLabel;
	Label scoreLabel;
	Label[][] playGroundLabel = new Label[20][10];
	Block block;

	boolean isReady;
	boolean isBeforeBlockStart;
	boolean isGameOn=false;
	boolean isWin = false;

	// 버튼
	Button startButton;
	Button endButton;

	
	MainGUI(){
//		==========================================================================
//		전체 기본 틀
//		==========================================================================
		// Basic BackGround
		// Basic BackGround : chat
		chatPanel = new Panel();
		chatPanel.setLayout(null);
		chatPanel.setBackground(new Color(124,100,100));
		
		// Basic BackGround : play
		backGroundPanel = new Panel();
		backGroundPanel.setLayout(null);
		backGroundPanel.setBackground(new Color(85,83,83));
		
		// Basic BackGround : menu
		menuPanel = new Panel();
		menuPanel.setLayout(null);
		menuPanel.setBackground(new Color(124,100,100));
		
		
		// Basic BackGround 배치
		setLayout(null);
		chatPanel.setBounds(0,30,240,690);
		backGroundPanel.setBounds(240,30,360,690);
		menuPanel.setBounds(600,30,180,690);
		add(chatPanel);
		add(backGroundPanel);
		add(menuPanel);
		
//		==========================================================================	
//		게임 플레이 화면 셋팅
//		==========================================================================	

		// Play BackGround
		Panel playPanel = new Panel(new GridLayout(20, 10));
		playPanel.setBounds(30,30,300,600);
		playPanel.setBackground(new Color(12,6,6));
		backGroundPanel.add(playPanel);
		
		// Play Ground
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
					playGroundLabel[i][j] = new Label();
					playGroundLabel[i][j].setBackground(new Color(12,6,6));
					playPanel.add(playGroundLabel[i][j]);
			}
		}
		

//		==========================================================================	
//		게임 메뉴 화면 셋팅
//		==========================================================================	
		
		// Menu BackGround
		topMenuPanel = new Panel();
		topMenuPanel.setLayout(null);
		topMenuPanel.setBounds(0,0,180,330);
		menuPanel.add(topMenuPanel);
		
		bottomMenuPanel = new Panel();
		bottomMenuPanel.setLayout(null);
		bottomMenuPanel.setBounds(0,330,180,330);
		menuPanel.add(bottomMenuPanel);
		
//		------------------------------------------------------------------------------
//		게임 메뉴 화면 : 상단
//		------------------------------------------------------------------------------
		
		// Top Menu
		// Next blocks
		topMenuGridPanel = new Panel(new GridLayout(10,4));
		topMenuGridPanel.setBounds(30,30,120,270);
		topMenuGridPanel.setBackground(Color.white);
		topMenuPanel.add(topMenuGridPanel);
		topMenuLabel = new Label[10][4];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				topMenuLabel[i][j] = new Label();
				topMenuGridPanel.add(topMenuLabel[i][j], BorderLayout.CENTER);
			}
		}
		
//		------------------------------------------------------------------------------
//		게임 메뉴 화면 : 하단
//		------------------------------------------------------------------------------
		// Bottom Menu
		Panel timeScorePanel = new Panel(new GridLayout(4,0));
		Panel buttonPanel = new Panel(new GridLayout(4,0));
		Panel userInfoPanel = new Panel(new GridLayout(3,0));
		timeScorePanel.setBounds(30,0,120,120);
		buttonPanel.setBounds(45,120,90,120);
		userInfoPanel.setBounds(30,240,120,90);
		bottomMenuPanel.add(timeScorePanel);
		bottomMenuPanel.add(buttonPanel);
		bottomMenuPanel.add(userInfoPanel);

		Font fontSizeBig = new Font("",Font.PLAIN,20);
		
		// Time & Score view -------------------------------------------
		Label timeTitleLabel = new Label("Time");
		timeTitleLabel.setForeground(Color.white);
		timeLabel = new Label("00:00");
		timeLabel.setAlignment(Label.RIGHT);
		timeLabel.setBackground(Color.white);
		Label scoreTitleLabel = new Label("Score");
		scoreTitleLabel.setForeground(Color.white);
		scoreLabel = new Label("0000");
		scoreLabel.setAlignment(Label.RIGHT);
		scoreLabel.setBackground(Color.white);
		// font
		timeTitleLabel.setFont(fontSizeBig);
		timeLabel.setFont(fontSizeBig);
		scoreTitleLabel.setFont(fontSizeBig);
		scoreLabel.setFont(fontSizeBig);
		// 배치
		timeScorePanel.add(timeTitleLabel);
		timeScorePanel.add(timeLabel);
		timeScorePanel.add(scoreTitleLabel);
		timeScorePanel.add(scoreLabel);
		
		// Button -------------------------------------------
		Label buttonPaddingTop = new Label();
		startButton = new Button("START");
		endButton = new Button("END");
		Label buttonPaddingBottom = new Label();
		// font
		startButton.setFont(fontSizeBig);
		endButton.setFont(fontSizeBig);
		// 배치
		buttonPanel.add(buttonPaddingTop);
		buttonPanel.add(startButton);
		buttonPanel.add(endButton);
		buttonPanel.add(buttonPaddingBottom);
		
		// action
//		startButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				/*
//				  카운트 다운 및 기본 정보 초기화
//
//				  기본 정보 초기화
//				  1. isGameOn = false
//				  2. isWin = false
//				  3. playGroundArr = 기존 블럭 지우기
//				  4. speedLevel = 0
//				  5. 카운트 다운 시작
//
//				  연결 상태 확인을 위해 sever로 부터 카운트 받아 옴
//				  게임 화면에 카운트 다운 5 4 3 2 1
//
//				  TODO : 서버에서 카운트 받아서 처리 해야함 / 1/4일 현재 클라이언트에서 처리 됨
//				  @param playGroundArr
//
//				 */
//				isGameOn = false;
//				isWin = false;
//				speedLevel = 0;
//				isReady = false;
//				isBeforeBlockStart = true;
//				new CountDown(playGroundLabel);
//
//				// 게임 스타트 및 타임 셋팅
//				Thread gameStartThread = new Thread(new Runnable() {
//					@Override
//					public void run() {
//						System.out.println("gameStartThread");
//						// socket 연결
//						byte[] arr = {(byte)192,(byte)168,(byte)240,127};
//						InetAddress addr = null;
//						int port = 8080;
//						InputStream is = null;
//						ObjectInputStream ois = null;
//						OutputStream os= null;
//						ObjectOutputStream oos = null;
//						Socket socket = null;
//						Object object;
//
//						try {
//							addr = InetAddress.getByAddress(arr);
//							socket = new Socket(addr, port);
//							os = socket.getOutputStream();
//							is = socket.getInputStream();
//							oos = new ObjectOutputStream(os);
//							ois = new ObjectInputStream(is);
//
//							do {
//								if (!isReady) {
//									oos.writeObject(true);
//									isReady = true;
//									oos.flush();
//								}
//								object = ois.readObject();
//								if (isReady && object instanceof String) {
//									System.out.println("왔나?3");
//									String temp = object.toString();
//									System.out.println(temp);
//									timeLabel.setText(temp);
//									if (!isGameOn) isGameOn = true;
//								}
////						if(isGameOn && isBeforeBlockStart){
////							isBeforeBlockStart = false;
////							requestFocusInWindow();
////							Thread palyGameThread = new Thread(new Runnable() {
////								@Override
////								public void run() {
////									playGame();
////								}
////							});
////							palyGameThread.start();
////						}
//							} while (!(object instanceof GameOver));
//
//						} catch (IOException | ClassNotFoundException ex) {
//							ex.printStackTrace();
//						} finally {
//							try {
//								if(oos!=null)oos.close();
//								if(ois!=null)ois.close();
//								if(os!=null)os.close();
//								if(is!=null)is.close();
//								if(socket!=null)socket.close();
//							} catch (IOException ex) {
//								ex.printStackTrace();
//							}
//						}
//
//					}
//				});
//				gameStartThread.start();
//
//				// 게임 시작 -------------------------------------------
//				requestFocusInWindow();
//				playGame();
//
//			}
//		});

//		endButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// 게임 종료
//				isGameOn = false;
//			}
//		});
		
		// UserInfomation -------------------------------------------
		Label userInfoPaddingTop = new Label();
		Label userInfoLabel = new Label("user name1");
		userInfoLabel.setAlignment(Label.CENTER);
		userInfoLabel.setFont(fontSizeBig);
		userInfoLabel.setBackground(Color.white);
		Label userInfoPaddingBottom = new Label();
		userInfoPanel.add(userInfoPaddingTop);
		userInfoPanel.add(userInfoLabel);
		userInfoPanel.add(userInfoPaddingBottom);
		
//		==========================================================================	
		
//		addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				/*
//				  블럭 모션
//
//				  1. 블럭이 ""초 마다 한칸씩 아래로 떨어짐
//				  2. 방향키 ↑ 클릭(38) : 시계 방향으로 블럭 회전
//				  3. 방향키 → 클릭(39) : 오른쪽으로 한 칸 이동
//				  4. 방향키 ← 클릭(37) : 왼쪽으로 한 칸 이동
//				  5. 방향키 ↓ 클릭(40) : 아래로 한 칸 이동
//				  6. space bar 클릭 (32) : 가장 아래로 이동
//				 */
//				if(isGameOn) {
//					switch (e.getKeyCode()) {
//					case 32: block.pressSpaceKey(); break;
//					case 37: block.pressLeftKey(); break;
//					case 38: block.pressUpKey(); break;
//					case 39: block.pressRightKey(); break;
//					case 40: block.pressDownKey(); break;
//					default: break;
//					}
//				}
//			}
//		});

	}

//	========================================================================== 
//	생성자 종료	
//	========================================================================== 
	
	
//	------------------------------------------------------------------------------
//	game play process
//	------------------------------------------------------------------------------
	public void playGame() {
		
		/*
		  게임 시작

		  게임 화면에 카운트 다운 5 4 3 2 1
		  카운트 다운 후 블럭 스타트
		 */
		
		/*
		  블럭 가져오기

		  1. 서버로 부터 블럭 배열 받아오기
		  2. 받아온 블럭을 오래된 순서대로(queue 활용) 하나씩 꺼내서 next block 창에 배치
		  3. 게임 화면에서 블럭 동작 마감하면 next block 창에서 하나 꺼냄
		  4. 서버로 부터 몇개씩 가져오지? 한번에? 아니면 가져온 블럭이 일정 갯수 이하로 떨어지면 요청?
		 */
		Block[] blocks = {new Block1(),new Block2(),new Block3(),new Block4(),new Block5(),new Block6(),new Block7()};
		List<Block> blockList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			blockList.add(blocks[i%7]);
		}
		speedLevel = 5;
		Thread moveBlock = new Thread(new Runnable() {
			@Override
			public void run() {
//				while(isGameOn) {
//					block.setBlock(speedLevel, playGroundArr);
//				}
				for (int i = 0; i < blockList.size(); i++) {

					// next block 화면 초기화
					for (int k = 0; k < 10; k++) {
						for (int j = 0; j < 4; j++) {
							topMenuLabel[k][j].setBackground(Color.white);
						}
					}

					// next block 화면 설정
					blockList.get(i+1).setNextBlock(1,topMenuLabel);
					blockList.get(i+2).setNextBlock(4,topMenuLabel);
					blockList.get(i+3).setNextBlock(7,topMenuLabel);

					// 화면에 블럭 출력
					block = blockList.get(i);
					isGameOn = block.setBlock(speedLevel, playGroundLabel);
					// score process
					checkLineComplete();
					if(!isGameOn)break;
				}
			}

		});
		moveBlock.start();

	}

	private void checkLineComplete() {
		// scan
		boolean isComplete = true;
		List<Integer> completeLine = new ArrayList<>();
		for (int i = 19; i >=2; i--){
			for (int j = 0; j<10; j++){
				Color checkColor = playGroundLabel[i][j].getBackground();
				if(Objects.equals(checkColor, new Color(12, 6, 6))) {
					isComplete = false;
					break;
				}
			}
			if(isComplete){
				scoreLabel.setText(Integer.parseInt(scoreLabel.getText())+100+"");
				completeLine.add(i);
			}
			isComplete = true;
		}

		if(completeLine.size()==4){
			for (int i = completeLine.get(0); i >= 2; i--){
				for (int j = 0; j<10; j++){
					if(i<=5){
						playGroundLabel[i][j].setBackground(new Color(12, 6, 6));
					} else {
						playGroundLabel[i][j].setBackground(playGroundLabel[i-4][j].getBackground());
					}
				}
			}
		} else if (completeLine.size()==1){
			for (int i = completeLine.get(0); i >= 2; i--){
				for (int j = 0; j<10; j++){
					playGroundLabel[i][j].setBackground(playGroundLabel[i - 1][j].getBackground());
				}
			}
		} else if (completeLine.size() == 2) {
			for (int i = completeLine.get(0); i > completeLine.get(0)-2; i--){
				for (int j = 0; j<10; j++){
					if(!completeLine.contains(i-1)){
						playGroundLabel[i][j].setBackground(playGroundLabel[i-1][j].getBackground());
					}else if(!completeLine.contains(i-2)){
						playGroundLabel[i][j].setBackground(playGroundLabel[i-2][j].getBackground());
					}
				}
			}
			for (int i = completeLine.get(0)-2; i >= 2; i--){
				for (int j = 0; j<10; j++){
					playGroundLabel[i][j].setBackground(playGroundLabel[i-2][j].getBackground());
				}
			}
		} else if (completeLine.size() == 3){
			for (int i = completeLine.get(0); i > completeLine.get(0)-3; i--) {
				for (int j = 0; j < 10; j++) {
					if (i <= 4) {
						playGroundLabel[i][j].setBackground(new Color(12, 6, 6));
					} else if (!completeLine.contains(i + 1)) {
						playGroundLabel[i][j].setBackground(playGroundLabel[i - 1][j].getBackground());
					} else if (!completeLine.contains(i - 2)) {
						playGroundLabel[i][j].setBackground(playGroundLabel[i - 2][j].getBackground());
					} else if (!completeLine.contains(i - 3)) {
						playGroundLabel[i][j].setBackground(playGroundLabel[i - 3][j].getBackground());
					}
				}
			}
			for (int i = completeLine.get(0)-3; i >= 2; i--){
				for (int j = 0; j<10; j++){
					if(i<=4){
						playGroundLabel[i][j].setBackground(new Color(12, 6, 6));
					} else {
						playGroundLabel[i][j].setBackground(playGroundLabel[i-3][j].getBackground());
					}
				}
			}

		}



	}


}
