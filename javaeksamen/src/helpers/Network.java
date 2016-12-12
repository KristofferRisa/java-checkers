package helpers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import network.data.Move;

public class Network {
	
	/*
	 * Helper class for sending object over a socket with ObjectOutputStream
	 * Returns 1 if everything is OK
	 * Returns 0 if something went wrong
	 */
	public int sendObject(Socket socket, Move move){
		
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(move);
//			output.flush();
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/*
	 * Helper class for reading objects from socket with ObjectInputStream
	 * Returns Move Class
	 */
	public Object readObject(Socket socket){
		
		try {
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			return (Object)input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
