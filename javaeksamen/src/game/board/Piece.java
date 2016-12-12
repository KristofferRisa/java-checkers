package game.board;

import java.awt.Color;
import java.awt.Graphics;


public class Piece {
	
	public final static int DIMENSION = 50;
	
	private CheckerType checkerType;
	
	public Piece(CheckerType checkerType)
	{
		this.checkerType = checkerType;
	}
	
	public void draw(Graphics g, int cx, int cy)
	{
		int x = cx - DIMENSION / 2;
		int y = cy - DIMENSION / 2;
		
		g.setColor(checkerType == CheckerType.BLACK_REGULAR || 
				checkerType == CheckerType.BLACK_KING ? (Color) Color.BLACK :
				Color.RED);
		
		
		g.fillOval(x, y, DIMENSION, DIMENSION);
		g.setColor(Color.WHITE);
		g.drawOval(x, y, DIMENSION, DIMENSION);
		
	    if (checkerType == CheckerType.RED_KING || 
	    		checkerType == CheckerType.BLACK_KING)
	            g.drawString("K", cx, cy);
	}
	
	   public static boolean contains(int x, int y, int cx, int cy)
	   {
	      return (cx - x) * (cx - x) + (cy - y) * (cy - y) < DIMENSION / 2 * 
	             DIMENSION / 2;
	   }
	   
	   public static int getDimension()
	   {
	      return DIMENSION;
	   }
	   
}

