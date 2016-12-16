package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import datamodels.GameDataDTO;
import datamodels.UserInput;
import graphics.DebugWindow;

public class Client {

	private UserInput userInput;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket socket;
	private DebugWindow Debug;
	
	public Client(UserInput userInput, DebugWindow d){
		this.Debug = d;
		this.userInput = userInput;
	}

	public void connect(){
		try {			
			Debug.log("_klient: Forsøker å kople til server");
			
			socket = new Socket(userInput.ipAdress, userInput.portNumber);
			
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			
			Debug.log("_client: Socket opprettet, " + socket.isConnected());
//			data = (GameDataDTO)input.readObject();
//		
//			output.writeObject(data);
//				
//			output.flush();
//				
		} catch (Exception e) {
			Debug.log("_client: " + e.getMessage());
		}
	}

	public void send(GameDataDTO data2) {
		
		try {
			output.writeObject(data2);
			output.flush();
			output.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public GameDataDTO recive(){
		
		try {
			return (GameDataDTO)input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	
}
