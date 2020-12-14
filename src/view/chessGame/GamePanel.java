package view.chessGame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.JPanel;

import controller.chessGame.BoardListener;
import model.ChessGame;
import model.Square;
import model.enumeration.Colour;
import model.enumeration.PieceType;
import model.pieces.CombinedPiece;
import model.pieces.Piece;

public class GamePanel extends JPanel
{
	private SquareGUI[][] squares = new SquareGUI[6][6];
	public static final int INITIAL_BOARD_SIZE = 1000;
	private JPanel board;
	
	private ChessGame game;
	public GamePanel(SummaryPanel summary)
	{
		setBounds(0,0, INITIAL_BOARD_SIZE, INITIAL_BOARD_SIZE);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		board = new JPanel() 
		{
			@Override
			public Dimension getPreferredSize()
			{
				return new Dimension(getLength(), getLength());
				
			}
		};
		
		add(board);
		createBoard(summary);
	}
	
	public void updateBoard()
	{
		if(game != null)
		{
			Square[][] gameSquares = game.getSquares();
			for(int x = 0; x < squares.length; x++)
			{
				for(int y = 0; y < squares.length; y++)
				{
					squares[x][y].removePiece();
					Square square = gameSquares[x][y];
					Piece piece = square.getPiece();
					if(piece != null)
					{
						if(piece.getPieceType() == PieceType.COMBINED)
						{
							Piece[] pieces = ((CombinedPiece)piece).getPieces();
							
							squares[x][y].addPiece((new CombinedPieceGui(pieces[0].getPieceType(), pieces[1].getPieceType(), piece.getColour())));
						}
						else
						{
							squares[x][y].addPiece((new PieceGUI(piece.getPieceType(), piece.getColour())));
						}
					}
				}
			}
		}
		repaint();
		revalidate();
	}
	
	public void addGame(ChessGame game)
	{
		this.game = game;
		updateBoard();
	}
	
	
	public void updatePossibleMoves() 
	{
		if(game != null)
		{
			Collection<Square> possibleMoves = game.getPossibleMove();
			
			for(Square square:possibleMoves)
			{
				squares[square.getX()][square.getY()].setValid(true);
			}
		}
	}

	public void resetPossibleMoves() 
	{
		for(int y= 0; y<6; y++)
		{
			for(int x = 0; x<6; x++)
			{
				squares[x][y].setValid(false);
			}
		}
	}
	
	public ChessGame getGame()
	{
		return game;
	}
	
	public JPanel getBoard()
	{
		return board;
	}
	
	// gets length of the board
	public int getLength()
	{
		int width = getWidth();
		int height = getHeight();		
		
		if(width < height)
		{
			return width;
		}
		return height;
	}
	
	private void createBoard(SummaryPanel summary)
	{
		board.setLayout(new GridLayout(6,6));
		for(int y= 0; y<6; y++)
		{
			for(int x = 0; x<6; x++)
			{
				if((x+y)%2 == 0)
				{
					squares[x][y] = new SquareGUI(Colour.BLACK);
					board.add(squares[x][y]);
				}
				else
				{
					squares[x][y] = new SquareGUI(Colour.WHITE);
					board.add(squares[x][y]);
					
				}
			}
		}
		addMouseListener(new BoardListener(this, summary));
	}
	
	
	
}
