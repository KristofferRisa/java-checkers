package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import datamodels.GameDataDTO;
import datamodels.UserInput;
import game.PostionValidator;
import graphics.DebugWindow;



public class Server extends Thread {
	public static GameDataDTO data;
	
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
			System.out.println("_server: venter på klient");

			Socket socket;
			while ((socket = server.accept()) != null) {
				System.out.println("_server: klar for å ta imot klienter");
				
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

				output = new ObjectOutputStream(socket.getOutputStream());
				
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				
				GameDataDTO data = (GameDataDTO)input.readObject();
		
				//TODO: Legge inn logikk for å håndtere tilstanden til spillet
				
				output = new ObjectOutputStream(socket.getOutputStream());

				input.close();
				//Sender data tilbake til klient
				output.writeObject(data);
				output.flush();
				output.reset();
				
				
				output.close();
				
				socket.close();
				
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
