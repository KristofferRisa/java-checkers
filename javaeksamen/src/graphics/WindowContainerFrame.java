package graphics;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

import game.Checker;
import game.RuleEngine;
import game.board.CheckerType;
import game.board.Piece;
import graphics.usercontrol.BoardPanel;
import graphics.usercontrol.GameControlPanel;
import graphics.usercontrol.StartPanel;
import graphics.usercontrol.UserInput;
import network.Client;
import network.Server;

public class WindowContainerFrame extends JFrame {

	public GameControlPanel gameview;
	
	public WindowContainerFrame(){
		configureFrame();
	}
	
	public UserInput showUserInput(){
		startPanel = new StartPanel(this);
		add(startPanel);
		pack();
		
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
		
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
				
		gc.weightx = 0;
		gc.weighty = 0;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		boardWindow.setPreferredSize(new Dimension(800,600));
		add(boardWindow,gc);
		
		
		gc.gridx = 0;
		gc.gridy = 1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		
		
		gameview = new GameControlPanel(klient);
		
		add(gameview,gc);
		pack();
	}
	
	private void configureFrame() {
		setTitle("Checkers 1.0!! (java eksamen)");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(height,width/2);
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
