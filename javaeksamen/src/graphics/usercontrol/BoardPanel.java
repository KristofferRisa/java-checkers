package graphics.usercontrol;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;

import sun.applet.Main;

public class BoardPanel extends JPanel {
	
	public BoardPanel () {
		add (new SquarePanel());
		setVisible(true);		
	}

	private static final long serialVersionUID = 2897347750559652886L;
}
