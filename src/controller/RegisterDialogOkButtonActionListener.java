package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.MainSystem;
import view.RegisterDialog;

public class RegisterDialogOkButtonActionListener implements ActionListener 
{
	private RegisterDialog registerDialog;
	private JTextField name;
	private JTextField password;
	private JTextField rePassword;
	private MainSystem mainSystem;
	private String detail="";
	
	public RegisterDialogOkButtonActionListener(RegisterDialog registerDialog, JTextField name, JTextField password, JTextField rePassword, MainSystem mainSystem)
	{
		this.registerDialog=registerDialog;
		this.name=name;
		this.password=password;
		this.rePassword=rePassword;
		this.mainSystem=mainSystem;
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		//check if username is not empty
		if(!name.getText().isEmpty())
		{
			//check if passwordis not empty
			if(!password.getText().isEmpty())
			{
				//check if repassword is not empty
				if(!rePassword.getText().isEmpty())
				{		
					//check if password is the same with repassword
					if(password.getText().equals(rePassword.getText()))
					{
						//check if there's any player
						if(!mainSystem.getAllPlayers().isEmpty())
						{
							detail=mainSystem.register(name.getText(), password.getText(), rePassword.getText());
							//check if it registers successfully and if it is, dispose the dialog box
							if(detail.equals("You have been registered as "+name.getText()))
							{
								registerDialog.dispose();
							}
						}
						else
						{
							detail=mainSystem.register(name.getText(), password.getText(), rePassword.getText());
							registerDialog.dispose();
						}
					}
					else
					{
						detail="Password does not match. Please try again!";
					}
				}
				else
				{
					detail="Please fill in the 2nd password text field";
				}
			}
			else
			{
				detail="Please input a valid password";
			}
		}
		else
		{
			detail="Please input a valid username";
		}
		JOptionPane.showMessageDialog(null, detail);
	}
}
