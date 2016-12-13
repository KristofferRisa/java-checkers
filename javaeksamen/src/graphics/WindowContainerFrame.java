package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

import datamodels.UserInput;
import game.Checker;
import game.RuleEngine;
import game.board.CheckerType;
import game.board.Piece;
import graphics.usercontrol.BoardPanel;
import graphics.usercontrol.GameControlPanel;
import graphics.usercontrol.StartPanel;
import network.Client;
import network.Server;

public class WindowContainerFrame extends JFrame {

	public GameControlPanel gameControls;
	
	public WindowContainerFrame(){
		configureFrame();
	}
	
	public UserInput showUserInput(){
		startPanel = new StartPanel(this);
		add(startPanel);
		
		while(startPanel.isVisible()){
			
			try {
				System.out.println("Waiting on user input");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return startPanel.getUserInputData();
	}

	public void showBoard(Client klient){
		boardWindow = new BoardPanel();
		remove(startPanel);
		
		boardWindow.add(new Piece(CheckerType.BLACK_KING),1,1);
		
		setLayout(new BorderLayout());
		
		
		boardWindow.setPreferredSize(new Dimension(800,600));
		add(boardWindow,BorderLayout.CENTER);
	
		gameControls = new GameControlPanel(klient);
		
		add(gameControls,BorderLayout.SOUTH);
		pack();
	}
	
	private void configureFrame() {
		setTitle("Checkers 1.0!! (java eksamen)");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(height/2,width/4);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public StartPanel startPanel;
	private BoardPanel boardWindow;
	public Server server;
	private static final long serialVersionUID = -3425445318104341180L;


	
}
