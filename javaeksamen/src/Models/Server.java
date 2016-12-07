package Models;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import Views.DebugView;

public class Server extends Thread {

	private DebugView debug;

	public Server(DebugView d){
		debug = d;
		isConnected = false;
	}
	
	private ServerSocket server;
	private Socket socket;
	
	public boolean isConnected;
	
	public void run(){
		
		    try {
		        server = new ServerSocket(1337);
		        socket = new Socket();
		        debug.log("Waiting for clients to connect...");
		        while( (socket = server.accept()) != null){
		        //while( (socket = server.accept()) != null){
		        	debug.log("Ny klient tilkoplet!");
		        	isConnected = true;
		        	InputStream inputStream = socket.getInputStream();
		        	PrintStream output = new PrintStream(socket.getOutputStream());
					
		        	output.println("Dette er en melding til player2!");
					output.flush();
					
					byte[] buffer = new byte[1024];
				    int read;
				    while((read = inputStream.read(buffer)) != -1) {
				        String out = new String(buffer, 0, read);
				        debug.log(out);
				        System.out.flush();
				    };
					
		        }
		    } catch (IOException e) {
		        debug.log("Unable to process client request");
		        e.printStackTrace();
		    }
		
	}
	
}
