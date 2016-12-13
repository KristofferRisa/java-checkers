package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import datamodels.GameDataTransferObject;
import graphics.DebugWindowFrame;
import helpers.Network;
import network.data.Move;

public class Client extends Thread {

	private DebugWindowFrame Debug;

	private String ip;

	private int port;

	public Client(String ip, int port, DebugWindowFrame d){
		this.ip = ip;
		this.port = port;
		this.Debug = d;
		this.isConnected = false;
	}
	
	public boolean isConnected;
	
	public Socket socket;

	private GameDataTransferObject data;
	
	public void start(){
		try {			
			Debug.log("_klient: Forsøker å kople til server");
			
			socket = new Socket(ip, port);
			
			Network helper = new Network();
			
			data = helper.readGameData(socket);
			
			if(data.msg.equals("OK")){
				helper.sendObject(socket, data);
				isConnected = true;
				Debug.log("_client: Tilkoplet server. melding fra server = " + data.msg);
			} else {
				isConnected = false;
				Debug.log("_client: " + data.msg);
				System.out.println(data.msg);
			}
			
			
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
