package Network;

import java.io.Serializable;
import Game.Game;

/***
 * A Data transfer object class that holds all that data that is being transmitted to the clients and back to the server. 
 * @author krist
 *
 */
public class GameData implements Serializable{
	public Game game;
	public String msg;
}
