package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.ChessGame;
import model.MainSystem;
import view.MaxMovesDialog;
import view.chessGame.GamePanel;
import view.chessGame.SummaryPanel;

public class PlayChessActionListener implements ActionListener
{
	private MainSystem mainSystem;
	private GamePanel gamePanel;
	private SummaryPanel summary;
	
	public PlayChessActionListener(MainSystem mainSystem, GamePanel gamePanel,SummaryPanel summary)
	{
		this.mainSystem=mainSystem;
		this.gamePanel=gamePanel;
		this.summary=summary;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//creates a game if possible
		if(gamePanel.getGame()==null || gamePanel.getGame().isGameOver())
		{
			if(mainSystem.getLoginPlayer1()!=null && mainSystem.getLoginPlayer2()!=null)
			{
				new MaxMovesDialog(new Frame(), gamePanel, summary, mainSystem);
			}
			//check if there is no 2 players logged in
			else
			{
				JOptionPane.showMessageDialog(null, "There are less than 2 players logged in");
			}
		}	
		//check if game is still on going
		else if(!gamePanel.getGame().isGameOver())
		{
			JOptionPane.showMessageDialog(null, "Game is on going. Please wait until the game ends");
		}
	}
}
