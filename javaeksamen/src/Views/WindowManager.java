package Views;

import javax.swing.JFrame;

import Models.Game;
import Models.GameConfig;
import Models.Player;

public class WindowManager extends JFrame {
	
	public Player player;
	public Game game;
	public DebugView debug;
	private GameView gameView;
	private UserInputView userInputView;
	
	public WindowManager(Game game, Player player,GameConfig config){
		this.game = game;
		this.player = player;
		userInputView = new UserInputView(config);
		gameView = new GameView();
		
		configureFrame();
		
		add(userInputView);
		debug = new DebugView();
	}

	private void configureFrame() {
		setTitle("Checkers 1.0!! (java eksamen)");
		setVisible(true);
		setSize(400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void showUserInput(){
		userInputView.p = this.player;
		userInputView.setVisible(true);
	}
	
	public void showGameView(){
		gameView.setVisible(true);
	}
	
	public void closeUserInput(){
		userInputView.setVisible(false);
		notifyAll();
	}
	
	public boolean userInputIsActive(){
		return (userInputView.isVisible() == true) ? true : false;
	}

	public boolean gameIsActive() {
		return (game.isActive == true) ? true : false;
	}

	
}
