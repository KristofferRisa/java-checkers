package checkers.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import checkers.datamodels.GameDataDTO;
import checkers.datamodels.UserInput;
import checkers.graphics.DebugWindow;

public class Client {

	private UserInput userInput;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket socket;
	private DebugWindow debug;
	
	public Client(UserInput userInput, DebugWindow d){
		this.debug = d;
		this.userInput = userInput;
	}

	public void connect(){
		try {			
			debug.log("_klient: Forsøker å kople til server");
			
			socket = new Socket(userInput.ipAdress, userInput.portNumber);
			
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			
			debug.log("_client: Socket opprettet, " + socket.isConnected());

		} catch (Exception e) {
			debug.log("_client: " + e.getMessage());
		}
	}

	public void send(GameDataDTO data2) {
		
		try {
			output.writeObject(data2);
			output.flush();
			output.reset();
		} catch (IOException e) {
			debug.log("Noe skjedde med sending av objekt " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public GameDataDTO recive(){
		
		try {
			return (GameDataDTO)input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			debug.log("Noe feil skjedde med mottak av objekt " + e.getMessage());
			e.printStackTrace();
		}
		return null;
		
	}
}
