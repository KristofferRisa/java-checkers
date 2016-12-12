package game;

import game.board.Move;
import graphics.DebugWindowFrame;
import graphics.WindowContainerFrame;
import network.Client;
import network.Server;

public class RuleEngine extends Thread {
	

	public boolean isActive;
	private WindowContainerFrame gui;
	private static DebugWindowFrame Debug;
	
	public RuleEngine(DebugWindowFrame d){
		
		isServer = false;
		Debug = d;
		
		Debug.log("Starter!");
		
		isActive = false;	
		
		
		
	}
	
	public Player player1;
	
	public Player player2;	
	
	public GameDetails gameDetails;
	
	private Checker game;
	private Server server;
	private Client klient;
	public Boolean isServer;

	public void update(Move move1) {
		// TODO Auto-generated method stub
		
	}
	

}


