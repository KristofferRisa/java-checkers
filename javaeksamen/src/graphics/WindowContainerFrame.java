package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import datamodels.UserInput;
import game.board.CheckerType;
import game.board.Piece;
import graphics.usercontrol.BoardPanel;
import graphics.usercontrol.GameControlPanel;
import graphics.usercontrol.StartPanel;
import graphics.usercontrol.UserInfoPanel;
import network.Client;
import network.Server;

public class WindowContainerFrame extends JFrame {

	public GameControlPanel gameControls;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();
	int screenHeight = d.height;
	int screenWidth = d.width;
	
	public WindowContainerFrame(){
		configureFrame();
		addMenu();
	}
	
	private void addMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Meny");
		JMenuItem closeMenuItem = new JMenuItem("Close");
		JMenuItem newGame = new JMenuItem("New Game");
		setJMenuBar(menubar);
		menubar.add(menu);
		menu.add(newGame);
		menu.add(closeMenuItem);
		menu.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
		newGame.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
		closeMenuItem.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
		closeMenuItem.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
	}

	public UserInput showUserInput() throws UnknownHostException{
		startPanel = new StartPanel(this);
		add(startPanel);
		JLabel waitLabel = new JLabel("Waiting for player 2");
		repaint();
		while(startPanel.isVisible()){
			
			try {
				System.out.println("Waiting on user input");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		add(waitLabel);
		repaint();
		return startPanel.getUserInputData();
	}

	public void showBoard(Client klient){
		boardpanel = new BoardPanel(klient);
		
		setLayout(new BorderLayout());
		
		remove(startPanel);
		
		//TODO: Oppdater med riktig brukerinfo
		UserInfoPanel user1 = new UserInfoPanel("Test bruker", "IP");
		
		add(user1, BorderLayout.NORTH);
		
		leggUtSvarteBrikker();
		
		leggUtHviteBrikker();
				
		boardpanel.setPreferredSize(new Dimension(800,600));
		
		add(boardpanel,BorderLayout.CENTER);
	
		//TODO: Oppdater med riktig brukerinfo
		UserInfoPanel user2 = new UserInfoPanel("TEST TEST", "Localhost");
		
		add(user2, BorderLayout.SOUTH);
		
		gameControls = new GameControlPanel(klient);
		
		//add(gameControls,BorderLayout.SOUTH);
		
		pack();
		repaint();
	}

	private void leggUtSvarteBrikker() {
		
		for (int i = 1; i <= 8; i++) {

			if (i % 2 == 0) {
				boardpanel.add(new Piece(CheckerType.BLACK_REGULAR), 1, i);
			}
		}
		
		for (int i = 1; i <= 8; i++) {

			if (i % 2 != 0) {
				boardpanel.add(new Piece(CheckerType.BLACK_REGULAR), 2, i);
				
				
			}
			
		}
	}
	
	private void leggUtHviteBrikker() {
		
		for (int i = 1; i <= 8; i++) {

			if (i % 2 == 0) {
				boardpanel.add(new Piece(CheckerType.WHITE_REGULAR), 7, i);
			}
		}
		
		for (int i = 1; i <= 8; i++) {

			if (i % 2 != 0) {
				boardpanel.add(new Piece(CheckerType.WHITE_REGULAR), 8, i);
				
				
			}
			
		}
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
	private BoardPanel boardpanel;
	public Server server;
	private static final long serialVersionUID = -3425445318104341180L;

	
}
