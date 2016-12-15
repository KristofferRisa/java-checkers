package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import datamodels.GameDataDTO;
import game.PostionValidator;
import graphics.DebugWindow;

public class Server extends Thread {

	public Server() {
	
	}

	public void run() {
		// Starter Server
		ServerSocket server = null;

		try {
			System.out.println("_server: Starter server");
			server = new ServerSocket(1337);
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
