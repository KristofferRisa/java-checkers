package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import datamodels.UserInput;
import network.Client;
import network.Server;

public class MainWindow extends JFrame {

	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();
	int screenHeight = d.height;
	int screenWidth = d.width;

	private BoardPanel board;
	private UserInput userinput;
	
	public MainWindow(){
		configureFrame();
		addMenu();
	}
	

	private void addMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem closeMenuItem = new JMenuItem("Close");
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem showDebug = new JMenuItem("Show Debug");
		JMenuItem portItem= new JMenuItem ("Change port");
		JMenuItem disableMouse= new JMenuItem ("Mouse off");

		
		setJMenuBar(menubar);
		menubar.add(menu);
		menu.add(newGame);
		menu.add(showDebug);
		menu.add(portItem);
		menu.add(disableMouse);
		menu.add(closeMenuItem);
		
		menu.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
		newGame.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
		showDebug.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
		closeMenuItem.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
		
		closeMenuItem.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
		showDebug.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				//ADD CLOSE AND OPEN DEBUG WINDOW HERE
            }
        });
		
		portItem.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
		portItem.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                startPanel.showPortfield();
            }
        });
		
		disableMouse.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
		disableMouse.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				//Denne er ikke bra nok.. Fryser hele frame
                setEnabled(false);
            }
        });
		
	
	}

	public UserInput showUserInput() {
		startPanel = new StartPanel(this);
		add(startPanel);
		setVisible(true);
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
		remove(startPanel);
		add(waitLabel);
		setVisible(true);
		repaint();
		return startPanel.getUserInputData();
		
	}


	public void showBoard(Client klient){
//		boardpanel = new Board(klient);
		
		setLayout(new BorderLayout());
				
		//TODO: Oppdater med riktig brukerinfo
		
		UserInfoPanel user1 = new UserInfoPanel(userinput.name, userinput.ipAdress);
		
		add(user1, BorderLayout.NORTH);
		
//		postBlackBricks();
//		
//		postHviteBricks();
		
		
		
		//boardpanel.setPreferredSize(new Dimension(800,600));
		
		
		
		//add(boardpanel,BorderLayout.CENTER);
	
		board = new BoardPanel(klient);
		add(board);
		
		
		
		//add(new CheckersPanel());
		//TODO: Oppdater med riktig brukerinfo
		UserInfoPanel user2 = new UserInfoPanel("TEST TEST", "Localhost");
		
		
		add(user2, BorderLayout.SOUTH);
		
		setResizable(false);
		pack();
		repaint();

		
	}

	private void configureFrame() {
		setTitle("Checkers 1.0 - OBJ2000");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(height/1,width/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public StartPanel startPanel;
	public Server server;
	
	private static final long serialVersionUID = -3425445318104341180L;
	
}