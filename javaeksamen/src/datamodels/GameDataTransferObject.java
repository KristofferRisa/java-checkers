package datamodels;

import java.io.Serializable;
import game.Game;

/***
 * A Data transfer object class that holds all that data that is being transmitted to the clients and back to the server. 
 * @author krist
 *
 */
public class GameDataTransferObject implements Serializable{
	
	public GameDataTransferObject(){
		
	}
	
	public GameDataTransferObject(String msg){
		this.msg = msg;
	}
	
	public GameDataTransferObject(Game game, String msg){
		this.game = game;
		this.msg = msg;
	}
	
	private static final long serialVersionUID = 4555028078803373326L;
	public Game game;
	public String msg;
}
