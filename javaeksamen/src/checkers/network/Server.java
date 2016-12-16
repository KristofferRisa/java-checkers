package checkers.network;

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

import checkers.datamodels.GameDataDTO;
import checkers.datamodels.UserInput;
import checkers.graphics.BoardPanel;
import checkers.graphics.DebugWindow;
import cherckers.game.Checker;
import cherckers.game.Move;
import cherckers.game.PostionValidator;

public class Server {

	private CheckersEngine checkersEngine;
	private UserInput userInput;
	public Server(UserInput userInput, DebugWindow debug) {
		this.checkersEngine = new CheckersEngine();
		this.Debug = debug;
		this.userInput = userInput;
		this.client = new ClientManager(debug);
	}

	public void connect() {
		// Starter Server
		ServerSocket server = null;

		try {
			
			Debug.log("_server: Starter server");
			server = new ServerSocket(userInput.portNumber);
			
		} catch (IOException ioe) {
			Debug.log("_server: Kunne ikke lage Server socket");
		}

		// Venter på klienter i server.accept()
		try {
			Socket socket;
			Debug.log("_server: venter på klient");

			socket = server.accept();
			Debug.log("_server: klar for å ta imot klienter");

			client.socket = socket;
			client.connect();
			

		} catch (IOException e) {
			Debug.log("_server: Unable to process client request");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Debug.log("_server: " + e.getMessage());
			e.printStackTrace();
		}

	}

	private DebugWindow Debug;
	public ClientManager client;

}
