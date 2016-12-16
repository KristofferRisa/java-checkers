package checkers.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import checkers.datamodels.GameDataDTO;
import checkers.graphics.DebugWindow;

public class ClientManager {
	public Socket socket;
	private DebugWindow Debug;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	public GameDataDTO data;
	
	public ClientManager(DebugWindow d){
		Debug = d;
	}
	
	public void connect(){
		
		try{			
				Debug.log("_clientManager: Starter klient tilkopling");				
							
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());			
				
		} catch(Exception e) {
			
			Debug.log("_clientManager_" + e.getMessage());
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


