package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import model.MainSystem;
import view.chessGame.GamePanel;
import view.chessGame.SummaryPanel;

public class ChessFrame extends JFrame
{
	private MainSystem mainSystem= new MainSystem();
	private SummaryPanel summary = new SummaryPanel();
	private GamePanel gamePanel = new GamePanel(summary);
	private ToolBar toolBar = new ToolBar(mainSystem, gamePanel, summary);
	
	public ChessFrame()
	{
		setLayout(new BorderLayout());
		setBounds(0, 0, 500, 500);
		setMinimumSize(new Dimension(500, 500));
		//creates a jmenubar
		setJMenuBar(new MenuBar(mainSystem, gamePanel, summary));
		//create a tool bar
		//makes the tool bar unmovable
		toolBar.setFloatable(false);
		
		add(toolBar, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);
		add(summary, BorderLayout.EAST);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}
}