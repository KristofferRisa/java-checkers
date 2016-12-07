package Models;

public class Game {
	
	//Constructor
	public Game(Player p1, Player p2){
		gameType = "Unni og Doffens uvannlige regler!";
		player1 = p1;
		player2 = p2;
		board = new Board();
		isActive = true;
	}
	
	private Player player1;
	
	private Player player2;
	
	private Board board;
	
	private String gameType;
	
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
