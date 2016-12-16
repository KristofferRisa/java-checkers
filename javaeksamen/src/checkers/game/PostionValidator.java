package checkers.game;

import java.io.Serializable;

public class PostionValidator implements Serializable {

	private static final long serialVersionUID = -6334134243511203124L;
	
	public int fromRow;  // Position of piece to be moved.
	public int fromCol;
	public int toRow, toCol;      // Square it is to move to.
	public Piece piece;
    public int cx;
	public int cy;
	
}
