package datamodels;

import java.io.Serializable;
import java.util.List;

import game.PostionValidator;
import game.Move;
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
	public Move move;
	public int clientId;
	public int clientIdTurn; //starter på 1
	public List<PostionValidator> pieces;
	public PostionValidator postionValidator;
	public void setMove(Move move2) {

		this.move = move2;
	}
	
	
}
