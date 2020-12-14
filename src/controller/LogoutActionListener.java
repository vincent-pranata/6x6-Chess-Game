package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.MainSystem;
import view.LogoutDialog;

public class LogoutActionListener implements ActionListener 
{
	private MainSystem mainSystem;
	public LogoutActionListener(MainSystem mainSystem)
	{
		this.mainSystem=mainSystem;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//check if there's no game or game has ended
		if(mainSystem.getChessGame()==null || mainSystem.getChessGame().isGameOver())
		{
			//check if no players registered
			if(mainSystem.getAllPlayers().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "There are no players registered");
			}
			else
			{
				//create a dialog box
				new LogoutDialog(new Frame(), mainSystem);
			}
		}
		//check if game is still ongoing
		else if(!mainSystem.getChessGame().isGameOver())
		{
			JOptionPane.showMessageDialog(null, "Game is on going. Please wait until it ends");
		}	
	}
}
