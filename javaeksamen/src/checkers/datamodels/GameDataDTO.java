package checkers.datamodels;

import java.io.Serializable;
import java.util.List;

import cherckers.game.Move;
import cherckers.game.PostionValidator;
/***
 * A Data transfer object class that holds all that data that is being transmitted to the clients and back to the server. 
 * @author krist
 *
 */
public class GameDataDTO implements Serializable{

	private static final long serialVersionUID = 4555028078803373326L;
	public String player1;
	public String player2;
	public String msg;
	public Move move;
	public int clientIdTurn; //starter p� 1
	public List<PostionValidator> pieces;
	public PostionValidator postionValidator;
	public boolean gameActive;
	
	public GameDataDTO(){
		clientIdTurn = 1;
	}
	
}