package Models;

import java.io.PrintStream;
import java.net.Socket;

import Views.DebugView;

public class KlientBehandler extends Thread {
	private Socket socket;
	private DebugView debug;

	public KlientBehandler(Socket s, DebugView d){
		socket = s;
		debug = d;
	}
	
	public void run(){
		try{
			debug.log("Lager output classe for å sende data til klient");
			
			PrintStream output = new PrintStream(socket.getOutputStream());
			output.println("Dette er en melding til player2!");
			
			
		} catch(Exception e) {
			
		}
	}
}
