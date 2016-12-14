package game;

import java.net.UnknownHostException;

import datamodels.GameDataTransferObject;
import datamodels.UserInput;
import game.board.Move;
import graphics.DebugWindowFrame;
import graphics.WindowContainerFrame;
import network.Client;
import network.Server;

public class Checker {

	public Checker(){
		openGuiManagerAndStartUserInput();
		
		starterServer();
			
		starterClient();
		
		showBoard();
	}

	private void showBoard() {
		
		
		if(klient.isConnected){
			debug.log("_chekers: Viser brett");
			guiManager.showBoard(klient);
			
			while(guiManager.gameControls.isVisible()){
				
				try {
						Thread.sleep(2000);
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
			server = new Server(debug);
			server.start();	
		}
	}

	private void starterClient() {
		debug.log("Starter ny klient");
		String ip = "127.0.0.1";
		int port = 1337;
		klient = new Client(ip, port, input, debug);
		klient.connect();
		
	}

	private void openGuiManagerAndStartUserInput() {
		debug = new DebugWindowFrame();
		
		guiManager = new WindowContainerFrame();
		
		ruleEngine = new GameController(debug);
		
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

	private GameController ruleEngine;


}
