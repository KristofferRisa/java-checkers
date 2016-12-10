package Views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import Board.BoardWindow;
import Game.Game;
import Network.Server;

public class WindowContainer extends JFrame {

	public GameView gameview;
	private Game game;
	public WindowContainer(){
		configureFrame();
	}
	
	public Game showUserInput(){
		game = new Game();
		
		startView = new StartPanel(game,this);
		add(startView);
		game = startView.getGame();

		return game;
	}
	
	public void showBoard(){
		boardWindow = new BoardWindow();
		remove(startView);
		
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
		
		if(server != null){
			gameview = new GameView(server);
		}
		else {
			gameview = new GameView();
		}
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
	
	public StartPanel startView;
	private BoardWindow boardWindow;
	public Server server;
	private static final long serialVersionUID = -3425445318104341180L;
	
	
}
