package game;

import game.board.Move;
import graphics.DebugWindowFrame;
import graphics.WindowContainerFrame;
import network.Client;
import network.Server;

public class GameController {
	

	public boolean isActive;
	private WindowContainerFrame gui;
	private static DebugWindowFrame Debug;
	
	public GameController(DebugWindowFrame d){
		
		Debug = d;
		
		Debug.log("Starter!");
		
		isActive = false;	

	}
	
	public void startGame(){
		this.isActive = true;
	}
	
	public Player player1;
	
	public Player player2;	
	
	//TODO: Legge inn måte for holde styr på alle koordinater 
	// 		til brikken
	public String Pices;
	
	public void update(Move move1) {
		// TODO Auto-generated method stub
		
	}
	

}


