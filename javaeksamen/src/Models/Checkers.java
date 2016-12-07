package Models;

import Views.WindowManager;

public class Checkers extends Thread {
	
	private Player p2;
	private Game game;	
	private Player p1;
	private WindowManager gui;
	private GameConfig config;
	private Server server;
	private Klient klient;

	public Checkers(){
		//oppsett spillere og GUI(windowmanager)
		setupPlayersAndGame();
		
		gui.showUserInput(); 
		
		while(gui.userInputIsActive() == true){
			try{
				//gui.debug.log("Venter på user input");
				sleep(2000);
			}
			catch(InterruptedException e){
				gui.debug.log(e.getMessage());
			}			
		}
		gui.debug.log(p1.name);
		
		if(config.isServer == true){
			//Start server
			server = new Server(gui.debug);
			server.start();
			while(server.isConnected == false){
				try {
					gui.debug.log("venter på spiller");
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
			gui.debug.log("Starter ny klient");
			klient = new Klient(gui.debug);
			
			gui.debug.log("Forsøker å kople til server");
			while(klient.isConnected == false){
				try {
					gui.debug.log("Venter på Server");
					sleep(1000);
					klient.start();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		gui.showGameView();
		while(gui.gameIsActive() == true && ((server != null && server.isConnected == true) || (klient != null && klient.isConnected))){
			try {
				gui.debug.log("Starter Game view!");
				gui.debug.log("Server: " + config.isServer.toString());
				
				sleep(2000);
				gui.game.isActive = true;
			} catch (InterruptedException e) {
				gui.debug.log(e.getMessage());
				gui.debug.log(e.getStackTrace().toString());
			}
		}
		
		gui.debug.log("ferdig, avslutter aplikasjon");
		System.exit(0);
		//game.start();
		
	}

	private void setupPlayersAndGame() {
		p1 = new Player();
		p2 = new Player("Robot", false);
		config = new GameConfig();
		game = new Game(p1, p2);				
		gui = new WindowManager(game, p1, config);
	}
		
}
