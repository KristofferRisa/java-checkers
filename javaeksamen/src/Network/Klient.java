package Network;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import Models.Game;
import Prototype.Handshake;
import Views.DebugWindow;

public class Klient extends Thread {

	private DebugWindow Debug;

	public Klient(DebugWindow d){
		Debug = d;
		isConnected = false;
	}
	
	public boolean isConnected;
	private Socket socket;
	
	public void start(){
		try {
			
			Debug.log("Forsøker å kople til server");
			socket = new Socket("127.0.0.1", 1337);

			isConnected = true;
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			ObjectInputStream input = new ObjectInputStream(inputStream);
			Handshake hs = (Handshake)input.readObject();
							
			Debug.log("Kopler til " + hs.server);
			
			Debug.log("Forsøker å sende data til server");
			
			ObjectOutputStream output = new ObjectOutputStream(outputStream);
			hs.client = "En helt vanlig klient";
			output.writeObject(hs);
					    		
			output.close();
			
		} catch (Exception e) {
			Debug.log(e.getMessage());
			isConnected = false;
		}
	}
}
