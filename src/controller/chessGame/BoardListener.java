
package controller.chessGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.ChessGame;
import view.chessGame.GamePanel;
import view.chessGame.SummaryPanel;

public class BoardListener extends MouseAdapter
{
	GamePanel panel;
	SummaryPanel summary;
	
	public BoardListener(GamePanel panel, SummaryPanel summary)
	{
		this.panel = panel;
		this.summary=summary;
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		ChessGame game = panel.getGame();
		if(game != null)
		{
			int sideLength = (panel.getWidth()-panel.getLength())/2;
			int x = (int)(((double)(e.getX()-sideLength)/panel.getBoard().getWidth())*6);
			int y = (int)(((double)e.getY()/panel.getBoard().getHeight())*6);
			if(SwingUtilities.isLeftMouseButton(e))
			{
					if(game.getState() == ChessGame.SELECT_STATE)
					{
						if(game.selectPiece(x, y))
						{
							panel.updatePossibleMoves();
						}
					}
					else if(game.getState() == ChessGame.MOVE_STATE)
					{
						if(game.movePiece(x, y))
						{
							panel.updateBoard();
							summary.update(game);
							showWinnerMessage(game);
						}
						else
						{
							game.deselectPiece();
							showWinnerMessage(game);
						}
						panel.resetPossibleMoves();
					}	
			}
			else if(SwingUtilities.isRightMouseButton(e))
			{
				if(game.getState() == ChessGame.MOVE_STATE)
				{
					if(game.seperateMove(x, y))
					{
						panel.updateBoard();
						summary.update(game);
						panel.resetPossibleMoves();
						showWinnerMessage(game);
					}
				}
			}
		}
	}
	
	private void showWinnerMessage(ChessGame game)
	{
		if(game.isGameOver())
		{
			if(game.getWinner()!=null)
			{
				JOptionPane.showMessageDialog(null, game.toString());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "It's a tie!");
			}
			game.getPlayer1().resetPoints();
			game.getPlayer2().resetPoints();
		}
	}
}
