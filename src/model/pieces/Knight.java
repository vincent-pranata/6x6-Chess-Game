package model.pieces;


import model.enumeration.Colour;
import model.enumeration.PieceType;

public class Knight extends Piece{

	public Knight(Colour colour, int x, int y) {
		super(colour, x, y);
		pieceType = PieceType.KNIGHT;
	}

	 public boolean isValid(int x, int y) {
	        if(super.isValid(x, y)==false){
	            return false;
	        }
	        int diffX=Math.abs(this.x-x);
	        int diffY=Math.abs(this.y-y);
	        if ((diffX+diffY)==3 && diffX!=0 && diffY!=0) {
	        return true;
	        }
	        return false;
	    }

}
