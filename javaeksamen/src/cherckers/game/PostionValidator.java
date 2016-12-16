package cherckers.game;

import java.io.Serializable;

public class PostionValidator implements Serializable {

	private static final long serialVersionUID = -6334134243511203124L;
	
	// A CheckersMove object represents a move in the game of Checkers.
    // It holds the row and column of the piece that is to be moved
    // and the row and column of the square to which it is to be moved.
    // (This class makes no guarantee that the move is legal.)
	public int fromRow;  // Position of piece to be moved.
	public int fromCol;
	public int toRow, toCol;      // Square it is to move to.
	public Piece piece;
    public int cx;
	public int cy;
	
}
