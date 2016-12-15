package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import datamodels.GameDataDTO;
import game.Move;
import game.PostionValidator;
import graphics.DebugWindow;
import sun.awt.datatransfer.DataTransferer;

public class ClientManager extends Thread {
	
	private Socket socket;

	public ClientManager(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try{	
			
			while(true){
				//Klar for å motta fra klienter
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				
				GameDataDTO data = (GameDataDTO)input.readObject();
		
				//TODO: Legge inn logikk for å håndtere tilstanden til spillet
				
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

				input.close();
				//Sender data tilbake til klient
				output.writeObject(data);
				output.flush();
				output.reset();
				
				
//				output.close();
				
//				socket.close();
			}
						
			
				
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}


