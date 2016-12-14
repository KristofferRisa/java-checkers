package mouseListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import game.board.Piece;
import graphics.usercontrol.Board;
import graphics.usercontrol.PosCheck;

public class BoardMouseListener implements MouseListener {
	
	private static final int SQUAREDIM = 0;
	private Board board;
	private List<PosCheck> posChecks;

	private PosCheck posCheck;
	private int oldcx;
	private int oldcy;
	private int deltax;
	private int deltay;
	private boolean inDrag;
	
	public BoardMouseListener(Board board){
		
		this.board = board;

	}
	
	@Override
	public void mousePressed(MouseEvent me) {
		// Obtain mouse coordinates at time of press.

		int x = me.getX();
		int y = me.getY();

		// Locate positioned checker under mouse press.

		for (PosCheck posCheck : posChecks)
			if (Piece.contains(x, y, posCheck.cx, posCheck.cy)) {
				BoardMouseListener.this.posCheck = posCheck;
				oldcx = posCheck.cx;
				oldcy = posCheck.cy;
				deltax = x - posCheck.cx;
				deltay = y - posCheck.cy;
				inDrag = true;
				return;
			}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// When mouse released, clear inDrag (to
		// indicate no drag in progress) if inDrag is
		// already set.

		if (inDrag)
			inDrag = false;
		else
			return;

		// Snap checker to center of square.

		int x = me.getX();
		int y = me.getY();
		posCheck.cx = (x - deltax) / SQUAREDIM * SQUAREDIM + SQUAREDIM / 2;
		posCheck.cy = (y - deltay) / SQUAREDIM * SQUAREDIM + SQUAREDIM / 2;

		// Do not move checker onto an occupied square.

		for (PosCheck posCheck : posChecks)
			if (posCheck != BoardMouseListener.this.posCheck 
				&& posCheck.cx == BoardMouseListener.this.posCheck.cx
					&& posCheck.cy == BoardMouseListener.this.posCheck.cy) {
				BoardMouseListener.this.posCheck.cx = oldcx;
				BoardMouseListener.this.posCheck.cy = oldcy;
				
			}
		
		
		posCheck = null;
		board.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}