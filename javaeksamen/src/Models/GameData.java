package Models;

import java.io.Serializable;

public class GameData implements Serializable {
	
	public GameData(Player p1){
		Name = "Spill test";
		player1 = p1;
	}
	
	public String Name;
	
	public Player player1;
	
	public Player player2;
}
