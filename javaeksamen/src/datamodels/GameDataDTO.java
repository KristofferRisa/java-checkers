package datamodels;

import java.io.Serializable;

import game.PostionValidator;
import game.Player;

/***
 * A Data transfer object class that holds all that data that is being transmitted to the clients and back to the server. 
 * @author krist
 *
 */
public class GameDataDTO implements Serializable{
	
	public GameDataDTO(){
		player1 = new Player();
		player2 = new Player();
		clientIdTurn = 1;
	}
	
	public GameDataDTO(String msg){
		player1 = new Player();
		player2 = new Player();
		this.msg = msg;
	}
		
	private static final long serialVersionUID = 4555028078803373326L;
	public Player player1;
	public Player player2;
	public String msg;
	public String ip;
	public PostionValidator move;
	public PostionValidator lastMove;
	public int clientId;
	public int clientIdTurn; //starter på 1
	
	
}
