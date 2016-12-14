package graphics.usercontrol;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import game.board.CheckerType;
import game.board.Move;
import game.board.Piece;
import game.board.Postion;
import mouseListener.BoardMouseListener;
import network.Client;

public class Board extends JPanel  {
	// dimension of checkerboard square (25% bigger than checker)
	private final static int SQUAREDIM = (int) (Piece.getDimension() * 1.25);

	// dimension of checkerboard (width of 8 squares)

	private final int BOARDDIM = 8 * SQUAREDIM;

	// preferred size of Board component

	private Dimension dimPrefSize;

	// dragging flag -- set to true when user presses mouse button over checker
	// and cleared to false when user releases mouse button

	private boolean inDrag = false;

	// displacement between drag start coordinates and checker center
	// coordinates

	private int deltax;
	
	private int deltay;

	// reference to positioned checker at start of drag

	private PosCheck posCheck;

	// center location of checker at start of drag

	private int oldcx, oldcy;

	// list of Checker objects and their initial positions

	private List<PosCheck> posChecks;

	private Client client;

	public Board(Client client) {
		// SquarePanel squarePanel = new SquarePanel();
		// add(squarePanel);
		this.client = client;
		setVisible(true);

		posChecks = new ArrayList<>();
		dimPrefSize = new Dimension(BOARDDIM, BOARDDIM);

		addMouseListener(new BoardMouseListener(this));

		// Attach a mouse motion listener to the applet. That listener listens
		// for mouse drag events.

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent me) {
				if (inDrag) {
					// Update location of checker center.

					posCheck.cx = me.getX() - deltax;
					posCheck.cy = me.getY() - deltay;
										
					repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	public void add(Piece piece, int row, int col) {
		if (row < 1 || row > 8)
			throw new IllegalArgumentException("row out of range: " + row);
		if (col < 1 || col > 8)
			throw new IllegalArgumentException("col out of range: " + col);
		PosCheck posCheck = new PosCheck();
		posCheck.piece = piece;
		posCheck.cx = (col - 1) * SQUAREDIM + SQUAREDIM / 2;
		posCheck.cy = (row - 1) * SQUAREDIM + SQUAREDIM / 2;
		for (PosCheck _posCheck : posChecks)
			if (posCheck.cx == _posCheck.cx && posCheck.cy == _posCheck.cy)
				try {
					throw new Exception("square at (" + row + "," + col + ") is occupied");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		posChecks.add(posCheck);
	}

	@Override
	public Dimension getPreferredSize() {
		return dimPrefSize;
	}

	@Override
	protected void paintComponent(Graphics g) {

		paintCheckerBoard(g);
		for (PosCheck posCheck : posChecks)
			if (posCheck != Board.this.posCheck)
				posCheck.piece.draw(g, posCheck.cx, posCheck.cy);

		// Draw dragged checker last so that it appears over any underlying
		// checker.

		if (posCheck != null)
			posCheck.piece.draw(g, posCheck.cx, posCheck.cy);
	}

	private void paintCheckerBoard(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Paint checkerboard.

		for (int row = 0; row < 8; row++) {
			g.setColor(((row & 1) != 0) ? Color.DARK_GRAY : Color.WHITE);
			for (int col = 0; col < 8; col++) {
				g.fillRect(col * SQUAREDIM, row * SQUAREDIM, SQUAREDIM, SQUAREDIM);
				g.setColor((g.getColor() == Color.DARK_GRAY) ? Color.WHITE : Color.DARK_GRAY);
			}
		}
	}
}
