package Prototype;

import Views.DebugWindow;

public class ConnectionManager {

	private static DebugWindow Debug = null;
	
	public ConnectionManager(){
		Debug = new DebugWindow();
		Debug.log("Starter");
		Debug.log("TEST");

		
	}
	
	
	public static void main(String[] args){
		new ConnectionManager();
	}
	
}
