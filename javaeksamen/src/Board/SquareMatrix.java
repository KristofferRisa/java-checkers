package Board;

import java.awt.GridLayout;

import javax.swing.JPanel;

import Board.Square;

public class SquareMatrix extends JPanel {

		public SquareMatrix() {
			setLayout (new GridLayout (8,8));
			for (int i=0; i<64; i++) {
				add (new Square(i));
			}
		}
}
