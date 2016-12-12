package graphics;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;

import board.SquareMatrix;
import sun.applet.Main;

public class BoardPanel extends JPanel {
	
	
	public BoardPanel () {
		add (new SquareMatrix());
		setVisible(true);		
	}

	private static final long serialVersionUID = 2897347750559652886L;
}
