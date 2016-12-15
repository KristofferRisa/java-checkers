package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import datamodels.GameDataDTO;
import datamodels.UserInput;
import game.PostionValidator;
import graphics.DebugWindow;

public class Server extends Thread {

	private UserInput userInput;

	public Server(UserInput userInput) {
		this.userInput = userInput;
	}

	public void run() {
		// Starter Server
		ServerSocket server = null;

		try {
			System.out.println("_server: Starter server");
			
			server = new ServerSocket(userInput.portNumber);
			
		} catch (IOException ioe) {
			System.out.println("_server: Kunne ikke lage Server socket");
		}

		// Venter på klienter i server.accept()
		try {
			Socket socket;
			System.out.println("_server: venter på klient");

			while ((socket = server.accept()) != null) {
				System.out.println("_server: klar for å ta imot klienter");

				new ClientManager(socket).start();
				
			}

		} catch (IOException e) {
			System.out.println("_server: Unable to process client request");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("_server: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
