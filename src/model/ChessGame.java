package model;

import java.util.ArrayList;
import java.util.Collection;

import model.enumeration.Colour;
import model.enumeration.PieceType;
import model.pieces.CombinedPiece;
import model.pieces.Piece;


public class ChessGame 
{
	private Player[] players = new Player[2];
	private Player winner;
	private Board board;
	private int turnCount = 1; 
	private int maxMoves; // Maximum amount of of moves before game ends
	private Piece selectedPiece; // Piece that is currently selected by the player
	
	// State variables
	private int gameState;
	public final static int STARTING = 0;
	public final static int SELECT_STATE = 1;
	public final static int MOVE_STATE = 2;
	public final static int GAME_OVER = 3;
	private final int PIECE_WORTH = 5;
	private final int COMBINED_PIECE_WORTH = 10;
	
	public ChessGame(Player player1, Player player2)
	{
		board = new Board();
	
		players[0] = player1;
		players[1] = player2;
		
		gameState = STARTING;
	}	
	
	// Player selects piece on (x,y)
	public boolean selectPiece(int selectX, int selectY)
	{
		if(gameState == SELECT_STATE)
		{
			Colour currentTurn = getColourTurn();
			if(board.pieceExists(selectX, selectY, currentTurn))
			{
				selectedPiece = board.getPiece(selectX, selectY);
				gameState = MOVE_STATE;
				return true;
			}
		}
		return false;
	}
	
	// Moves selected piece to (x,y)
	public boolean movePiece(int moveX, int moveY)
	{
		if(gameState == MOVE_STATE)
		{
			if(isValidMove(selectedPiece, moveX,moveY))
			{
				Piece pieceOnSquare = board.getPiece(moveX,moveY);
				if(pieceOnSquare != null && selectedPiece.getColour() != pieceOnSquare.getColour())
				{
					capturePiece(pieceOnSquare);
				}
				board.movePiece(selectedPiece, moveX, moveY);
				deselectPiece();
				turnCount++;
				checkGameOver();
				return true;
			}
		}
		return false;
	}
	
