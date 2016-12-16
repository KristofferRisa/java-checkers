package cherckers.game;

import checkers.datamodels.UserInput;
import checkers.graphics.DebugWindow;
import checkers.graphics.MainWindow;
import checkers.network.Client;
import checkers.network.Server;

public class Checker {
	
	private DebugWindow debug;
	private MainWindow guiManager;
	private Server server;
	private Client client;
	private UserInput userInput;

	public Checker(){
		debug = new DebugWindow();
		guiManager = new MainWindow(debug);		
		openUserInputPanel();
		startNetwork(userInput);
		debug.log("_chekers: Viser brett");
		guiManager.showBoard(server,client, userInput);
<<<<<<< HEAD
		String status = (userInput.isServer == true) ? "on" : "off";
		debug.log("Server status: " + status);
		String title = (userInput.isServer) ? "Player 1" : "Player 2";
		guiManager.setTitle("Checkers (" + title + ")");
		
=======
>>>>>>> origin/snart-jul
	}
	
	private void startNetwork(UserInput userInput2) {
		
		if(userInput.isServer){
			//Start server
			server = new Server(userInput,debug);
			server.connect();	
		} else {
			debug.log("Starter ny klient");
			client = new Client(userInput, debug);
			client.connect();
		}
	}

	private void openUserInputPanel() {
		
		//Henter input data fra UI og lagrer det i UserInput objekt
		userInput = guiManager.showUserInput();

		//Logging
		debug.log("Player = " + userInput.name);
	}

}
