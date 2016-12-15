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
import game.PostionValidator;
import graphics.DebugWindow;

public class Server extends Thread {

	private CheckersEngine checkersEngine;
	public Server(DebugWindow debug) {
		this.checkersEngine = new CheckersEngine();
		this.Debug = debug;
		this.isConnected = false;
		this.dataTransferObject = new GameDataDTO();
		this.client1 = new ClientManager(dataTransferObject, 1, Debug);
		this.client2 = new ClientManager(dataTransferObject, 2, Debug);
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

		// Venter p� klienter i server.accept()
		try {
			Socket socket;
			Debug.log("_server: venter p� klient");

			while ((socket = server.accept()) != null) {
				Debug.log("_server: klar for � ta imot klienter");

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

			while (dataTransferObject.clientIdTurn == 1) {

				dataTransferObject = client1.recive();
				dataTransferObject = checkersEngine.validate(dataTransferObject);
				client1.send(dataTransferObject);
			
			}

			while (dataTransferObject.clientIdTurn == 2) {
				
				dataTransferObject = client2.recive();
				dataTransferObject = checkersEngine.validate(dataTransferObject);
				client2.send(dataTransferObject);
				
			}

		}

	}

	public boolean isConnected;
	private DebugWindow Debug;
	public ClientManager client2;
	public ClientManager client1;
	private GameDataDTO dataTransferObject;

}
