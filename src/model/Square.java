package model;

import model.enumeration.Colour;
import model.pieces.Piece;

public class Square 
{
	private int x; // x position of square
	private int y; // y position of square
	private Colour colour;
	private Piece currentPiece = null; // current piece on square
	
	public Square(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setPiece(Piece piece)
	{
		currentPiece = piece;
	}
	
	public void removePiece()
	{
		currentPiece = null;
	}
	
	public Piece getPiece()
	{
		return currentPiece;
	}
	
	public Piece getSquare()
	{
		return currentPiece;
	}
}
