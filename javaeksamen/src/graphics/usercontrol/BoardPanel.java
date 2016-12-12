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
import game.board.Piece;

public class BoardPanel extends JPanel implements MouseListener {
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

	private int deltax, deltay;

	// reference to positioned checker at start of drag

	private PosCheck posCheck;

	// center location of checker at start of drag

	private int oldcx, oldcy;

	// list of Checker objects and their initial positions

	private List<PosCheck> posChecks;

	public BoardPanel() {
		SquarePanel squarePanel = new SquarePanel();
		add(squarePanel);
		setVisible(true);

		posChecks = new ArrayList<>();
		dimPrefSize = new Dimension(BOARDDIM, BOARDDIM);

		addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent me) {
				// Obtain mouse coordinates at time of press.

				int x = me.getX();
				int y = me.getY();

				// Locate positioned checker under mouse press.

				for (PosCheck posCheck : posChecks)
					if (Piece.contains(x, y, posCheck.cx, posCheck.cy)) {
						BoardPanel.this.posCheck = posCheck;
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
					if (posCheck != BoardPanel.this.posCheck && posCheck.cx == BoardPanel.this.posCheck.cx
							&& posCheck.cy == BoardPanel.this.posCheck.cy) {
						BoardPanel.this.posCheck.cx = oldcx;
						BoardPanel.this.posCheck.cy = oldcy;
					}
				posCheck = null;
				repaint();
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

		});

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
	
	public void add(Piece piece, int row, int col){
		 if (row < 1 || row > 8)
	         throw new IllegalArgumentException("row out of range: " + row);
	      if (col < 1 || col > 8)
	         throw new IllegalArgumentException("col out of range: " + col);
	      PosCheck posCheck = new PosCheck();
	      posCheck.piece = piece;
	      posCheck.cx = (col - 1) * SQUAREDIM + SQUAREDIM / 2;
	      posCheck.cy = (row - 1) * SQUAREDIM + SQUAREDIM / 2;
	      for (PosCheck _posCheck: posChecks)
	         if (posCheck.cx == _posCheck.cx && posCheck.cy == _posCheck.cy)
				try {
					throw new Exception("square at (" + row + "," +
					                                   col + ") is occupied");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      posChecks.add(posCheck);
	}
	   @Override
	   public Dimension getPreferredSize()
	   {
	      return dimPrefSize;
	   }

	   @Override
	   protected void paintComponent(Graphics g)
	   {
	      paintCheckerBoard(g);
	      for (PosCheck posCheck: posChecks)
	         if (posCheck != BoardPanel.this.posCheck)
	            posCheck.piece.draw(g, posCheck.cx, posCheck.cy);

	      // Draw dragged checker last so that it appears over any underlying 
	      // checker.

	      if (posCheck != null)
	         posCheck.piece.draw(g, posCheck.cx, posCheck.cy);
	   }

	
	   private void paintCheckerBoard(Graphics g)
	   {
	      ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                                        RenderingHints.VALUE_ANTIALIAS_ON);

//	       Paint checkerboard.

	      for (int row = 0; row < 8; row++)
	      {
	         g.setColor(((row & 1) != 0) ? Color.BLACK : Color.WHITE);
	         for (int col = 0; col < 8; col++)
	         {
	            g.fillRect(col * SQUAREDIM, row * SQUAREDIM, SQUAREDIM, SQUAREDIM);
	            g.setColor((g.getColor() == Color.BLACK) ? Color.WHITE : Color.BLACK);
	         }
	      }
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
