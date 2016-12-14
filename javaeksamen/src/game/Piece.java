package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Color;


public class Piece {
	
	private final static int DIMENSION = 50;
		
	public void draw(Graphics g, int cx, int cy)
	{
		
	}
	
	public static boolean contains(int x, int y, int cx, int cy)
	{
		return (cx - x) * (cx - x) + (cy - y) * (cy - y) < DIMENSION / 2 * DIMENSION / 2;
	}
   
	public static int getDimension()
	{
		return DIMENSION;
	}
	
	public static enum types {
		EMPTY,   
		BLACK_REGULAR,
		BLACK_KING,
		WHITE_REGULAR,
		WHITE_KING		
	}
	
	
	   
}

