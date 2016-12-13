package graphics.usercontrol;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

import game.board.Piece;
import game.board.Square;

public class SquarePanel extends JPanel {

	public SquarePanel() {
		setLayout (new GridLayout (8,8));
		setPreferredSize(new Dimension(400,400));
		for (int i=0; i<64; i++) {
			add (new Square(i));
		}
	}
	
	private static final long serialVersionUID = 25062131082510728L;

}
