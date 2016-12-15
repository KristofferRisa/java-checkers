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
import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import datamodels.GameDataDTO;
import game.Checker;
import game.Move;
import game.PostionValidator;
import graphics.BoardPanel;
import graphics.DebugWindow;

public class Server extends Thread {

	private static CheckersEngine checkersEngine = new CheckersEngine();
	private GameDataDTO dataFromclien1;
	private GameDataDTO dataFromclien2;
	
	public Server(DebugWindow debug) {
		checkersEngine.clientIdTurn = 1;
		this.Debug = debug;
		this.isConnected = false;
		this.dataTransferObject = new GameDataDTO();
		this.client1 = new ClientManager(dataTransferObject,1, checkersEngine, this, Debug);
		this.client2 = new ClientManager(dataTransferObject,2, checkersEngine,this, Debug);
		this.dataFromclien1 = new GameDataDTO();
		this.dataFromclien2 = new GameDataDTO();
		checkersEngine.startGame();
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

						try {
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		
		}
	}

	public boolean isConnected;
	private DebugWindow Debug;
	public ClientManager client2;
	public ClientManager client1;
	private GameDataDTO dataTransferObject;
	public List<PostionValidator> pieces;

}
