package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import datamodels.GameDataDTO;
import datamodels.UserInput;

public class Client {

	private String ip;
	private int port;
	private UserInput userInput;
	public int id;
	private ObjectInputStream input;
	private Socket socket;
	private ObjectOutputStream output;

	public Client(UserInput userInput){
		this.ip = userInput.ipAdress;
		this.port = userInput.portNumber;
		this.userInput = userInput;
		this.id = (userInput.isServer) ? 1 : 2;

		
		
		
		
	}

	public GameDataDTO send(GameDataDTO data){
		try {		
			
			try {
				socket = new Socket(ip, port);
				output = new ObjectOutputStream(socket.getOutputStream());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("_klient: Forsøker å kople til server");
			
				data.clientId = (userInput.isServer) ? 1 : 2;
				
				
				System.out.println("_client: Socket opprettet, " + socket.isConnected());
				
				output.writeObject(data);

//				output.close();
				
				System.out.println("_client: Venter på svar fra server");
				
				input = new ObjectInputStream(socket.getInputStream());
				
				data = (GameDataDTO)input.readObject();
				
				output.flush();
				output.reset();
				
//				input.close();				
//				socket.close();
				
				return data;
			
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
}
