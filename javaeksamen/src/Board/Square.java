package board;

import java.awt.Color;
import javax.swing.JPanel;

public class Square extends JPanel{
	
	public Square(int p) {
		position = p;
		setBackground(calculateColor(position));
		
	}
	
	private Color calculateColor(int p) {
			boolean annenhverRute = (p % 2 == 0);
			boolean annenhverRad = (p / 8) % 2 == 0;
			//return annenhverRute ? Color.black : Color.white;
			return (annenhverRute != annenhverRad ? Color.decode("#FFE4B5"):Color.decode("#DEB887"));
	}
	
	private int position;	
	
	private static final long serialVersionUID = 7926042768479192097L;

}
