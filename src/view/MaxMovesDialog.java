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
import javax.swing.JTextField;

import controller.DialogCancelButtonActionListener;
import controller.MaxMovesDialogOkButtonActionListener;
import model.ChessGame;
import model.MainSystem;
import view.chessGame.GamePanel;
import view.chessGame.SummaryPanel;

public class MaxMovesDialog extends JDialog 
{
	private JTextField maxMoves1;
	private JTextField maxMoves2;
	private JButton okButton;
	private JButton cancelButton;
	
	public MaxMovesDialog(Frame frame, GamePanel gamePanel, SummaryPanel summary, MainSystem mainSystem)
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
		JLabel maxMoves1Label = new JLabel("Player 1 ("+mainSystem.getLoginPlayer1().getUserName()+") Max Moves: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(maxMoves1Label,gbc);
		maxMoves1 = new JTextField(15);
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 0;
		inputPanel.add(maxMoves1,gbc);
		JLabel maxMoves2Label = new JLabel("Player 2 ("+mainSystem.getLoginPlayer2().getUserName()+") Max Moves: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		inputPanel.add(maxMoves2Label,gbc);
		maxMoves2 = new JTextField(15);
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 1;
		inputPanel.add(maxMoves2,gbc);
		JLabel spacer = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		inputPanel.add(spacer,gbc);
		//creating a panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2, 10, 10));
		okButton = new JButton("OK");
		okButton.addActionListener(new MaxMovesDialogOkButtonActionListener(this, mainSystem, gamePanel, maxMoves1, maxMoves2, summary));
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
