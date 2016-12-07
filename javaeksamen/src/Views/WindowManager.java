package Views;

import javax.swing.JFrame;

import Models.Game;
import Models.GameConfig;
import Models.GameData;
import Models.Klient;
import Models.Player;
import Models.Server;

public class WindowManager extends Thread {
	
	public Player player;
	public Game game;
	public DebugView debug;
	private GameView gameView;
	private WindowContainer windowContainer;
	private GameConfig config;
	private Server server;
	private Klient klient;
	private GameData gameData;
	
	public WindowManager(Game g, Player p, GameConfig c){
		game = g;
		player = p;
		config = c;
		gameView = new GameView();
		windowContainer = new WindowContainer(c);
		
		
		debug = new DebugView();
	}
	
	public void startUserview(){
		
		windowContainer.userInputView.p = this.player;
		windowContainer.setVisible(true);
		
		while(userInputIsActive() == true){
			try{
			debug.log("Venter på user input");
				sleep(2000);
			}
			catch(InterruptedException e){
				debug.log(e.getMessage());
			}			
		}
		
	}
	
	public void showGameView(){
		gameView.setVisible(true);
	}
	
	public void closeUserInput(){
		windowContainer.userInputView.setVisible(false);
		notifyAll();
	}
	
	public boolean userInputIsActive(){
		return (windowContainer.userInputView.isVisible() == true) ? true : false;
	}

	public boolean gameIsActive() {
		return (game.isActive == true) ? true : false;
	}



	public void startGameView() {
		if(config.isServer == true){
			//Start server
			server = new Server(gameData, debug);
			server.start();
			while(server.isConnected == false){
				try {
					debug.log("venter på spiller");
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		else 
		{
			//Klient
			debug.log("Starter ny klient");
			klient = new Klient(debug);
			klient.start();
			debug.log("Forsøker å kople til server");
			while(klient.isConnected == false){
				try {
					debug.log("Venter på Server");
					sleep(1000);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
	}

	
}
