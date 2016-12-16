package checkers.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import checkers.datamodels.GameDataDTO;
import checkers.graphics.DebugWindow;

public class ClientManager {
	
	public Socket socket;
	private DebugWindow debug;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	public GameDataDTO data;
	
	public ClientManager(DebugWindow debug){
		this.debug = debug;
	}
	
	public void connect(){
		
		try{
			debug.log("_clientManager: Starter klient tilkopling");		
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());			
				
		} catch(Exception e) {
			debug.log("_clientManager_" + e.getMessage());
		}
		
	}
	
	public void send(GameDataDTO data2) {
		try {			
			output.writeObject(data2);
			output.flush();
			output.reset();
		} catch (IOException e) {
			debug.log("Noe skjedde med sending av objekt i server (klientbehandler) " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public GameDataDTO recive(){
		try {
			return (GameDataDTO)input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			debug.log("Noe skjedde med mottak av object i server (klientbehanlder) " + e.getMessage() );
			e.printStackTrace();
		}
		return null;
	}
}


