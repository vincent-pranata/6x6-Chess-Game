package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.MainSystem;
import view.LoginDialog;

public class LoginActionListener implements ActionListener 
{
	private MainSystem mainSystem;
	public LoginActionListener(MainSystem mainSystem)
	{
		this.mainSystem=mainSystem;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//check if it doesn't have a game or a game ends
		if(mainSystem.getChessGame()==null || mainSystem.getChessGame().isGameOver())
		{
			//if game can't be created
			if(mainSystem.getAllPlayers().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "There are no players registered");
			}
			else
			{
				//create a dialog box
				new LoginDialog(new Frame(), mainSystem);
			}
		}
		//check if game is ongoing
		else if(!mainSystem.getChessGame().isGameOver())
		{
			JOptionPane.showMessageDialog(null, "Game is on going. Please wait until it ends");
		}	
		
	}

}
