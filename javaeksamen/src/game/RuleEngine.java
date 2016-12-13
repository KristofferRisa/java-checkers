package game;

import graphics.DebugWindowFrame;
import graphics.WindowContainerFrame;
import network.Client;
import network.Server;
import network.data.Move;

public class RuleEngine extends Thread {
	

	public boolean isActive;
	private WindowContainerFrame gui;
	private static DebugWindowFrame Debug;
	
	public RuleEngine(DebugWindowFrame d){
		
		Debug = d;
		
		Debug.log("Starter!");
		
		isActive = false;	

	}
	
	public Player player1;
	
	public Player player2;	
	
	public GameDetails gameDetails;

	public void update(Move move1) {
		// TODO Auto-generated method stub
		
	}
	

}


