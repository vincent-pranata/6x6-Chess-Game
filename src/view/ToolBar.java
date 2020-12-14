package view;

import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.PlayChessActionListener;
import model.MainSystem;
import view.chessGame.GamePanel;
import view.chessGame.SummaryPanel;

public class ToolBar extends JToolBar
{
	public ToolBar(MainSystem mainSystem, GamePanel gamePanel, SummaryPanel summary)
	{
		//add a button of play chess to tool bar
		JButton playChess = new JButton("Play Chess");
		playChess.addActionListener(new PlayChessActionListener(mainSystem, gamePanel, summary));
		add(playChess);
	}
}
