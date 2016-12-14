package network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import datamodels.GameDataTransferObject;
import game.Checker;
import game.RuleEngine;
import graphics.DebugWindowFrame;
import network.data.Move;

public class Server extends Thread {
	
	public ClientManager client2;
	public ClientManager client1;
	private GameDataTransferObject dataTransferObject;
	public Server(DebugWindowFrame debug){
		this.ruleEngine = new RuleEngine(debug);
		this.Debug = debug;
		this.isConnected = false;
		this.dataTransferObject = new GameDataTransferObject();
		this.client1 = new ClientManager(dataTransferObject,1,Debug);
    	this.client2 = new ClientManager(dataTransferObject,2,Debug);
	}
	
	public void run(){
		ServerSocket server = null;
	   
		try {
	    	Debug.log("_server: Starter server");
	        server = new ServerSocket(1337);
	    }catch ( IOException ioe){
	    	Debug.log("_server: Kunne ikke lage Server socket");
	    }
	    	    
	    try{
	    	Socket socket;
	    	Debug.log("_server: venter på klient");
	    	
	    	while( (socket = server.accept()) != null){
	    		Debug.log("_server: klar for å ta imot klienter");
	    		
	    		if(client1.isClientConneted == false && client1.socket == null){
	    			Debug.log("_server: starter klient 1");
	    			client1.socket = socket;
	    			client1.start();
	    		} else {
	    			Debug.log("_server: starter klient 2");
	    			client2.socket = socket;
	    			client2.start();
	    			isActive = true;
	    			break;
	    		} 
	    		
			}
	    	
	    	while(isActive){
				//Spillet kan starte - annen hver tur
				
				client1.send(dataTransferObject);
				Move move1 = client1.recive();
				
				ruleEngine.update(move1);
				
				client2.send(dataTransferObject);
				Move move2 = client2.recive();
				
				ruleEngine.update(move2);
			}
	    	
	    } catch (IOException e) {
	    	Debug.log("_server: Unable to process client request");
	        e.printStackTrace();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
	    	Debug.log("_server: " + e.getMessage());
			e.printStackTrace();
		}		
	    Debug.log("_server: Klient frakoplet");
	}	

	public boolean isConnected;
	private RuleEngine ruleEngine;
	private boolean isActive;
	private DebugWindowFrame Debug;
	
}
