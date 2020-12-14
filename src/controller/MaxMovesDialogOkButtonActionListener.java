package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.ChessGame;
import model.MainSystem;
import view.MaxMovesDialog;
import view.chessGame.GamePanel;
import view.chessGame.SummaryPanel;

public class MaxMovesDialogOkButtonActionListener implements ActionListener
{
	private SummaryPanel summary;
	private MaxMovesDialog maxMovesDialog;
	private GamePanel gamePanel;
	private ChessGame game;
	private JTextField maxMoves1;
	private JTextField maxMoves2;
	private MainSystem mainSystem;
	
	public MaxMovesDialogOkButtonActionListener(MaxMovesDialog maxMovesDialog, MainSystem mainSystem, GamePanel gamePanel, JTextField maxMoves1, JTextField maxMoves2, SummaryPanel summary)
	{
		this.mainSystem=mainSystem;
		this.summary=summary;
		this.maxMovesDialog=maxMovesDialog;
		this.gamePanel=gamePanel;
		this.maxMoves1=maxMoves1;
		this.maxMoves2=maxMoves2;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		game = mainSystem.playChess();
		//try if maxmoves1 and maxmoves2 are valid integers value
		try
		{
			int maxMovesValue1 = Integer.parseInt(maxMoves1.getText());
			int maxMovesValue2 = Integer.parseInt(maxMoves2.getText());
			
			//check if maxmoves1 and maxmove2 are positive integers
			if(maxMovesValue1>0 && maxMovesValue2>0)
			{
				int maxMovesAvr = (maxMovesValue1+maxMovesValue2)/2;
				game.setMaxMoves(maxMovesAvr);
				maxMovesDialog.dispose();
				JOptionPane.showMessageDialog(null, "Max Moves for this game will be "+maxMovesAvr);
				SwingUtilities.invokeLater(new Runnable() 
				{
					 public void run() 
					 {
						 summary.update(game);
						 gamePanel.addGame(game);
					 }
				 });
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please input a positive value for max moves");
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please input a valid value for max moves");
		}
	}
}
