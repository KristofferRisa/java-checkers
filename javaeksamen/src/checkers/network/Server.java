package checkers.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import checkers.datamodels.UserInput;
import checkers.graphics.DebugWindow;

public class Server {

	private DebugWindow Debug;
	public ClientManager client;
	private UserInput userInput;
	
	public Server(UserInput userInput, DebugWindow debug) {
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
			Debug.log("_server: Kunne ikke lage Server socket. error" + ioe.getMessage());
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
			Debug.log("_server: Unable to process client request. error="+e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			Debug.log("_server: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
