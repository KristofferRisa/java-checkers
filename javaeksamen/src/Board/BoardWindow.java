package Board;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;

import sun.applet.Main;

public class BoardWindow extends JPanel {
	
	
	public BoardWindow () {
		add (new SquareMatrix());
		setVisible(true);		
	}

	private static final long serialVersionUID = 2897347750559652886L;
}
