package Board;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Square extends JPanel{
	
	private int position;
	

	public Square(int position) {
		this.position = position;
		setBackground(calculateColor(position));
		
	}

	private Color calculateColor(int p) {
			boolean annenhverRute = (p % 2 == 0);
			boolean annenhverRad = (p / 8) % 2 == 0;
			//return annenhverRute ? Color.black : Color.white;
			return (annenhverRute != annenhverRad ? Color.decode("#FFE4B5"):Color.decode("#DEB887"));
		}
		
}
