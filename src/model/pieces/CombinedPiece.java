package model.pieces;

import model.enumeration.PieceType;

public class CombinedPiece extends Piece
{
	private Piece[] pieces = new Piece[2];
	
	public CombinedPiece(Piece selectPiece, Piece movePiece) 
	{
		super(movePiece.getColour(), movePiece.getX(), movePiece.getY());
		pieces[0] = selectPiece;
		pieces[1] = movePiece;
		pieceType = PieceType.COMBINED;
	}
	
	@Override
	public boolean isValid(int x, int y)
	{
		return pieces[0].isValid(x, y) || pieces[1].isValid(x, y);
	}
	
	@Override
	public boolean move(int x, int y)
	{
		if(super.move(x, y))
		{
			pieces[0].x = x;
			pieces[0].y = y;
			pieces[1].x = x;
			pieces[1].y = y;
			return true;
		}
		return false;
	}
	
	public Piece[] getPieces()
	{
		return pieces;
	}
}
