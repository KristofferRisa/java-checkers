package Models;

import Views.WindowManager;

public class Checkers extends Thread {
	
	private Game game;	
	private WindowManager guiManager;
	private Server server;
	private Klient klient;

	public Checkers(){
		//oppsett spillere og GUI(windowmanager)
		guiManager = new WindowManager();
		
		guiManager.debug.log("Starter");
		game = guiManager.setupGame();
						
		guiManager.debug.log("Player = " + game.player1.name);
		
		guiManager.startGameView();
		
		guiManager.showGameView();
		while(guiManager.gameIsActive() == true && ((server != null && server.isConnected == true) || (klient != null && klient.isConnected))){
			try {
				guiManager.debug.log("Starter Game view!");
				
				sleep(2000);
				guiManager.game.isActive = true;
			} catch (InterruptedException e) {
				guiManager.debug.log(e.getMessage());
				guiManager.debug.log(e.getStackTrace().toString());
			}
		}
		
		guiManager.debug.log("ferdig, avslutter aplikasjon");
		System.exit(0);
		//game.start();
		
	}
		
}
