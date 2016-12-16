package checkers.graphics;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import cherckers.game.Square;


public class SquarePanel extends JPanel {

	private static final long serialVersionUID = 6159152699386482661L;

	public SquarePanel() {
		
		setLayout (new GridLayout (8,8));
		setPreferredSize(new Dimension(400,400));
		
		int i = 0;
		for(int x = 0; x <  8; x++){
			for(int y = 0; y < 8; y++){
				add(new Square(x,y,i));
				i++;
			}
		}

	}
	

}
