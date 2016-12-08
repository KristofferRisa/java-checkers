package Views;

import Models.Game;
import Models.Server;

public class WindowManager extends Thread {
	
	public WindowManager(){
		windowContainer = new WindowContainer();
		debug = new DebugView();
	}

	public void showGameView(){
		windowContainer.showBoard();
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

	public Game setupGame() {		
		windowContainer.showUserInput();		
		
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

	public Boolean getServerStatus() {
		return windowContainer.userInputView.isServer;
	}
	
	public void setServer(Server s){
		windowContainer.server = s;
	}

	public Game game;
	public DebugView debug;
	private WindowContainer windowContainer;
}
