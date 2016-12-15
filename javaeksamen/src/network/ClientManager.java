package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import datamodels.GameDataDTO;
import game.PostionValidator;
import graphics.DebugWindow;

public class ClientManager extends Thread {
	public Socket socket;
	private DebugWindow Debug;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private PostionValidator move;
	public boolean isClientConneted;
	public GameDataDTO data;
	private int clientId;
	
	public ClientManager(GameDataDTO data, int clientId, DebugWindow d){
		Debug = d;
		isClientConneted = false;
		this.clientId = clientId;
		this.data = data;
	}
	
	public void run(){
		try{			
				Debug.log("_clientManager_"+clientId+": Starter klient tilkopling");				
				Debug.log("_clientManager_"+clientId+": sender melding til klient");
							
				output = new ObjectOutputStream(socket.getOutputStream());
				
				data.clientId = clientId;
				data.msg = "OK";
				
				output.writeObject(data);
				
				Debug.log("_clientManager_"+clientId+": Sendt OK melding til klient!" + data.msg);
				
				input = new ObjectInputStream(socket.getInputStream());
			
				GameDataDTO dataRecived = (GameDataDTO)input.readObject();
				
				if(dataRecived.player1 != null){
					data.player1 = dataRecived.player1;
				} 
				if(dataRecived.player2 != null){
					data.player2 = dataRecived.player2;
				}
				
				Debug.log("_clientManager_"+clientId+": Mottatt melding fra klient. data.msg = " + data.msg);
				
				if(data.msg.equals("OK")){
					Debug.log("_clientManager_"+clientId+": Fikk melding fra klient. msg = " + data.msg);
					isClientConneted = true;
				} else {
					Debug.log("_clientManager_"+clientId+": Noe feilet med handshake. data.msg = " + data.msg);
				}
				
				while(data.player2.name == null){
					Debug.log("_clientManager_"+clientId+": Venter på spiller 2");
					sleep(1100);
					//Venter på spiller 2 
					// må sende info om spiller 2 til spiller1 
				}
				
				output.writeObject(data);
				
//				while(true){
//					Move move = (Move)input.readObject();
//					Debug.log("_clientManager_"+clientId+": Mottok ett trekk fra "+clientId + ". Move: "+ move);
//					
//				}
				
		} catch(Exception e) {
			
			Debug.log("_clientManager_"+clientId+": " + e.getMessage());
		}
	}
	
	public void send(GameDataDTO data) {
		try {			
			ObjectOutputStream output2 = new ObjectOutputStream(socket.getOutputStream());
			output2.writeObject(data);
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


