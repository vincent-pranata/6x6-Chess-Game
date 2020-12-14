package model.pieces;


import model.enumeration.Colour;
import model.enumeration.PieceType;

public class Bishop	extends Piece {
	
	
	
	
	
public Bishop(Colour colour, int x, int y) {
		super(colour,x,y);
		pieceType = PieceType.BISHOP;
	}

public boolean isValid(int x ,int y) {
		
	
		if(super.isValid(x,y)==false) {
			return false;
		}
		else if(Math.abs(x-this.x)==Math.abs(y-this.y) && Math.abs(x - this.x) <= 2) {
			return true;
		}
           
		return false;
		
	}



}



