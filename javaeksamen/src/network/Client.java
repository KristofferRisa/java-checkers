package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import graphics.DebugWindowFrame;
import helpers.Network;
import network.data.Move;

public class Client extends Thread {

	private DebugWindowFrame Debug;

	public Client(DebugWindowFrame d){
		Debug = d;
		isConnected = false;
	}
	
	public boolean isConnected;
	
	private Socket socket;
	
	public void start(){
		try {			
			Debug.log("_klient: Forsøker å kople til server");
			socket = new Socket("127.0.0.1", 1337);
			
			Network helper = new Network();
			Object data = helper.readObject(socket);
			
			//
			isConnected = true;
			
			
		} catch (Exception e) {
			Debug.log(e.getMessage());
			isConnected = false;
		}
	}

	public void send(Move move) {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(move);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
