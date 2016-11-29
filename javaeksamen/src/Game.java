
public class Game {
	
	//Constructor
	Game(Player p1, Player p2){
		GameType = "Unni og Doffens uvannlige regler!";
		
	}
	
	public void restart(){
		throw new UnsupportedOperationException();
	}
	
	public void quit(){
		throw new UnsupportedOperationException();
	}
	
	public void save(){
		throw new UnsupportedOperationException();
	}
	
	public void load(){
		
	}
	
	public Board Board;
	
	String GameType;
}
