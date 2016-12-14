package game;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Square extends JPanel {
	
	public Square(int x, int y,int postion) {
		this.position = postion;
		this.x = x;
		this.y = y;
		setBackground(calculateColor(position));
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Square X = " + x);
				System.out.println("Square y = " + y);
			}
		});
	}

	private Color calculateColor(int p) {
			boolean annenhverRute = (p % 2 == 0);
			boolean annenhverRad = (p / 8) % 2 == 0;
			//return annenhverRute ? Color.black : Color.white;
			return (annenhverRute != annenhverRad ? Color.decode("#FFE4B5"):Color.decode("#DEB887"));
	}
	
	private int position;	
	
	public int x;
	public int y;
	
	private static final long serialVersionUID = 7926042768479192097L;

}
