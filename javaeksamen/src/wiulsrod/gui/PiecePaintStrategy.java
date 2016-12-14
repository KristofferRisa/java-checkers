package wiulsrod.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import wiulsrod.model.Piece;

public interface PiecePaintStrategy {

    void draw(int cx, int cy, Piece piece, Graphics g, int squareSize, int pieceSize);

    Color getColorForPoint(Point point);

}
