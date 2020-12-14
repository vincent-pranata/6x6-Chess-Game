package model.pieces;


import model.enumeration.Colour;
import model.enumeration.PieceType;

public class Rook extends Piece{

	public Rook(Colour colour, int x, int y) {
		super(colour, x, y);
		pieceType = PieceType.ROOK;
	}
	
	public boolean isValid(int x,int y) {
		if(super.isValid(x,y) == false) {
            return false;
            }
		else if(x == this.x && Math.abs(y-this.y) <= 2 ) {
            return true;
            }
		else if(y == this.y&& Math.abs(x-this.x) <= 2 ) {
            return true;
        }
		return false;
	}


}
