package datamodels;

import java.io.Serializable;
import game.Game;
import game.Player;

/***
 * A Data transfer object class that holds all that data that is being transmitted to the clients and back to the server. 
 * @author krist
 *
 */
public class GameDataTransferObject implements Serializable{
	
	public GameDataTransferObject(){
		player1 = new Player();
		player2 = new Player();
	}
	
	public GameDataTransferObject(String msg){
		player1 = new Player();
		player2 = new Player();
		this.msg = msg;
	}
		
	private static final long serialVersionUID = 4555028078803373326L;
	public Game game;
	public Player player1;
	public Player player2;
	public String msg;
}
