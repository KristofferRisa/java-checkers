//BOARDPANEL

package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import datamodels.GameDataDTO;
import game.CheckerType;
import game.Move;
import game.Piece;
import game.PostionValidator;
import network.Client;
import network.Server;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = -3323794800676139078L;
	private Client client;
	private Server server;
	private static GameDataDTO gameData;

	// dimension of checkerboard square (25% bigger than checker)
	private final static int SQUAREDIM = (int) (Piece.getDimension() * 1.25);
	// dimension of checkerboard (width of 8 squares)
	private final int BOARDDIM = 8 * SQUAREDIM;
	// preferred size of Board component
	private Dimension dimPrefSize;
	// dragging flag -- set to true when user presses mouse button over checker
	// and cleared to false when user releases mouse button
	// private Move move;
	// reference to positioned checker at start of drag
	// private PostionValidator postionValidator;

	// list of Checker objects and their initial positions
	// private List<PostionValidator> pieces;s

	public BoardPanel(Server server, Client client) {
		if (server != null) {
			gameData = new GameDataDTO();
			gameData.pieces = new ArrayList<>();
			gameData.move = new Move();
			addPieces();
			//setUpGame();
		}

		this.server = server;
		this.client = client;

		// SquarePanel squarePanel = new SquarePanel();
		// add(squarePanel);
		setVisible(true);

		dimPrefSize = new Dimension(BOARDDIM, BOARDDIM);

		if (server == null) {
			gameData = client.recive();
			repaint();
		} else {
			server.client.send(gameData);
		}

		addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent me) {
				// Obtain mouse coordinates at time of press.
				if((server != null && gameData.clientIdTurn == 1)
						|| (server == null && gameData.clientIdTurn == 2)){
					int x = me.getX();
					int y = me.getY();
					
					gameData.move.oldPostionCol = x/SQUAREDIM;
					gameData.move.oldPostionRow = y/SQUAREDIM;
					
					System.out.println("Current COL=" + gameData.move.oldPostionCol +" ROW=" + gameData.move.oldPostionRow + "(pos: X= " + x + " Y="+y + ")");
					
					// Locate positioned checker under mouse press.
					for (PostionValidator _pv : gameData.pieces){
						if (Piece.contains(x, y, _pv.cx, _pv.cy)) {
							gameData.postionValidator = _pv;
							gameData.move.oldcx = _pv.cx;
							gameData.move.oldcy = _pv.cy;
							gameData.move.deltax = x - _pv.cx;
							gameData.move.deltay = y - _pv.cy;
							gameData.move.isMoving = true;
							return;
						}
					}					
				}			
			}



			@Override
			public void mouseReleased(MouseEvent me) {
				// When mouse released, clear inDrag (to
				// indicate no drag in progress) if inDrag is
				// already set.
				if((server != null && gameData.clientIdTurn == 1)
						|| (server == null && gameData.clientIdTurn == 2)){
					int x = me.getX();
					int y = me.getY();
					
					System.out.println("Current COL=" + x/SQUAREDIM +" ROW=" + y/SQUAREDIM + "(pos: X= " + x + " Y="+y + ")");
	
					if (gameData.move.isMoving)
						gameData.move.isMoving = false;
					else
						return;
	
					// Snap checker to center of square.			
					gameData.postionValidator.cx = (x - gameData.move.deltax) / SQUAREDIM * SQUAREDIM + SQUAREDIM / 2;
					gameData.postionValidator.cy = (y - gameData.move.deltay) / SQUAREDIM * SQUAREDIM + SQUAREDIM / 2;
	
					// Do not move checker onto an occupied square.
	
					for (PostionValidator posCheck : gameData.pieces)
						if (posCheck != gameData.postionValidator 
							&& posCheck.cx == gameData.postionValidator.cx
								&& posCheck.cy == gameData.postionValidator.cy) {
							gameData.postionValidator.cx = gameData.move.oldcx;
							gameData.postionValidator.cy = gameData.move.oldcy;
						}
					
					gameData.postionValidator = null;
					
					
					if(gameData.clientIdTurn == 2 && server == null ){
						gameData.clientIdTurn = 1;
						client.send(gameData);
					}
					if(gameData.clientIdTurn == 1 && server != null){
						gameData.clientIdTurn = 2;
						server.client.send(gameData);
					}
					repaint();
				}
				
			}


			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		// Attach a mouse motion listener to the applet. That listener listens
		// for mouse drag events.

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent me) {
				if (gameData.move.isMoving) {
					// Update location of checker center.

					gameData.postionValidator.cx = me.getX() - gameData.move.deltax;
					gameData.postionValidator.cy = me.getY() - gameData.move.deltay;

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

		if (row < 1 || row > 8) {
			throw new IllegalArgumentException("row out of range: " + row);
		}

		if (col < 1 || col > 8) {
			throw new IllegalArgumentException("col out of range: " + col);
		}

		PostionValidator pv = new PostionValidator();
		pv.piece = piece;
		pv.cx = (col - 1) * SQUAREDIM + SQUAREDIM / 2;
		pv.cy = (row - 1) * SQUAREDIM + SQUAREDIM / 2;

		for (PostionValidator _validator : gameData.pieces) {
			if (pv.cx == _validator.cx && pv.cy == _validator.cy) {
				try {
					throw new Exception("square at (" + row + "," + col + ") is occupied");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		gameData.pieces.add(pv);
	}

//	public void setUpGame() {
//         // Set up the board with checkers in position for the beginning
//         // of a game.  Note that checkers can only be found in squares
//         // that satisfy  row % 2 == col % 2.  At the start of the game,
//         // all such squares in the first three rows contain black squares
//         // and all such squares in the last three rows contain red squares.
//     for (int row = 0; row < 8; row++) {
//        for (int col = 0; col < 8; col++) {
//           if ( row % 2 == col % 2 ) {
//              if (row < 3 ) {
//                 [row][col] = CheckerType.BLACK_REGULAR;
//                 
//              else if (row > 4)
//                 [row][col] = CheckerType.WHITE_REGULAR;
//              else
//                 board[][col] = CheckerType.EMPTY;
//           }
//           else {
//              [row][col] = CheckerType.EMPTY;
//           }
//        }
//     }
	  // end
																																																														// setUpGame()

	@Override
	public Dimension getPreferredSize() {
		return dimPrefSize;
	}

	@Override
	protected void paintComponent(Graphics g) {

		paintCheckerBoard(g);
		
		if(gameData.clientIdTurn ==1 && server == null ){
			gameData = client.recive();
		}
				
		if(gameData.clientIdTurn == 2 && server != null){
			 gameData = server.client.recive();
		}
		
		if(gameData != null && gameData.pieces != null){
			for (PostionValidator _move : gameData.pieces){
				if (_move != gameData.postionValidator){
					_move.piece.draw(g, _move.cx, _move.cy);
				}					
			}
				
			// Draw dragged checker last so that it appears over any underlying
			// checker.
			if (gameData.postionValidator != null){
				gameData.postionValidator.piece.draw(g, gameData.postionValidator.cx, gameData.postionValidator.cy);
			}		
			
		}	
		
	}

	private void paintCheckerBoard(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Paint checkerboard.

		for (int row = 0; row < 8; row++) {
			g.setColor(((row & 1) != 0) ? Color.DARK_GRAY : Color.LIGHT_GRAY);
			for (int col = 0; col < 8; col++) {
				g.fillRect(col * SQUAREDIM, row * SQUAREDIM, SQUAREDIM, SQUAREDIM);
				g.setColor((g.getColor() == Color.DARK_GRAY) ? Color.LIGHT_GRAY : Color.DARK_GRAY);
			}
		}
	}

	private void addPieces() {

		// Black pieces
		for (int i = 1; i <= 8; i++) {

			if (i % 2 == 0) {
				add(new Piece(CheckerType.BLACK_REGULAR), 1, i);
			}
		}

		for (int i = 1; i <= 8; i++) {

			if (i % 2 != 0) {
				add(new Piece(CheckerType.BLACK_REGULAR), 2, i);
			}

		}

		// White pieces
		for (int i = 1; i <= 8; i++) {

			if (i % 2 == 0) {
				add(new Piece(CheckerType.WHITE_REGULAR), 7, i);
			}
		}

		for (int i = 1; i <= 8; i++) {

			if (i % 2 != 0) {
				add(new Piece(CheckerType.WHITE_REGULAR), 8, i);

			}
		}

	}

}
