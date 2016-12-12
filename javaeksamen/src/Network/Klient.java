package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import game.Move;
import graphics.DebugWindowFrame;

public class Klient extends Thread {

	private DebugWindowFrame Debug;

	public Klient(DebugWindowFrame d){
		Debug = d;
		isConnected = false;
	}
	
	public boolean isConnected;
	private Socket socket;
	
	public void start(){
		try {
			
			Debug.log("_klient: Forsøker å kople til server");
			socket = new Socket("127.0.0.1", 1337);
			
			Debug.log("_klient: Tilkoplet server");
			isConnected = true;
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
//			Debug.log("_klient: venter på data fra server");
//			ObjectInputStream input = new ObjectInputStream(inputStream);
//			GameData data = (GameData)input.readObject();
//							
//			Debug.log(" data: " + data.msg);
			
//			ObjectOutputStream output = new ObjectOutputStream(outputStream);
//			data.msg = "Dette er en melding tilbake til server fra klient klassen";
//			output.writeObject(data);					    		
//			output.close();
			
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
