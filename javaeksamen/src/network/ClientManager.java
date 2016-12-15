package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import datamodels.GameDataDTO;
import game.Move;
import game.PostionValidator;
import graphics.DebugWindow;
import sun.awt.datatransfer.DataTransferer;

public class ClientManager extends Thread {
	public Socket socket;
	private DebugWindow Debug;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private PostionValidator move;
	public boolean isClientConneted;
	public GameDataDTO data;
	private int clientId;
	private CheckersEngine checkersEngine;
	private Server server;
	
	public ClientManager(GameDataDTO data, int clientId, CheckersEngine checkersEngine, Server server, DebugWindow d){
		Debug = d;
		isClientConneted = false;
		this.server = server;
		this.clientId = clientId;
		this.checkersEngine = checkersEngine;
		this.data = data;
	}
	
	public void run(){
		try{			
				Debug.log("_clientManager_"+clientId+": Starter klient tilkopling");				
				Debug.log("_clientManager_"+clientId+": sender melding til klient");
							
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
			
				data.clientId = clientId;
				data.msg = "OK";
				data.pieces = checkersEngine.pieces;
				
				output.writeObject(data);
				output.flush();
				output.reset();
				
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
				
				dataRecived.pieces = checkersEngine.pieces;
				output.writeObject(dataRecived);
				output.flush();
				output.reset();
				
				dataRecived.clientId = clientId;
				dataRecived.clientIdTurn = 1;
				dataRecived.pieces = checkersEngine.pieces;
				output.writeObject(dataRecived);
				output.flush();
				output.reset();
				
				while(checkersEngine.isActive ){
					
					while(CheckersEngine.clientIdTurn == clientId){
						
						GameDataDTO activeClient = (GameDataDTO)input.readObject();
						Debug.log(Thread.currentThread().getId() + "_clientManager_"+clientId+": Mottok ett trekk fra "+clientId + ". Move: "+ move);
						//Oppdater og send tilbake
						activeClient = checkersEngine.validate(activeClient);
						activeClient.clientIdTurn = CheckersEngine.clientIdTurn;
						activeClient.clientId = clientId;
						activeClient.pieces = CheckersEngine.pieces;
						output.writeObject(activeClient);
						output.flush();
						output.reset();
						
					} while(CheckersEngine.clientIdTurn != clientId){
						
						Debug.log("clientManager"+clientId+": sender melding til klient" + clientId);
						System.out.println(Thread.currentThread().getId()+"_clientManager: sender melding til klient" + clientId);
						dataRecived.clientId = clientId;
						dataRecived.clientIdTurn = CheckersEngine.clientIdTurn;
						dataRecived.pieces = CheckersEngine.pieces;
						output.writeObject(dataRecived);
						output.flush();
						output.reset();
						Thread.sleep(1100);
						
					}
					dataRecived.clientId = clientId;
					dataRecived.clientIdTurn = checkersEngine.clientIdTurn;
					output.writeObject(dataRecived);
					output.flush();
					output.reset();
				}
					
				
				
		} catch(Exception e) {
			
			Debug.log("_clientManager_"+clientId+": " + e.getMessage());
		}
	}
	
	public void send(GameDataDTO data2) {
		try {			
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
}


