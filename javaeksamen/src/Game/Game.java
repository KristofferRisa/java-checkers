package Game;

import java.io.Serializable;

public class Game implements Serializable {

	public Game(){
		setupGame();
	}

	private void setupGame() {
		player1 = new Player();		

		isActive = true;
	}
	
	public Player player1;
	
	public Player player2;
	
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
		
	private static final long serialVersionUID = 3484073086581610846L;

}
