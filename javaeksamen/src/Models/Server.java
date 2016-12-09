package Models;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Views.DebugView;

public class Server extends Thread {

	public Server(Game g,DebugView d){
		game = g;
		debug = d;
		isConnected = false;
	}
    
	public void stopServer()
    {
        running = false;
        this.interrupt();
    }
	
	public boolean isConnected;
	
	public void run(){
		running = true;
        while( running )
        {
        	//http://www.javaworld.com/article/2853780/core-java/socket-programming-for-scalable-systems.html?page=2

		    try {
		        server = new ServerSocket(1337);
		        debug.log("Waiting for clients to connect...");
		        
		        socket = server.accept();
		        debug.log("klient koplet til");
		        
		    } catch (IOException e) {
		        debug.log("Unable to process client request");
		        e.printStackTrace();
		    }		
        }
	    debug.log("ferdig med klient!");
	}
	
	public void sendGame(){
		
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(game);
	    	output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    
	private ServerSocket server;
	private Socket socket;
	private Game game;
	private boolean running;

	
	private DebugView debug;
	
}
