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

		showBoard(input);
		
		debug.log("ferdig, avslutter aplikasjon");
		
		System.exit(0);
	}

	
	
	private void starterServer() {
		
		if(input.isServer){
			//Start server
			server = new Server();
			server.start();	
		}
	}

	private void starterClient() {
		debug.log("Starter ny klient");
		String ip = "127.0.0.1";
		int port = 1337;
		client = new Client(input);
	}

	private void openUserInputPanel() {
		
		input = guiManager.showUserInput();

		debug.log("Player = " + input.name);
		
		String status = (input.isServer == true) ? "on" : "off";
		
		debug.log("Server status: " + status);
		
	}
	
	private void showBoard(UserInput input2) {

			guiManager.showBoard(client, input2);
	}
	
	public String gameType;
	
	private DebugWindow debug;

	private MainWindow guiManager;
	
	private Server server;

	private Client client;

	private UserInput input;
	
}
