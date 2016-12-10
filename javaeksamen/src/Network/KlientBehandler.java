package Network;

import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

import Views.DebugWindow;

public class KlientBehandler extends Thread {
	private Socket socket;
	private DebugWindow Debug;

	public KlientBehandler(Socket s, DebugWindow d){
		socket = s;
		Debug = d;
	}
	
	public void run(){
		try{
			
				Debug.log("_klientbehandler: Starter klient tilkopling");				
				Debug.log("_klientbehandler: sender melding til klient");
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				GameData data = new GameData();
				data.msg =  "test melding fra server i klientbehandler Klassen";
				output.writeObject(data);
						
		} catch(Exception e) {
			Debug.log("_klientbehandler: " + e.getMessage());
		}
	}
}
