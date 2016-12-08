package Board;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class BoardWindow extends JPanel {

	public BoardWindow () {
		add (new SquareMatrix());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize(height, width/4);
		setVisible(true);		
	}

	private static final long serialVersionUID = 2897347750559652886L;
}
