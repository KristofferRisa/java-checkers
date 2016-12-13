package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import datamodels.GameDataTransferObject;
import game.Checker;
import graphics.DebugWindowFrame;
import network.data.Move;

public class ClientManager extends Thread {
	public Socket socket;
	private DebugWindowFrame Debug;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Move move;
	public boolean isClientConneted;
	public GameDataTransferObject data;
	public ClientManager(DebugWindowFrame d){
		Debug = d;
		isClientConneted = false;
	}
	
	public void run(){
		try{			
				Debug.log("_klientbehandler: Starter klient tilkopling");				
				Debug.log("_klientbehandler: sender melding til klient");
							
				output = new ObjectOutputStream(socket.getOutputStream());
				
				data = new GameDataTransferObject("OK");
				
				output.writeObject(data);
				
				Debug.log("_klientbehandler: Sendt OK melding til klient!" + data.msg);
				
				input = new ObjectInputStream(socket.getInputStream());
			
				GameDataTransferObject dataRecived = (GameDataTransferObject)input.readObject();
				
				Debug.log("_clientManager: Mottatt melding fra klient. data.msg = " + data.msg);
				
				if(dataRecived.msg.equals("OK")){
					Debug.log("_clientManager: Fikk melding fra klient. msg = " + dataRecived.msg);
					isClientConneted = true;
				} else {
					Debug.log("_clientManager: Noe feilet med handshake. data.msg = " + dataRecived.msg);
				}
					
		} catch(Exception e) {
			
			Debug.log("_klientbehandler: " + e.getMessage());
		}
	}
	
	public void send(GameDataTransferObject data) {
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


