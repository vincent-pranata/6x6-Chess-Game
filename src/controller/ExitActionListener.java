package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.MainSystem;

public class ExitActionListener implements ActionListener
{
	private MainSystem mainSystem;
	public ExitActionListener(MainSystem mainSystem) 
	{
		this.mainSystem=mainSystem;
	}

	//close the frame and write the players
	@Override
	public void actionPerformed(ActionEvent e)
	{
		mainSystem.writeData();
		System.exit(JFrame.EXIT_ON_CLOSE);
	}

}
