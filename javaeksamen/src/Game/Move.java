package game;

import java.io.Serializable;

import board.Piece;
import board.Postion;

public class Move implements Serializable {
	public Postion fromPostion;
	public Postion toPostion;
	public Piece piece;
}
