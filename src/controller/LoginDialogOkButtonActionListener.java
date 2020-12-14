package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.MainSystem;
import view.LoginDialog;

public class LoginDialogOkButtonActionListener implements ActionListener 
{
	private LoginDialog loginDialog;
	private JTextField name;
	private JPasswordField password;
	private MainSystem mainSystem;
	private String detail="";
	
	public LoginDialogOkButtonActionListener(LoginDialog loginDialog, JTextField name, JPasswordField password, MainSystem mainSystem)
	{
		this.loginDialog=loginDialog;
		this.name=name;
		this.password=password;
		this.mainSystem=mainSystem;
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		//check if username textfield is empty
		if(!name.getText().isEmpty())
		{
			//check if password consists of more than 0 characters
			if(password.getPassword().length>0)
			{
				String passwordText = new String(password.getPassword());
				detail=mainSystem.login(name.getText(), passwordText);
				if(detail.equals("You have successfully login as "+name.getText()))
				{
					//close the dialog box
					loginDialog.dispose();
				}
			}
			else
			{
				detail="Please input password";
			}
		}
		else
		{
			detail="Please input username";
		}
		JOptionPane.showMessageDialog(null, detail);
	}
}	
