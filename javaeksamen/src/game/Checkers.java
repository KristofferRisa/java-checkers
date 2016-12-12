package game;

import graphics.DebugWindowFrame;
import graphics.WindowContainerFrame;
import network.DataTransferObject;
import network.Klient;
import network.Server;

public class Checkers extends Thread {

	private WindowContainerFrame gui;
	private static DebugWindowFrame Debug;
	
	public Checkers(){
		
		Debug = new DebugWindowFrame();
		gui = new WindowContainerFrame();
		isServer = false;
		
		Debug.log("Starter!");
		
		game = gui.showUserInput();
		
		if(game.player2 == null)
		{
			isServer = true;
		}
		Debug.log("Player = " + game.player1.name);
		
		String status = (isServer == true) ? "on" : "off";
		
		Debug.log("Server status: " + status);
		
		if(isServer){
			//Start server
			server = new Server(game);
			server.start();		
		} 
		
		//Starter klient
		Debug.log("Starter ny klient");
		klient = new Klient(Debug);
		klient.start();

		while(klient.isConnected == false){
			try {
				klient.start();
				Debug.log("Forsøker å kople til server");
				Debug.log("Venter på Server");
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Debug.log(e.getMessage());
				e.printStackTrace();
			}
		}
		
		while(klient.isConnected == true){
			Debug.log("_chekers: Viser brett");
			gui.showBoard(klient);
			
			while(gui.gameview.isVisible()){
					
			
//				GameData data = new GameData();
//				data.msg = "klient er klar for spill";
//				klient.send(data);
				
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
		Debug.log("ferdig, avslutter aplikasjon");
		System.exit(0);
		//game.start();
		
	}
	
	private Game game;
	private Server server;
	private Klient klient;
	public Boolean isServer;
	
	public static void main(String[] args){
		new Checkers();
	}
}


