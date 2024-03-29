package game;

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
		
		starterClient(input);

		showBoard(input);
		
		debug.log("ferdig, avslutter aplikasjon");
		
		System.exit(0);
	}

	
	
	private void starterServer() {
		
		if(input.isServer){
			//Start server
			server = new Server(debug,input);
			server.start();
		}
	}

	private void starterClient(UserInput input2) {
		debug.log("Starter ny klient");
		String ip = "127.0.0.1";
		int port = input2.portNumber;
		klient = new Client(ip, port, input, debug);
		klient.connect();
	}

	private void openUserInputPanel() {
		
		input = guiManager.showUserInput();

		debug.log("Player = " + input.name);
		
		String status = (input.isServer == true) ? "on" : "off";
		
		debug.log("Server status: " + status);
		
	}
	
	private void showBoard(UserInput input2) {
		if(klient.isConnected){
			debug.log("_chekers: Viser brett");
			guiManager.showBoard(klient, input2);
			
			
			//while(guiManager.gameControls.isVisible()){				
			while(true){
				try {
					//Venter p� 
						Thread.sleep(1000);
				} catch (InterruptedException e) {
					debug.log("_checker: error = " + e.getMessage());
					e.printStackTrace();
				}
			}		
			
		}
	}
	
	public String gameType;
	
	private DebugWindow debug;

	private MainWindow guiManager;
	
	private Server server;

	private Client klient;

	private UserInput input;
	
}
