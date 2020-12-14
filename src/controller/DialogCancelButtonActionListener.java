package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class DialogCancelButtonActionListener implements ActionListener 
{
	private JDialog dialog;
	public DialogCancelButtonActionListener(JDialog dialog) 
	{
		this.dialog=dialog;
	}
	
	//close any dialog box sent
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		dialog.dispose();
	}

}
