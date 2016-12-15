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
import java.util.List;

import javax.swing.JPanel;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import datamodels.GameDataDTO;
import game.CheckerType;
import game.Move;
import game.Piece;
import game.PostionValidator;
import network.Client;
import network.ClientManager;
import network.Server;

public class BoardPanel extends JPanel {
	
	public GameDataDTO data;
	private Client client;
	
	public BoardPanel(Client client) {
		// SquarePanel squarePanel = new SquarePanel();
		// add(squarePanel);
		setVisible(true);

		this.client = client;
		dimPrefSize = new Dimension(BOARDDIM, BOARDDIM);
		
		move = new Move();
		
		
		addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent me) {
				// Obtain mouse coordinates at time of press.

				int x = me.getX();
				int y = me.getY();
				
				move.oldPostionCol = x/SQUAREDIM;
				move.oldPostionRow = y/SQUAREDIM;
				
				System.out.println("BoardPanel: clientId = " + data.clientId);
				System.out.println("BoardPanel: turnId = " + data.clientIdTurn);
				
				System.out.println("Current COL=" + move.oldPostionCol +" ROW=" + move.oldPostionRow + "(pos: X= " + x + " Y="+y + ")");
				
				// Locate positioned checker under mouse press.
				for (PostionValidator _pv : pieces){
					if (Piece.contains(x, y, _pv.cx, _pv.cy)) {
						BoardPanel.this.postionValidator = _pv;
						move.oldcx = _pv.cx;
						move.oldcy = _pv.cy;
						move.deltax = x - _pv.cx;
						move.deltay = y - _pv.cy;
						move.isMoving = true;
						return;
					}
				}
					
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				// When mouse released, clear inDrag (to
				// indicate no drag in progress) if inDrag is
				// already set.
				int x = me.getX();
				int y = me.getY();
				
				System.out.println("Current COL=" + x/SQUAREDIM +" ROW=" + y/SQUAREDIM + "(pos: X= " + x + " Y="+y + ")");
				
				if (move.isMoving) {
					if (data == null || data.clientId != data.clientIdTurn) {
						System.err.println("data er null eller clientId er ulik turnid");
						System.err.println("Data er: " + data);
						//System.err.println("ClientId er:" + data.clientId);
						//System.err.println("ClientIdTurn:" +data.clientIdTurn);
						//System.out.println(data.clientIdTurn);
						
						BoardPanel.this.postionValidator.cx = move.oldcx;
						BoardPanel.this.postionValidator.cy = move.oldcy;
					
						move.isMoving = false;
						postionValidator = null;
						
						data.pieces = pieces;
						data.postionValidator = BoardPanel.this.postionValidator;
						data.setMove(move);
						
						client.send(data);
						
						repaint();
						
						return;
					}
					move.isMoving = false;
				}
					
				else
					return;

				// Snap checker to center of square.			
				postionValidator.cx = (x - move.deltax) / SQUAREDIM * SQUAREDIM + SQUAREDIM / 2;
				postionValidator.cy = (y - move.deltay) / SQUAREDIM * SQUAREDIM + SQUAREDIM / 2;

				//data = null;
				
				//data = client.recive();

				data.pieces = pieces;
				data.postionValidator = BoardPanel.this.postionValidator;
				data.setMove(move);
				
				client.send(data);
				
				repaint();
							
				
				
//				paintComponent(getGraphics());
//				data.pieces = pieces;
//				data.postionValidator = BoardPanel.this.postionValidator;
//				data.setMove(move);
//				
//				client.send(data);
				
//				repaint();
				
				//MOTTA OK eller FEIL!
				//				data = client.recive();
				
				
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
				if (move.isMoving) {
					// Update location of checker center.

					postionValidator.cx = me.getX() - move.deltax;
					postionValidator.cy = me.getY() - move.deltay;
										
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
		
		if (row < 1 || row > 8){
			throw new IllegalArgumentException("row out of range: " + row);
		}
			
		if (col < 1 || col > 8){
			throw new IllegalArgumentException("col out of range: " + col);
		}
			
		PostionValidator pv = new PostionValidator();
		pv.piece = piece;
		pv.cx = (col - 1) * SQUAREDIM + SQUAREDIM / 2;
		pv.cy = (row - 1) * SQUAREDIM + SQUAREDIM / 2;
		
		for (PostionValidator _validator : pieces){
			if (pv.cx == _validator.cx && pv.cy == _validator.cy){
				try {
					throw new Exception("square at (" + row + "," + col + ") is occupied");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}			
				
		pieces.add(pv);
	}

	@Override
	public Dimension getPreferredSize() {
		return dimPrefSize;
	}

	@Override
	protected void paintComponent(Graphics g) {

		paintCheckerBoard(g);
	
		for (PostionValidator _move : pieces){
			if (_move != BoardPanel.this.postionValidator){
				_move.piece.draw(g, _move.cx, _move.cy);
			}					
		}
			
		// Draw dragged checker last so that it appears over any underlying
		// checker.
		if (postionValidator != null){
			postionValidator.piece.draw(g, postionValidator.cx, postionValidator.cy);
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
			
			//Black pieces			
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
			
			//White pieces
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
	
	// dimension of checkerboard square (25% bigger than checker)
	private final static int SQUAREDIM = (int) (Piece.getDimension() * 1.25);
	// dimension of checkerboard (width of 8 squares)
	private final int BOARDDIM = 8 * SQUAREDIM;
	// preferred size of Board component
	private Dimension dimPrefSize;
	// dragging flag -- set to true when user presses mouse button over checker
	// and cleared to false when user releases mouse button
	private Move move;
	// reference to positioned checker at start of drag
	private PostionValidator postionValidator;
	
	// list of Checker objects and their initial positions
	private List<PostionValidator> pieces;
	
	public void refresh(GameDataDTO dataFromServer){
		
		if(dataFromServer.postionValidator != null){

			BoardPanel.this.postionValidator = dataFromServer.postionValidator;
				
		}
		
		pieces = dataFromServer.pieces;
		
		if(dataFromServer != null){
			data = dataFromServer;
		}
		
		
		BoardPanel.this.postionValidator = data.postionValidator;
		postionValidator = data.postionValidator;
		// Do not move checker onto an occupied square.
		
//			paintComponent(getGraphics());
		repaint();
	}
	
}



