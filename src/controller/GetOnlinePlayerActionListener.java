package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.MainSystem;
public class GetOnlinePlayerActionListener implements ActionListener 
{
	private MainSystem mainSystem;
	public GetOnlinePlayerActionListener(MainSystem mainSystem)
	{
		this.mainSystem=mainSystem;
	}
	
	//pop up a message of online players
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JOptionPane.showMessageDialog(null, mainSystem.getOnlinePlayer());
	}

}
