package game;

import datamodels.UserInput;
import graphics.DebugWindowFrame;
import graphics.WindowContainerFrame;
import network.Client;
import network.Server;
import network.data.Move;

public class Checker extends Thread {

	public Checker(){
		openGuiManagerAndStartUserInput();
		
		starterServer(); 
		
		starterClient();
		
		while(klient.isConnected == true){
			debug.log("_chekers: Viser brett");
			guiManager.showBoard(klient);
			
			while(guiManager.gameview.isVisible()){
				
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

	private void starterServer() {
		if(input.isServer){
			//Start server
			server = new Server(ruleEngine);
			server.start();		
		}
	}

	private void starterClient() {
		debug.log("Starter ny klient");
		String ip = "127.0.0.1";
		int port = 1337;
		klient = new Client(ip, port, debug);
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
	}

	private void openGuiManagerAndStartUserInput() {
		debug = new DebugWindowFrame();
		
		guiManager = new WindowContainerFrame();
		
		ruleEngine = new RuleEngine(debug);
		
		input = guiManager.showUserInput();

		debug.log("Player = " + input.name);
		
		String status = (input.isServer == true) ? "on" : "off";
		
		debug.log("Server status: " + status);
	}
	
	public String gameType;
	
	private DebugWindowFrame debug;

	private WindowContainerFrame guiManager;
	
	private Server server;

	private Client klient;

	private UserInput input;

	private RuleEngine ruleEngine;


}
