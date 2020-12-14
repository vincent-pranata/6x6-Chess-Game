package model;

import java.util.ArrayList;
import java.util.List;

import model.enumeration.Colour;
import model.pieces.Bishop;
import model.pieces.CombinedPiece;
import model.pieces.Knight;
import model.pieces.Piece;
import model.pieces.Rook;


public class Board 
{
	private Square[][] squares;
	private List<Piece> pieces = new ArrayList<Piece>();
	
	// Constants
	private final int BOARD_SIZE = 6;
	
	public Board()
	{
		createSquares();
		createPieces();
	}
	
	private void createSquares()
	{
		squares = new Square[BOARD_SIZE][BOARD_SIZE];
		
		for(int y = 0; y < BOARD_SIZE; y++)
		{
			for(int x = 0; x < BOARD_SIZE; x++)
			{
				squares[x][y] = new Square(x,y);				
			}
		}
	}
	
	private void createPieces()
	{
		// Black pieces
		Piece piece1_1 = new Rook(Colour.BLACK, 0, 0);
		pieces.add(piece1_1);
		squares[0][0].setPiece(piece1_1);
		
		Piece piece2_1 = new Bishop(Colour.BLACK, 1,0 );
		pieces.add(piece2_1);
		squares[1][0].setPiece(piece2_1);

		Piece piece3_1 = new Knight(Colour.BLACK,2,0);
		pieces.add(piece3_1);
		squares[2][0].setPiece(piece3_1);
		
		Piece piece4_1 = new Knight(Colour.BLACK,3,0);
		pieces.add(piece4_1);
		squares[3][0].setPiece(piece4_1);
		
		Piece piece5_1 = new Bishop(Colour.BLACK,4,0);
		pieces.add(piece5_1);
		squares[4][0].setPiece(piece5_1);
		
		Piece piece6_1 = new Rook(Colour.BLACK,5,0);
		pieces.add(piece6_1);
		squares[5][0].setPiece(piece6_1);
		
		
		// White pieces
		Piece piece1_2 = new Rook(Colour.WHITE,0,5);
		pieces.add(piece1_2);
		squares[0][5].setPiece(piece1_2);
		
		Piece piece2_2 = new Bishop(Colour.WHITE,1,5);
		pieces.add(piece2_2);
		squares[1][5].setPiece(piece2_2);

		Piece piece3_2 = new Knight(Colour.WHITE,2,5);
		pieces.add(piece3_2);
		squares[2][5].setPiece(piece3_2);
		
		Piece piece4_2 = new Knight(Colour.WHITE,3,5);
		pieces.add(piece4_2);
		squares[3][5].setPiece(piece4_2);
		
		Piece piece5_2 = new Bishop(Colour.WHITE,4,5);
		pieces.add(piece5_2);
		squares[4][5].setPiece(piece5_2);
		
		Piece piece6_2 = new Rook(Colour.WHITE,5,5);
		pieces.add(piece6_2);
		squares[5][5].setPiece(piece6_2);
		
	}

	public Square getSquare(int xPos, int yPos)
	{
		for(int x = 0; x < BOARD_SIZE; x++)
		{
			for(int y = 0; y < BOARD_SIZE; y++)
			{
				Square square = squares[x][y];
				if(square.getX() == xPos && square.getY() == yPos)
				{
					return square;
				}
			}
		}
		return null;
	}
	
	public Piece getPiece(int x, int y)
	{
		return getSquare(x,y).getPiece();
	}
	
	public void removePiece(Piece piece)
	{
		pieces.remove(piece);
	}
	
	public void addPiece(Piece piece)
	{
		pieces.add(piece);
	}
	
	public void movePiece(Piece piece, int xPos, int yPos)
	{
		Square currentSquare = getSquare(piece.getX(), piece.getY());
		Square moveSquare = getSquare(xPos, yPos);
		
		// Moving piece
		Piece movePiece = getPiece(xPos, yPos);
		if(movePiece != null && movePiece.getColour() == piece.getColour())
		{
			piece = combinePiece(piece, movePiece);
		}
		
		currentSquare.removePiece(); // Removes piece from old square
		moveSquare.setPiece(piece); // Adds piece to new square
		piece.move(xPos, yPos);
	}
	
	private Piece combinePiece(Piece piece, Piece movePiece) 
	{
		Piece combinedPiece = new CombinedPiece(piece, movePiece);
		
		addPiece(combinedPiece);
		removePiece(piece);
		removePiece(movePiece);
		return combinedPiece;
	}

	// Checks if piece exists based on it's x, y and team colour
	public boolean pieceExists(int xPos, int yPos, Colour teamColour)
	{
		boolean inBounds = xPos <= 5 && xPos >=0 && yPos <= 5 && yPos >=0;
		if(inBounds)
		{
			Piece piece = getSquare(xPos,yPos).getPiece();
			if(piece != null)
			{
				return teamColour == piece.getColour();
			}
		}
		return false;
	}
	
	// Checks if a player has lost
	public boolean isGameOver()
	{
		int blackPieceAlive = 0;
		int whitePieceAlive = 0;
		for(Piece piece:pieces)
		{
			if(piece.getColour() == Colour.WHITE)
			{
				whitePieceAlive++;
			}
			else
			{
				blackPieceAlive++;
			}
		}
		
		// If player runs out of pieces then game is over
		if(blackPieceAlive == 0 || whitePieceAlive == 0)
		{
			return true;
		}
		return false;
	}

	public Square[][] getSquares() 
	{
		return squares;
	}
}
