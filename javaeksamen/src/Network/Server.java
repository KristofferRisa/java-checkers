package Network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import Game.Game;
import Views.DebugWindow;

public class Server extends Thread {
	
	private DebugWindow Debug;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	public Server(DebugWindow d){
		Debug = d;
		isConnected = false;
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
	    		if(numberOfClients < 3){
	    			Debug.log("_server: Number of clients connected = " + numberOfClients ); 
	        		Debug.log("_server: Ny klient tilkoplet");
					new KlientBehandler(socket, Debug).start();;
					
	        	} else {
	        		Debug.log("_server: Ny klient kunne ikke kople til da det allerede er 2 spillere tilkoplet! ");
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
