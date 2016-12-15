package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import datamodels.GameDataDTO;
import game.PostionValidator;
import graphics.DebugWindow;

public class Server extends Thread {

	private CheckersEngine checkersEngine;
	private GameDataDTO dataFromclien1;
	private GameDataDTO dataFromclien2;
	public Server(DebugWindow debug) {
		this.checkersEngine = new CheckersEngine();
		this.Debug = debug;
		this.isConnected = false;
		this.dataTransferObject = new GameDataDTO();
		this.client1 = new ClientManager(dataTransferObject,1, Debug);
		this.client2 = new ClientManager(dataTransferObject,2, Debug);
		this.dataFromclien1 = new GameDataDTO();
		this.dataFromclien2 = new GameDataDTO();
	}

	public void run() {
		// Starter Server
		ServerSocket server = null;

		try {
			Debug.log("_server: Starter server");
			server = new ServerSocket(1337);
		} catch (IOException ioe) {
			Debug.log("_server: Kunne ikke lage Server socket");
		}

		// Venter på klienter i server.accept()
		try {
			Socket socket;
			Debug.log("_server: venter på klient");

			while ((socket = server.accept()) != null) {
				Debug.log("_server: klar for å ta imot klienter");

				if (client1.isClientConneted == false && client1.socket == null) {
					Debug.log("_server: starter klient 1");
					client1.socket = socket;
					client1.start();
				} else {
					Debug.log("_server: starter klient 2");
					client2.socket = socket;
					client2.start();
					checkersEngine.startGame();
					break;
				}
			}

		} catch (IOException e) {
			Debug.log("_server: Unable to process client request");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Debug.log("_server: " + e.getMessage());
			e.printStackTrace();
		}

		// Starter spill
		while (checkersEngine.isActive) {

						
			while (checkersEngine.clientIdTurn == 1) {
				dataFromclien1.clientId = 1;
				dataTransferObject.clientIdTurn = 1;
				client1.send(dataTransferObject);
				
				//client1.recive();

				client1.send(dataTransferObject);
				
				dataFromclien1 = client1.recive();
				System.out.println("server: mottok data fra klient 1");
				if(dataFromclien1.move == null){
					System.out.println("server: mangler move data klient 1 (clientID = " + dataFromclien1.clientId + ")");
				}
				else {
					System.out.println("server: brikke flytte fra row " + dataFromclien1.move.oldPostionCol + " col " + dataFromclien1.move.oldPostionRow);	
				}
				System.out.println("server: ny melding fra klient 1 (clientID = " + dataFromclien1.clientId + ") "+ dataFromclien1.msg);
				
				
				// Do not move checker onto an occupied square.
				for (PostionValidator _pv : dataFromclien1.pieces){
					if (_pv != dataFromclien1.postionValidator 
							&& _pv.cx == dataFromclien1.postionValidator.cx
								&& _pv.cy == dataFromclien1.postionValidator.cy) {
						//Dette skal sendes tilbaek
						dataFromclien1.postionValidator.cx = dataFromclien1.move.oldcx;
						dataFromclien1.postionValidator.cy = dataFromclien1.move.oldcy;
					}
				}
				
				dataFromclien1.msg = "FLYTT";
				dataFromclien1.clientIdTurn = 2;
				dataFromclien1.clientId = 1;
				//dataTransferObject = checkersEngine.validate(dataTransferObject);
				client1.send(dataFromclien1);


				checkersEngine.clientIdTurn = 2;
			
			}

			while (checkersEngine.clientIdTurn == 2) {
				dataFromclien2 = new GameDataDTO();
				
				dataFromclien2.clientId = 2;
				dataFromclien2.clientIdTurn = 2;
				dataFromclien2.pieces = dataFromclien1.pieces;
				dataFromclien2.postionValidator = dataFromclien1.postionValidator;
				
				client2.send(dataFromclien2);
				
				client2.send(dataFromclien2);
								
				dataFromclien2 = client2.recive();
				System.out.println("server: mottok data fra klient 2");
				
				if(dataFromclien2.move == null){
					System.out.println("server: mangler move data klient 2 ");
				}
				else {
					System.out.println("server: brikke flytte fra row " + dataFromclien2.move.oldPostionCol + " col " + dataFromclien2.move.oldPostionRow);	
				}
				System.out.println("server: ny melding fra klient 2 (clientID =  " + dataFromclien2.clientId + ") "+ dataFromclien2.msg);
				
				
				// Do not move checker onto an occupied square.
				for (PostionValidator _pv : dataFromclien2.pieces){
					if (_pv != dataFromclien2.postionValidator 
							&& _pv.cx == dataFromclien2.postionValidator.cx
								&& _pv.cy == dataFromclien2.postionValidator.cy) {
						//Dette skal sendes tilbaek
						dataFromclien2.postionValidator.cx = dataFromclien2.move.oldcx;
						dataFromclien2.postionValidator.cy = dataFromclien2.move.oldcy;
					}
				}
				
				dataFromclien2.msg = "FLYTT";
				dataFromclien2.clientIdTurn = 1;
				dataFromclien2.clientId = 2;
				//dataTransferObject = checkersEngine.validate(dataTransferObject);
				client2.send(dataFromclien2);
				
				checkersEngine.clientIdTurn = 1;
			}
		}
	}

	public boolean isConnected;
	private DebugWindow Debug;
	public ClientManager client2;
	public ClientManager client1;
	private GameDataDTO dataTransferObject;

}
