package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import game.Game;
import game.Move;
import graphics.DebugWindowFrame;

public class KlientBehandler extends Thread {
	private Socket socket;
	private DebugWindowFrame Debug;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Move move;

	public KlientBehandler(Socket s, DebugWindowFrame d){
		socket = s;
		Debug = d;
	}
	
	public void run(){
		try{
			
				Debug.log("_klientbehandler: Starter klient tilkopling");				
				Debug.log("_klientbehandler: sender melding til klient");
							
				output = new ObjectOutputStream(socket.getOutputStream());
				
				
				DataTransferObject data = new DataTransferObject();
				data.msg =  "test melding fra server i klientbehandler Klassen";
				output.writeObject(data);
				
//				data = (GameData)input.readObject();
//
//				Debug.log("_klientbehandler: " +data.msg);
				input = new ObjectInputStream(socket.getInputStream());
				
				while(true){
					
					move = (Move)input.readObject();
					
					Debug.log("_klientBehandler: fikk melding fra klient - move= " + move.fromPostion.x + " og " + move.fromPostion.y);
					
					input.reset();
				}
						
		} catch(Exception e) {
			Debug.log("_klientbehandler: " + e.getMessage());
		}
	}
	
	public void send(DataTransferObject data) {
		try {			
			ObjectOutputStream output2 = new ObjectOutputStream(socket.getOutputStream());
			output2.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Move recive(){
		try {
			input = new ObjectInputStream(socket.getInputStream());
			return (Move)input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}


