package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import datamodels.GameDataDTO;
import game.Checker;
import game.Move;
import game.PostionValidator;
import graphics.BoardPanel;
import graphics.DebugWindow;

public class Server extends Thread {

	private CheckersEngine checkersEngine;
	public Server(DebugWindow debug) {
		this.checkersEngine = new CheckersEngine();
		this.Debug = debug;
		this.isConnected = false;
		this.dataTransferObject = new GameDataDTO();
		this.client1 = new ClientManager(dataTransferObject,1, Debug);
		this.client2 = new ClientManager(dataTransferObject,2, Debug);
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

				client1.send(dataTransferObject);
				
				GameDataDTO datare = client1.recive();
				System.out.println("server: mottok data fra klient 1");
				if(datare.move == null){
					System.out.println("server: mangler move data klient 1 (clientID = " + datare.clientId + ")");
				}
				else {
					System.out.println("server: brikke flytte fra row " + datare.move.oldPostionCol + " col " + datare.move.oldPostionRow);	
				}
				System.out.println("server: ny melding fra klient 1 (clientID = " + datare.clientId + ") "+ datare.msg);
				
				
				// Do not move checker onto an occupied square.
				for (PostionValidator _pv : datare.pieces){
					if (_pv != datare.postionValidator 
							&& _pv.cx == datare.postionValidator.cx
								&& _pv.cy == datare.postionValidator.cy) {
						//Dette skal sendes tilbaek
						datare.postionValidator.cx = datare.move.oldcx;
						datare.postionValidator.cy = datare.move.oldcy;
					}
				}
				
				datare.msg = "FLYTT";
				datare.clientId = 1;
				datare.clientIdTurn = 2;
				//dataTransferObject = checkersEngine.validate(dataTransferObject);
				client1.send(datare);


				checkersEngine.clientIdTurn = 2;
			
			}
			
			while (checkersEngine.clientIdTurn == 2) {
				
				client2.send(dataTransferObject);
				
				GameDataDTO datare = client1.recive();
				System.out.println("server: mottok data fra klient 2");
				
				if(datare.move == null){
					System.out.println("server: mangler move data klient 2 ");
				}
				else {
					System.out.println("server: brikke flytte fra row " + datare.move.oldPostionCol + " col " + datare.move.oldPostionRow);	
				}
				System.out.println("server: ny melding fra klient 2 (clientID =  " + datare.clientId + ") "+ datare.msg);
				
				
				// Do not move checker onto an occupied square.
				for (PostionValidator _pv : datare.pieces){
					if (_pv != datare.postionValidator 
							&& _pv.cx == datare.postionValidator.cx
								&& _pv.cy == datare.postionValidator.cy) {
						//Dette skal sendes tilbaek
						datare.postionValidator.cx = datare.move.oldcx;
						datare.postionValidator.cy = datare.move.oldcy;
					}
				}
				
				datare.msg = "FLYTT";
				datare.clientIdTurn = 1;
				//dataTransferObject = checkersEngine.validate(dataTransferObject);
				client2.send(datare);
				
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
