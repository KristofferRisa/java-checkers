package network.data;

import java.io.Serializable;

import game.board.Piece;
import game.board.Postion;

public class Move implements Serializable {
	private static final long serialVersionUID = 5139389699256854634L;
	
	public Move(Postion from, Postion to){
		this.fromPostion = from;
		this.toPostion = to;
	}
	
	public Postion fromPostion;
	public Postion toPostion;
	public Piece piece;
}
