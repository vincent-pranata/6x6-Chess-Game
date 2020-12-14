package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.MainSystem;
import model.Player;
import view.LogoutDialog;

public class LogoutDialogOkButtonActionListener implements ActionListener 
{
	private LogoutDialog logoutDialog;
	private JTextField name;
	private MainSystem mainSystem;
	private String detail="";

	public LogoutDialogOkButtonActionListener(LogoutDialog logoutDialog, JTextField name, MainSystem mainSystem)
	{
		this.mainSystem=mainSystem;
		this.logoutDialog=logoutDialog;
		this.name=name;
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		//check if username textfield is empty
		if(!name.getText().isEmpty())
		{
			detail=mainSystem.logout(name.getText());
			if(detail.equals("You have successfully logout from "+name.getText()))
			{
				logoutDialog.dispose();
			}
		}
		else
		{
			detail="Please input username";
		}
		JOptionPane.showMessageDialog(null, detail);
	}
}	
