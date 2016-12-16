package game;

import java.net.UnknownHostException;
import datamodels.GameDataDTO;
import datamodels.UserInput;
import graphics.DebugWindow;
import graphics.MainWindow;
import network.Client;
import network.Server;

public class Checker {

	public Checker(){
		debug = new DebugWindow();
		guiManager = new MainWindow();
		
		openUserInputPanel();
				
		startNetwork();

		debug.log("_chekers: Viser brett");
		
		
		guiManager.showBoard(server,klient);
	
		
		debug.log("ferdig, avslutter aplikasjon");	
	}

	
	
	private void startNetwork() {
		
		if(userInput.isServer){
			//Start server
			server = new Server(userInput,debug);
			server.connect();	
		} else {
			debug.log("Starter ny klient");
			klient = new Client(userInput, debug);
			klient.connect();
		}
	}

	private void openUserInputPanel() {
		
		//Henter input data fra UI og lagrer det i UserInput objekt
		userInput = guiManager.showUserInput();

		//Logging
		debug.log("Player = " + userInput.name);
		String status = (userInput.isServer == true) ? "on" : "off";
		debug.log("Server status: " + status);
		
	}
	
	private DebugWindow debug;

	private MainWindow guiManager;
	
	private Server server;

	private Client klient;

	private UserInput userInput;
	
}
