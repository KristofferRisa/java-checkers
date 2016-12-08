package Models;

import Views.WindowManager;

public class Checkers extends Thread {

	public Checkers(){
		//oppsett spillere og GUI(windowmanager)
		windowManager = new WindowManager();
		
		windowManager.debug.log("Starter");
		//Viser GUI for å spill og spiller informasjon
		game = windowManager.setupGame();
		
		//Debug status
		isServer = windowManager.getServerStatus();
		windowManager.debug.log("Player = " + game.player1.name);
		String status = (isServer == true) ? "on" : "off";
		windowManager.debug.log("Server status: " + status);
		
		if(isServer){
			//Start server
			server = new Server(game, windowManager.debug);
			server.start();
			windowManager.setServer(server);
			while(server.isConnected == false){
				try {
					windowManager.debug.log("venter på spiller");
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
			while(server.isConnected && game.isActive){
				try{
					windowManager.debug.log("Spillet kan starte");
					sleep(1000);
					windowManager.showGameView();
				} catch (InterruptedException e){
					e.printStackTrace();
					break;
				}
			}
		} 
		else 
		{
			//Starter klient
			windowManager.debug.log("Starter ny klient");
			klient = new Klient(windowManager.debug);
			klient.start();

			windowManager.debug.log("Forsøker å kople til server");
			while(klient.isConnected == false){
				try {
					windowManager.debug.log("Venter på Server");
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while(server.isConnected && game.isActive){
				try{
					windowManager.debug.log("Spillet kan starte");
					sleep(1000);
					windowManager.showGameView();
				} catch (InterruptedException e){
					e.printStackTrace();
					break;
				}
			}
		}
		
		windowManager.debug.log("ferdig, avslutter aplikasjon");
		System.exit(0);
		//game.start();
		
	}
		
	private Game game;	
	private WindowManager windowManager;
	private Server server;
	private Klient klient;
	public Boolean isServer;
}
