package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.DialogCancelButtonActionListener;
import controller.LoginDialogOkButtonActionListener;
import model.MainSystem;

public class LoginDialog extends JDialog 
{
	private JTextField name;
	private JPasswordField password;
	private JButton okButton;
	private JButton cancelButton;
	
	public LoginDialog(Frame frame, MainSystem mainSystem)
	{
		//setting the position of the dialog box
		Point loc = frame.getLocation();
		setLocation(loc.x+80,loc.y+80);
		//creating panel to contain input panel and button panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		//creating the panel to contain all the elements of input
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2,2,2,2);
		JLabel nameLabel = new JLabel("Username: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(nameLabel,gbc);
		name = new JTextField(15);
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 0;
		inputPanel.add(name,gbc);
		JLabel passwordLabel = new JLabel("Password: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(passwordLabel,gbc);
		password = new JPasswordField(15);
		password.setEchoChar('*');
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 1;
		inputPanel.add(password,gbc);
		JLabel spacer = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(spacer,gbc);
		//creating a panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2, 10, 10));
		okButton = new JButton("Ok");
		okButton.addActionListener(new LoginDialogOkButtonActionListener(this, name, password, mainSystem));
		buttonPanel.add(okButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new DialogCancelButtonActionListener(this));
		buttonPanel.add(cancelButton);
		panel.add(inputPanel, BorderLayout.NORTH);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		getContentPane().add(panel);
		pack();
		this.setVisible(true);
	}	
}
