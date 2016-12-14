package graphics;

import javax.swing.JPanel;

import game.CheckerType;
import game.Piece;

public class BoardPanel extends JPanel {

	private SquarePanel squares;

	public BoardPanel() {
		squares = new SquarePanel();
		
		add(squares);
		
		postBlackBricks();
		postHviteBricks();
				
	}	

	private void postBlackBricks() {
		
		for (int i = 1; i <= 8; i++) {

			if (i % 2 == 0) {
				squares.add(new Piece(CheckerType.BLACK_REGULAR), 1, i);
			}
		}
		
		for (int i = 1; i <= 8; i++) {

			if (i % 2 != 0) {
				squares.add(new Piece(CheckerType.BLACK_REGULAR), 2, i);
			}
			
		}
	}
	
	private void postHviteBricks() {
		
		for (int i = 1; i <= 8; i++) {

			if (i % 2 == 0) {
				squares.add(new Piece(CheckerType.WHITE_REGULAR), 7, i);
			}
		}
		
		for (int i = 1; i <= 8; i++) {

			if (i % 2 != 0) {
				squares.add(new Piece(CheckerType.WHITE_REGULAR), 8, i);
			}
			
		}
	}
}
