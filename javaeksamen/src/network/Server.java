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

import datamodels.GameDataTransferObject;
import game.Checker;
import game.Game;
import game.RuleEngine;
import graphics.DebugWindowFrame;
import helpers.Network;
import network.data.Move;

public class Server extends Thread {
	
	private ClientManager client2;
	private ClientManager client1;
	public Server(RuleEngine ruleEngine){
		Debug = new DebugWindowFrame("checkers server logg");
		isConnected = false;
		game = new Game();
		this.ruleEngine = ruleEngine;
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
	    	
	    	//Instansierer 2 klient objekter 
	    	client1 = new ClientManager(Debug);
	    	client2 = new ClientManager(Debug);
	    	
	    	while( (socket = server.accept()) != null){
	    		Debug.log("_server: klar for å ta imot klienter");
	    		
	    		if(client1.isClientConneted == false && client1.socket == null){
	    			client1.socket = socket;
	    			client1.start();
	    		} else if(client2.isClientConneted == false && client2.socket == null){
	    			client2.socket = socket;
	    			client2.start();
	    		} else {
	    			helpers.Network helper = new Network();
	    			helper.sendObject(socket, new GameDataTransferObject("ERROR"));
	    		}
	    		
	    			
	        		while(game.isActive){
	        			//Spillet kan starte - annen hver tur
	        			GameDataTransferObject data = new GameDataTransferObject();
	        			data.game = game;
	        			
	        			klient1.send(data);
	        			Move move1 = klient1.recive();
	        			
	        			ruleEngine.update(move1);
	        			
	        			klient2.send(data);
	        			Move move2 = klient2.recive();
	        			
	        			ruleEngine.update(move2);
	        		}
	        		
	        	
	    		
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
	private DebugWindowFrame Debug;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Game game;
	private ClientManager klient1;
	private ClientManager klient2;
	private RuleEngine ruleEngine;
	
}
