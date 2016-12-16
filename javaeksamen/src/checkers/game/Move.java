package cherckers.game;

import java.io.Serializable;

public class Move implements Serializable {

	private static final long serialVersionUID = 3330753089596560159L;
	public boolean isMoving = false;
	// displacement between drag start coordinates and checker center
	// coordinates
	public int deltax;		
	public int deltay;
	// center location of checker at start of drag
	public int oldcx;
	public int oldcy;
	public int oldPostionCol;
	public int oldPostionRow;
}
