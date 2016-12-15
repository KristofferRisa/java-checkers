package game;

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
	
	public PostionValidator(){
		
	}
	
	public PostionValidator(int r1, int c1, int r2, int c2) {
		// Constructor.  Just set the values of the instance variables.
		fromRow = r1;
		fromCol = c1;
		toRow = r2;
		toCol = c2;
	}
		 
	public Piece piece;
	     
	public int cx;
	     
	public int cy;
	
}
