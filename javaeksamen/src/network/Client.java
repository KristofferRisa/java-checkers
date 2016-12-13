package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import datamodels.GameDataTransferObject;
import datamodels.UserInput;
import graphics.DebugWindowFrame;
import network.data.Move;

public class Client {

	private UserInput userInput;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	public Client(String ip, int port, UserInput userInput, DebugWindowFrame d){
		this.ip = ip;
		this.port = port;
		this.Debug = d;
		this.isConnected = false;
		this.userInput = userInput;
	}
	
	public void connectServer(){
		try {			
			Debug.log("_klient: Forsøker å kople til server");
			
			socket = new Socket(ip, port);
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			
			Debug.log("_client: Socket opprettet, " + socket.isConnected());
			
			Debug.log("_client: Venter på handshake fra server");
			
			data = (GameDataTransferObject)input.readObject();
			
			if(data.msg.equals("OK")){
				
				if(userInput.isServer){
					data.player1.isHuman = true;
					data.player1.name = userInput.name;
				} else {
					data.player2.isHuman = true;
					data.player2.name = userInput.name;
				}
				
				Debug.log("_client: Tilkoplet server. melding fra server = " + data.msg);
				
				output.writeObject(data);

				isConnected = true;
				
				data =(GameDataTransferObject)input.readObject();
				
			} else {
				isConnected = false;
				Debug.log("_client: " + data.msg);
				System.out.println(data.msg);
			}
			
		} catch (Exception e) {
			Debug.log("_client: " + e.getMessage());
			isConnected = false;
			
		}
	}

	public void send(Move move) {
		// TODO Auto-generated method stub
		try {
			output.writeObject(move);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	private DebugWindowFrame Debug;
	private String ip;
	private int port;
	public boolean isConnected;
	public Socket socket;
	public GameDataTransferObject data;
}
