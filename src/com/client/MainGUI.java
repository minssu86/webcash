package com.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.client.block.Block;

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

	Label timeLabel;
	Label scoreLabel;
	Label[][] playGroundLabel = new Label[20][10];
	Block block;

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
		startButton = new Button("Ready");
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

		// UserInfomation -------------------------------------------
		Label userInfoPaddingTop = new Label();
		Label userInfoLabel = new Label("user name");
		userInfoLabel.setAlignment(Label.CENTER);
		userInfoLabel.setFont(fontSizeBig);
		userInfoLabel.setBackground(Color.white);
		Label userInfoPaddingBottom = new Label();
		userInfoPanel.add(userInfoPaddingTop);
		userInfoPanel.add(userInfoLabel);
		userInfoPanel.add(userInfoPaddingBottom);

		setBounds(800, 100, 780, 690);
		setResizable(false);
		setVisible(true);

		Frame frame = new Frame();
		Dialog userNameDialog = new Dialog(frame);
		Label userNameLabel = new Label("Input name!");
		Button userNameButton = new Button("Check");
		TextField userNameTextField = new TextField();
		Panel userNamePanel = new Panel();
		userNamePanel.setLayout(new GridLayout(3,0));
		userNamePanel.add(userNameLabel);
		userNamePanel.add(userNameTextField);
		userNamePanel.add(userNameButton);
		userNameDialog.add(userNamePanel);
		userNameDialog.setBounds(890,250,180,120);
		userNameDialog.setResizable(false);
		userNameDialog.setVisible(true);
		userNameDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				userNameDialog.dispose();
			}
		});

		userNameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userInfoLabel.setText(userNameTextField.getText());
				userNameDialog.dispose();
			}
		});


	}

}