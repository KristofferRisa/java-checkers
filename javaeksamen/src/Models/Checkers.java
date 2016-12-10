package Models;

import Network.Klient;
import Network.Server;
import Views.DebugWindow;
import Views.WindowContainer;

public class Checkers extends Thread {

	private WindowContainer gui;
	private static DebugWindow Debug;
	public Checkers(){
		Debug = new DebugWindow();
		gui = new WindowContainer();
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
			server = new Server(Debug);
			server.start();
			gui.showBoard();
			while(server.isConnected == false){
				try {
					Debug.log("venter på spiller");
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
			while(server.isConnected && game.isActive){
				try{
					Debug.log("Spillet kan starte");
					sleep(1000);
					
				} catch (InterruptedException e){
					e.printStackTrace();
					break;
				}
			}
		} 
		else 
		{
			//Starter klient
			Debug.log("Starter ny klient");
			klient = new Klient(Debug);
			klient.start();

			Debug.log("Forsøker å kople til server");
			while(klient.isConnected == false){
				try {
					Debug.log("Venter på Server");
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while(server.isConnected && game.isActive){
				try{
					Debug.log("Spillet kan starte");
					sleep(1000);
					gui.showBoard();
				} catch (InterruptedException e){
					e.printStackTrace();
					break;
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
}
