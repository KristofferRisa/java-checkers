package Views;

import javax.swing.JFrame;

import Models.Game;
import Models.Klient;
import Models.Player;
import Models.Server;

public class WindowManager extends Thread {
	
	public Game game;
	private GameView gameView;
	public DebugView debug;
	private WindowContainer windowContainer;
	private Server server;
	private Klient klient;
	
	public WindowManager(){
		gameView = new GameView();
		windowContainer = new WindowContainer();
		
		debug = new DebugView();
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
		if(game.player1 != null){
			//Start server
			server = new Server(game, debug);
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


	public Game setupGame() {
		
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
		
		return windowContainer.userInputView.game;
	}

	
}
