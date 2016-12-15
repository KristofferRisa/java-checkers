package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import datamodels.GameDataDTO;
import datamodels.UserInput;
import game.Move;
import game.PostionValidator;
import graphics.DebugWindow;

public class Client {

	private UserInput userInput;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private GameDataDTO data;
	private int clientId;
	
	public Client(String ip, int port, UserInput userInput, DebugWindow d){
		this.ip = ip;
		this.port = port;
		this.Debug = d;
		this.isConnected = false;
		this.userInput = userInput;
	}

	public void connect(){
		try {			
			Debug.log("_klient: Forsøker å kople til server");
			
			socket = new Socket(ip, port);
			
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			
			Debug.log("_client: Socket opprettet, " + socket.isConnected());
			
			Debug.log("_client: Venter på handshake fra server");
			
			data = (GameDataDTO)input.readObject();
			clientId = data.clientId;
			if(data.msg.equals("OK")){				
				//Legger til spill info til data 
				// transfer object
				if(userInput.isServer){
					data.player1.isHuman = true;
					data.player1.name = userInput.name;
				} else {
					data.player2.isHuman = true;
					data.player2.name = userInput.name;
				}
				
				Debug.log("_client: Tilkoplet server. melding fra server = " + data.msg);
				
				output.writeObject(data);
				
				output.flush();
				
				data =(GameDataDTO)input.readObject();
				
				isConnected = true;
				
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

	public void send(GameDataDTO data2) {
		// TODO Auto-generated method stub
		try {
			data2.clientId = clientId;
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
	
	public GameDataDTO getGameData(){
		return data;
	}

	private DebugWindow Debug;
	private String ip;
	private int port;
	public boolean isConnected;
	public Socket socket;

	
}
