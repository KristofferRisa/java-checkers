package Models;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import Views.DebugView;

public class Klient extends Thread {
	
	private DebugView debug;

	public Klient(DebugView d){
		isConnected = false;
		debug = d;
	}
	
	public boolean isConnected;
	private Socket socket;
	
	public void start(){
		try {
			
			debug.log("Forsøker å kople til server");
			socket = new Socket("127.0.0.1", 1337);

			isConnected = true;
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			ObjectInputStream input = new ObjectInputStream(inputStream);
			Game dataFromServer = (Game)input.readObject();
			
			debug.log("leser objekt fra server");
			debug.log("Spiller 1: " + dataFromServer.player1.name);
			
			ObjectOutputStream out = new ObjectOutputStream(outputStream);
			
		    byte[] buffer = new byte[1024];
		    int read;
		    while((read = inputStream.read(buffer)) != -1) {
		        String output = new String(buffer, 0, read);
		        debug.log(output);
		        System.out.flush();
		    };
			
			debug.log("Forsøker å sende data til server");
			out.writeObject("Her er player 2 ");
			out.flush();
			
			out.close();
			
		} catch (Exception e) {
			debug.log(e.getMessage());
			isConnected = false;
		}
	}
}
