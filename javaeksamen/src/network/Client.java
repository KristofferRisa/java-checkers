package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import datamodels.GameDataDTO;
import datamodels.UserInput;
import game.Move;
import game.PostionValidator;
import graphics.DebugWindow;

public class Client {

	private UserInput userInput;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private GameDataDTO data;
	private int clientId;
	
	public Client(UserInput userInput){
		this.isConnected = false;
		this.userInput = userInput;
	}

	public void connect(){
		try {			
			Debug.log("_klient: Forsøker å kople til server");
			
			socket = new Socket(ip, port);
			
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			
			Debug.log("_client: Socket opprettet, " + socket.isConnected());
			
			Debug.log("_client: Venter på handshake fra server");
			
			data = (GameDataDTO)input.readObject();
			clientId = data.clientId;
			
				output.writeObject(data);
				
				output.flush();
				
				data =(GameDataDTO)input.readObject();
				
			
			
		} catch (Exception e) {
			
		}
	}

	public void send(GameDataDTO data) {
		// TODO Auto-generated method stub
		try {
			data.clientId = clientId;
			output.writeObject(data);
			output.flush();
			output.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public GameDataDTO recive(){
		try {
			 data = (GameDataDTO)input.readObject();
			 return data;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public GameDataDTO getGameData(){
		return data;
	}

	private DebugWindow Debug;
	private String ip;
	private int port;
	public boolean isConnected;
	public Socket socket;

	
}