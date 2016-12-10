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

import Prototype.Handshake;
import Views.DebugWindow;

public class Server extends Thread {
	
	private DebugWindow Debug;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Handshake handshake;

	public Server(DebugWindow d){
		Debug = d;
		isConnected = false;
		handshake = new Handshake();
	}
	
	public void run(){
		
	    try {
	    	Debug.log("Starter server");
	        server = new ServerSocket(1337);	 
	        while( (socket = server.accept()) != null){
	        	
				Debug.log("Ny klient tilkoplet");
				isConnected = true;
				
				//http://stackoverflow.com/questions/5680259/using-sockets-to-send-and-receive-data
				  try {
					  Debug.log("Oppretter kontakt.");					  
					  output = new ObjectOutputStream (socket.getOutputStream());
				  }
				  catch (IOException e) {
				      System.out.println(e);
				      Debug.log(e.getMessage());
				  }
				  Debug.log("Sender handshake melding til klient");
				  
				  handshake.server = "Super server 01";
				  
				  output.writeObject(handshake);
				  
				  input = new ObjectInputStream(socket.getInputStream());
				  Handshake data = (Handshake)input.readObject();
				  
				  Debug.log("Kople til: "+ data.client);
				  
				  isConnected = true;
				  
			}
	    } catch (IOException e) {
	    	Debug.log("Unable to process client request");
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	    Debug.log("Klient frakoplet");
	}
	
	public Socket getSocket(){
		if(isConnected)
		{
			return socket;
		} else {
			return null;
		}
		
	}
	
	public void SendCmd(Handshake hs){
		if(isConnected){
			try {
				Debug.log("Forsøker å sende data!");
				output.writeObject(hs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	private ServerSocket server;
	private Socket socket;
		
	public boolean isConnected;
	
}
