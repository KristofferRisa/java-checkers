package game;

import datamodels.GameDataTransferObject;
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
		
	}

	private void startGame() {
		debug.log("_checker: start nytt Game");
		Game game = new Game(server,ruleEngine,debug);
		game.start();
	}

	private void starterServer() {
		if(input.isServer){
			//Start server
			server = new Server(debug);
			server.start();	
			startGame();
		}
	}

	private void starterClient() {
		debug.log("Starter ny klient");
		String ip = "127.0.0.1";
		int port = 1337;
		klient = new Client(ip, port, input, debug);
		klient.start();

		while(klient.isConnected == false){
			try {
				debug.log("Fors�ker � kople til server");
				debug.log("Venter p� Server");
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				debug.log(e.getMessage());
				e.printStackTrace();
			}
		}
		while(klient.data == null
				|| klient.data.player1.name == null
				|| klient.data.player2.name == null){
			//Sjekker om det er blitt utvekslet data mellom klienten og at det finnes b�de player 1 og player2
			try {
				
				debug.log("_checker: waiting for oponent.");
				sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		debug.log("_chekers: Viser brett");
		guiManager.showBoard(klient);
		
		while(guiManager.gameControls.isVisible()){
			
			try {
					sleep(2000);
			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		debug.log("ferdig, avslutter aplikasjon");
		System.exit(0);
		//game.start();
		
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
