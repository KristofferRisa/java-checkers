package Models;

import java.io.Serializable;

public class Game implements Serializable{
	
	public Game(){
		setupGame();
	}
	
	//Constructor
	public Game(Player p1){
		gameType = "Test!";
		player1 = p1;
		setupGame();
	}

	private void setupGame() {
		board = new Board();
		isActive = true;
	}
	
	public Player player1;
	
	public Player player2;
	
	public Board board;
	
	public String gameType;
	
	public void restart(){
		throw new UnsupportedOperationException();
	}
	
	public void save(){
		throw new UnsupportedOperationException();
	}
	
	public void load(){
		throw new UnsupportedOperationException();
	}
	
	public void start() {
		throw new UnsupportedOperationException();
	}
	
	public boolean isActive;
		
}
