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

import game.Checker;
import game.board.Move;
import graphics.DebugWindowFrame;

public class Server extends Thread {
	
	private DebugWindowFrame Debug;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Checker game;
	private ClientManager klient1;
	private ClientManager klient2;

	public Server(Checker game){
		Debug = new DebugWindowFrame("checkers server logg");
		isConnected = false;
		this.game = game;
		game.isActive = true;
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
	    	int numberOfClients = 0;
	    	Debug.log("_server: venter på klient");
	    	while( (socket = server.accept()) != null){
	    		numberOfClients++;
	    		
	    		Debug.log("_server: klar for å ta imot klienter");
	    		Debug.log("_server: Number of clients connected = " + numberOfClients );
	    		if(numberOfClients == 1){
	    			 
	        		Debug.log("_server: Ny klient tilkoplet");
					klient1 = new ClientManager(socket, Debug);
					klient1.start();
	        	} else if(numberOfClients == 2) {
	        		
	        		Debug.log("_server: Ny klient tilkoplet");
					klient2 = new ClientManager(socket, Debug);
					klient2.start();
	        	
	        	} else {
	        			    			
	        		while(game.isActive){
	        			//Spillet kan starte - annen hver tur
	        			GameDataTransferObject data = new GameDataTransferObject();
	        			data.game = game;
	        			
	        			klient1.send(data);
	        			Move move1 = klient1.recive();
	        			
	        			game.update(move1);
	        			
	        			klient2.send(data);
	        			Move move2 = klient2.recive();
	        			
	        			game.update(move2);
	        		}
	        		
	        	}
	    		
	    		
	    		
	    		
			}
	    } catch (IOException e) {
	    	Debug.log("_server: Unable to process client request");
	        e.printStackTrace();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	    Debug.log("_server: Klient frakoplet");
	}	
			
	public boolean isConnected;
	
}
