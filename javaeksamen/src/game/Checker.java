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
		
		starterServer();
		
		starterClient();

		showBoard();
		
		debug.log("ferdig, avslutter aplikasjon");
		
		System.exit(0);
	}

	
	
	private void starterServer() {
		
		if(input.isServer){
			//Start server
			server = new Server(input);
			server.start();	
		}
	}

	private void starterClient() {
		debug.log("Starter ny klient");
		client = new Client(input);
	}

	private void openUserInputPanel() {
		
		input = guiManager.showUserInput();

		debug.log("Player = " + input.name);
		
		String status = (input.isServer == true) ? "on" : "off";
		
		debug.log("Server status: " + status);
		
	}
	
	private void showBoard() {

			guiManager.showBoard(client, input);
	}
	
	public String gameType;
	
	private DebugWindow debug;

	private MainWindow guiManager;
	
	private Server server;

	private Client client;

	private UserInput input;
	
}
