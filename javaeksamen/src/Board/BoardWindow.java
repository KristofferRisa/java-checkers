package Board;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class BoardWindow extends JFrame {
	public BoardWindow () {
		setTitle ("Dirty Checkers");
		add (new SquareMatrix());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		
		setSize(height/2, width/4);
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
}
