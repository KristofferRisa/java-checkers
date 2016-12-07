package Models;

import Views.WindowManager;

public class Checkers extends Thread {
	
	private Player p2;
	private Game game;	
	private Player p1;
	private WindowManager guiManager;
	private GameConfig config;
	private Server server;
	private Klient klient;
	private GameData gameData;

	public Checkers(){
		//oppsett spillere og GUI(windowmanager)
		setupPlayersAndGame();
		guiManager.debug.log("Starter");
		
		guiManager.startUserview(); 
				
		guiManager.debug.log(p1.name);
		
		guiManager.startGameView();
		
		guiManager.showGameView();
		while(guiManager.gameIsActive() == true && ((server != null && server.isConnected == true) || (klient != null && klient.isConnected))){
			try {
				guiManager.debug.log("Starter Game view!");
				guiManager.debug.log("Server: " + config.isServer.toString());
				
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

	private void setupPlayersAndGame() {
		p1 = new Player();
		
		p2 = new Player("Robot", false);
		
		config = new GameConfig();
		game = new Game(p1, p2);				
		guiManager = new WindowManager(game, p1, config);
		gameData = new GameData(p1);
	}
		
}
