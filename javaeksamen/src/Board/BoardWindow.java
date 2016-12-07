package Board;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardWindow extends JPanel {
	public BoardWindow () {
		add (new SquareMatrix());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		
		setSize(height/2, width/4);
		setVisible(true);		
	}
}
