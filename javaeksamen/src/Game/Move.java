package Game;

import java.io.Serializable;

import Board.Piece;
import Board.Postion;

public class Move implements Serializable {
	public Postion fromPostion;
	public Postion toPostion;
	public Piece piece;
}
