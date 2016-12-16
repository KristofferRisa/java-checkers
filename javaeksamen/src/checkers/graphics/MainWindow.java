package checkers.graphics;

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
import javax.swing.JPanel;
import checkers.datamodels.GameDataDTO;
import checkers.datamodels.UserInput;
import checkers.network.Client;
import checkers.network.Server;

public class MainWindow extends JFrame {

	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension d = tk.getScreenSize();
	private int screenWidth = d.width;
	private BoardPanel board;
	private DebugWindow debug;
	public StartPanel startPanel;
	public Server server;
	private static final long serialVersionUID = -3425445318104341180L;
	
	public MainWindow(DebugWindow debug){
		configureFrame();
		addMenu();
		this.debug = debug;
	}

	private void addMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem closeMenuItem = new JMenuItem("Close");
		JMenuItem showDebug = new JMenuItem("Show/hide Debug");
		JMenuItem portItem= new JMenuItem ("Change port");
		JMenuItem disableMouse= new JMenuItem ("Mouse off");

		
		setJMenuBar(menubar);
		menubar.add(menu);
		menu.add(showDebug);
		menu.add(portItem);
		menu.add(disableMouse);
		menu.add(closeMenuItem);
		
		menu.setFont(new Font("Arial", Font.PLAIN, (int) screenWidth / 150));
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
				boolean active = (debug.isVisible()) ? false : true;
				debug.setVisible(active);
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
		startPanel = new StartPanel(this,debug);
		add(startPanel);
		setVisible(true);
		
		JLabel waitLabel = new JLabel("Waiting for player 2");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(waitLabel , BorderLayout.CENTER);
		
		repaint();
		
		while(startPanel.isVisible()){
			
			try {
				System.out.println("Waiting on user input");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				debug.log("Noe feil ved henting av user input. " + e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		remove(startPanel);		
		
	
		add(panel);
		
		repaint();
		
		return startPanel.getUserInputData();
		
	}


	public void showBoard(Server server, Client client, UserInput input){

		setLayout(new BorderLayout());
		
		//Utvkelse bruker info
		GameDataDTO data = new GameDataDTO();
		if(server != null){
			data.player1 = input.name;
			server.client.send(data);
			data = server.client.recive();
		} else {
			data = client.recive();
			data.player2 = input.name;
			client.send(data);
		}

		UserInfoPanel user1 = new UserInfoPanel("Spiller 1: " + data.player1);
		
		add(user1, BorderLayout.NORTH);
		
		board = new BoardPanel(server, client,debug);
		add(board);
		
		UserInfoPanel user2 = new UserInfoPanel("Spiller 2: "+ data.player2);
		
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
	
}