package network;

import java.io.Serializable;

import game.Checker;

/***
 * A Data transfer object class that holds all that data that is being transmitted to the clients and back to the server. 
 * @author krist
 *
 */
public class GameDataTransferObject implements Serializable{
	private static final long serialVersionUID = 4555028078803373326L;
	public Checker game;
	public String msg;
}
