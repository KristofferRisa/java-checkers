package board;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

import board.Square;

public class SquareMatrix extends JPanel {

	public SquareMatrix() {
		setLayout (new GridLayout (8,8));
		setPreferredSize(new Dimension(600,600));
		for (int i=0; i<64; i++) {
			add (new Square(i));
		}
	}
	
	private static final long serialVersionUID = 25062131082510728L;

}