	public boolean seperateMove(int moveX, int moveY)
	{
		if(gameState == MOVE_STATE)
		{
			if(selectedPiece.getPieceType() == PieceType.COMBINED)
			{
				int selectedX = selectedPiece.getX();
				int selectedY = selectedPiece.getY();
				Piece[] pieces = ((CombinedPiece) selectedPiece).getPieces();
				
				int pieceMoveIndex = pieces[0].isValid(moveX, moveY)?0:1; // Check which piece is being moved
				
				if(movePiece(moveX, moveY))
				{
					// Updates the squares to the new pieces
					pieces[1-pieceMoveIndex].setX(selectedX);
					pieces[1-pieceMoveIndex].setY(selectedY);
					
					board.getSquare(moveX, moveY).setPiece(pieces[pieceMoveIndex]);
					board.getSquare(selectedX, selectedY).setPiece(pieces[1-pieceMoveIndex]);
					
					// Updates the pieces stored in board
					board.removePiece(selectedPiece);
					board.addPiece(pieces[0]);
					board.addPiece(pieces[1]);
					return true;
				}
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	public void deselectPiece()
	{
		if(gameState == MOVE_STATE)
		{
			selectedPiece = null;
			gameState = SELECT_STATE;
		}
	}
	
	// Get's all the possible moves of a piece through a collection of squares
	public Collection<Square> getPossibleMove() 
	{
		ArrayList<Square> possibleMoves = new ArrayList<Square>();
		
		// Checks each square if move is valid
		for(int x=0;x<6;x++) 
		{
			for(int y=0;y<6;y++) 
			{
				if(isValidMove(selectedPiece, x, y))
				{
					possibleMoves.add(board.getSquare(x,y));
				}
			}
		}
		return possibleMoves;
	}
	
	public boolean setMaxMoves(int maxMoves)
	{
		if(gameState == STARTING)
		{
			if(maxMoves > 0)
			{
				this.maxMoves = maxMoves;
				gameState = SELECT_STATE;
				return true;
			}
		}
		return false;
	}
	
	public Player getWinner()
	{
		if(players[0].getPoints()>players[1].getPoints())
		{
			winner=players[0];
		}
		else if(players[1].getPoints()>players[0].getPoints())
		{
			winner=players[1];
		}
		else
		{
			winner=null;
		}
		return winner;
	}
	
	public String toString()
	{
		return String.format("\nWinner\nUsername: %s\nPoints: %s", winner.getUserName(), winner.getPoints());		
	}
	
	// Returns true if the game is over
	public boolean isGameOver()
	{
		if(gameState == GAME_OVER)
		{
			return true;
		}
		return false;
	}
	
	// Checks if game is over
	private boolean checkGameOver()
	{
		if(isGameOver())
		{
			return true;
		}
		else if(board.isGameOver() || turnCount > maxMoves)
		{
			gameState = GAME_OVER;
			return true;
		}
		return false;
	}
	
	// Checks if move is invalid
	private boolean isValidMove(Piece piece, int x, int y)
	{
		boolean inBounds = x <= 5 && x >=0 && y <= 5 && y >=0;
		if(inBounds)
		{
			PieceType pieceType = piece.getPieceType();
			if(!piece.isValid(x,y))
			{
				return false;
			}
			else if(board.getSquare(x,y).getPiece() != null && piece.getColour() == board.getSquare(x,y).getPiece().getColour())
			{
				if (pieceType == PieceType.COMBINED) 
				{
					return false;
				}
				else if(!canCombine(board.getSquare(x,y).getPiece()))
				{
					return false;
				}
			}
			else if(pieceType == PieceType.COMBINED)
			{
				Piece[] pieces = ((CombinedPiece)selectedPiece).getPieces();
				if(!(isValidMove(pieces[0], x, y) || isValidMove(pieces[1], x, y)))
				{
					return false;
				}
			}
			
			if(detectCollision(x, y, pieceType))
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		return true;
	}
	
	private boolean canCombine(Piece piece) 
	{
		if(piece.getColour() == selectedPiece.getColour())
		{
			PieceType pieceType1 = piece.getPieceType();
			PieceType pieceType2 = selectedPiece.getPieceType();
			if(pieceType1 != pieceType2 && pieceType1 != PieceType.COMBINED && pieceType2 != PieceType.COMBINED)
			{
				return true;
			}
		}
		return false;
	}

	private boolean detectCollision(int moveX, int moveY, PieceType type)
	{
		if(type == PieceType.BISHOP)
		{
			return bishopCollide(moveX, moveY);
		}
		else if(type == PieceType.ROOK)
		{
			return rookCollide(moveX, moveY);
		}
		return false;
	}
	
	// Rook collision detection
	private boolean bishopCollide(int moveX, int moveY) 
	{
		int directionX;
		int directionY;
		int selectX = selectedPiece.getX();
		int selectY = selectedPiece.getY();
		
		// Finds the direction at which the bishop is moving
		if(moveX > selectX)
		{
			directionX = 1;
		}
		else
		{
			directionX = -1;
		}
		
		if(moveY > selectY)
		{
			directionY = 1;
		}
		else
		{
			directionY = -1;
		}
		
		// Checks if there is a piece between
		int amtOfSquareBetween = Math.abs(moveX - selectX);
		for(int i = 1; i < amtOfSquareBetween; i++)
		{
			if(board.getPiece(selectX + i*directionX, selectY + i*directionY) != null)
			{
				return true;
			}
		}

		return false;
	}

	// Rook collision detection
	private boolean rookCollide(int moveX, int moveY)
	{
		// Checks if there is a piece between the selected piece and the move location
		if(moveX == selectedPiece.getX())
		{
			int selectedY = selectedPiece.getY();
			int min;
			int max;
			
			// Sets min to the y coordinate with the lowest number
			// and max to the y coordinate with the highest number
			if(selectedY > moveY)
			{
				min = moveY+1;
				max = selectedY;
			}
			else
			{
				min = selectedY+1;
				max = moveY;
			}
			
			// Checks if there is a piece between the coordinates
			for(int i = min; i < max; i++)
			{
				if(board.getPiece(moveX, i) != null)
				{
					return true;
				}
			}
		}
		else if(moveY == selectedPiece.getY())
		{
			int selectedX = selectedPiece.getX();
			int min;
			int max;

			// Sets min to the x coordinate with the lowest number
			// and max to the x coordinate with the highest number
			if(selectedX > moveX)
			{
				min = moveX+1;
				max = selectedX;
			}
			else
			{
				min = selectedX+1;
				max = moveX;
			}
			
			// Checks if there is a piece between the coordinates
			for(int i = min; i < max; i++)
			{
				if(board.getPiece(i, moveY) != null)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	// Captures a piece
	private void capturePiece(Piece piece)
	{
		int playerIndex; // index of player from array
		if(piece.getColour() == Colour.WHITE)
		{
			playerIndex = 1;
		}
		else
		{
			playerIndex = 0;
		}
	
		
		// Increase points of opposing player
		if(piece.getPieceType() == PieceType.COMBINED)
		{
			players[playerIndex].addPoints(COMBINED_PIECE_WORTH);
		}
		else
		{
			players[playerIndex].addPoints(PIECE_WORTH);
		}
		
		board.removePiece(piece);
	}
	
	// Finds the colour of the turn's player
	public Colour getColourTurn()
	{
		int turnTeam = 2-turnCount%2;
		if(turnTeam == 1)
		{
			return Colour.WHITE;
		}
		else // turnTeam == 2
		{
			return Colour.BLACK;
		}
	}

	public Square[][] getSquares() 
	{
		return board.getSquares();
	}
	
	public int getState()
	{
		return gameState;
	}
	
	public int getTurnCount()
	{
		return turnCount;
	}
	
	public int getMaxMoves()
	{
		return maxMoves;
	}
	
	public Player getPlayer1()
	{
		return players[0];
	}
	
	public Player getPlayer2()
	{
		return players[1];
	}
}
