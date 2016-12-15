package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import datamodels.GameDataDTO;
import datamodels.UserInput;

public class Client {

	private String ip;
	private int port;
	private GameDataDTO data;
	private UserInput userInput;

	public Client(UserInput userInput){
		this.ip = userInput.ipAdress;
		this.port = userInput.portNumber;
		this.userInput = userInput;
	}

	public GameDataDTO send(GameDataDTO data){
		try {			
			System.out.println("_klient: Forsøker å kople til server");
			
				Socket socket = new Socket(ip, port);
				
				data.clientId = (userInput.isServer) ? 1 : 2;
				
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				
				System.out.println("_client: Socket opprettet, " + socket.isConnected());
				
				
				output.writeObject(data);
				output.flush();
				output.reset();
				output.close();
				System.out.println("_client: Venter på svar fra server");
				
				data = (GameDataDTO)input.readObject();
				input.close();
				return data;
			
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
}
