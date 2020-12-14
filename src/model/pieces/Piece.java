package model.pieces;



import model.enumeration.Colour;
import model.enumeration.PieceType;


public abstract class Piece {
	
	private Colour colour;
	protected int x;
	protected int y;
	private  boolean isAvailable;
	protected PieceType pieceType;
	
	public Piece(Colour colour, int x, int y)
	{
		this.colour=colour;
		this.setAvailable(true);
		this.x = x;
		this.y = y;
	}
	

	
	//to cheak the move is valid
	public boolean isValid(int x, int y) 
	{
		
		if(x==this.x&&y==this.y) {
			return false;
		}
		if(x < 0 || x > 5 || this.x < 0 || this.x> 5 || y < 0 
				  || y > 5 || this.y <0 || this.y > 5) {
			return false;
		}
		return true;
	}
	
	//set the piece dead or alive
	public void setAvailable(boolean avaliable)
	{
		isAvailable=avaliable;
	}
	
	//get piece dead or alive
	public boolean getAvailable() {
		return this.isAvailable;
	}
	
	public Colour getColour()
	{
		return colour;
	}
	
	//actual move
	public boolean move(int x, int y) {
		if(this.isValid(x,y)) {
			this.x=x;
			this.y=y;
			return true;
		}
		else {
			return false;
		}
	}

	public int getX() 
	{	
		return x;
	}

	public int getY() 
	{	
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public PieceType getPieceType()
	{
		return pieceType;
	}

}
