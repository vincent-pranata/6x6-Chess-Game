package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.MainSystem;
import view.RegisterDialog;
public class RegisterActionListener implements ActionListener 
{
	private MainSystem mainSystem;
	public RegisterActionListener(MainSystem mainSystem)
	{
		this.mainSystem=mainSystem;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//check if there is no game and if game has ended
		if(mainSystem.getChessGame()==null|| mainSystem.getChessGame().isGameOver())
		{
			new RegisterDialog(new Frame(), mainSystem);
		}
		//check if game is still on going
		else if(!mainSystem.getChessGame().isGameOver())
		{
			JOptionPane.showMessageDialog(null, "Game is on going. Please wait until it ends");
		}
	}
}
