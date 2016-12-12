package game;

import java.io.Serializable;

import game.board.Move;
import graphics.DebugWindowFrame;
import graphics.WindowContainerFrame;
import graphics.usercontrol.UserInput;
import network.Client;
import network.Server;

public class Checker extends Thread {

	private DebugWindowFrame debug;

	private WindowContainerFrame guiManager;
	
	private Server server;

	private Client klient;

	public Checker(){
		debug = new DebugWindowFrame();
		
		guiManager = new WindowContainerFrame();
		
		RuleEngine ruleEngine = new RuleEngine(debug);
		
		UserInput input = guiManager.showUserInput();


		debug.log("Player = " + input.name);
		
		String status = (input.isServer == true) ? "on" : "off";
		
		debug.log("Server status: " + status);
		
		if(input.isServer){
			Game game = new Game();
			//Start server
			server = new Server(game, ruleEngine);
			server.start();		
		} 
		
		//Starter klient
		debug.log("Starter ny klient");
		klient = new Client(debug);
		klient.start();

		while(klient.isConnected == false){
			try {
				klient.start();
				debug.log("Forsøker å kople til server");
				debug.log("Venter på Server");
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				debug.log(e.getMessage());
				e.printStackTrace();
			}
		}
		
		while(klient.isConnected == true){
			debug.log("_chekers: Viser brett");
			guiManager.showBoard(klient);
			
			while(guiManager.gameview.isVisible()){
					
			
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
		debug.log("ferdig, avslutter aplikasjon");
		System.exit(0);
		//game.start();
		
	}
	
	public String gameType;
	
	
		
	private static final long serialVersionUID = 3484073086581610846L;

	public void update(Move move1) {
		// TODO Auto-generated method stub
		
	}

}
