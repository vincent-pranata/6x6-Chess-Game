package view.chessGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ChessGame;
import model.enumeration.Colour;

public class SummaryPanel extends JPanel
{
	private JLabel playerTurn;
	private JLabel numberOfTurn;
	private JLabel name1;
	private JLabel name2;
	private JLabel point1;
	private JLabel point2;
	
	public SummaryPanel()
	{
		//creating panel to contain input panel and button panel
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2,2,2,2);
		playerTurn = new JLabel(" Turn");
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(playerTurn, gbc);
		numberOfTurn= new JLabel("0/0");
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(numberOfTurn, gbc);
		JLabel spacer = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(spacer,gbc);
		JLabel label = new JLabel("Player Detail");
		gbc.gridy = 3;
		panel.add(label, gbc);
		JLabel player1 = new JLabel("Player 1");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(player1, gbc);
		name1 = new JLabel("None");
		gbc.gridx = 2;
		gbc.gridy = 4;
		panel.add(name1, gbc);
		JLabel player1Point = new JLabel("Points: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(player1Point, gbc);
		point1 = new JLabel("0");
		gbc.gridx = 2;
		gbc.gridy = 5;
		panel.add(point1, gbc);
		JLabel spacer1 = new JLabel(" ");
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(spacer1,gbc);
		JLabel player2 = new JLabel("Player 2");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 7;
		panel.add(player2, gbc);
		name2 = new JLabel("None");
		gbc.gridx = 2;
		gbc.gridy = 7;
		panel.add(name2, gbc);
		JLabel player2Point = new JLabel("Points: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 8;
		panel.add(player2Point, gbc);
		point2 = new JLabel("0");
		gbc.gridx = 2;
		gbc.gridy = 8;
		panel.add(point2, gbc);
		add(panel, BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	//updates all the labels needed to be updated
	public void update(ChessGame game)
	{
		if(game!=null)
		{
			if(game.getColourTurn()==Colour.WHITE)
			{
				playerTurn.setText("Player 1's (White) Turn");
			}
			else
			{
				playerTurn.setText("Player's 2 (Black) Turn");
			}
			
			if(game.getTurnCount()>game.getMaxMoves())
			{
				playerTurn.setText("Game End");
				numberOfTurn.setText(game.getMaxMoves()+"/"+game.getMaxMoves());
			}
			else
			{
				numberOfTurn.setText(game.getTurnCount()+"/"+game.getMaxMoves());
			}
			name1.setText(game.getPlayer1().getUserName());
			point1.setText(Integer.toString(game.getPlayer1().getPoints()));
			name2.setText(game.getPlayer2().getUserName());
			point2.setText(Integer.toString(game.getPlayer2().getPoints()));
		}
		//check if there's no game
		else
		{
			playerTurn.setText(" - Turn");
			numberOfTurn.setText("0/0");
			name1.setText("None");
			point1.setText("0");
			name2.setText("None");
			point2.setText("0");
		}
	}
	
	//auto resize the summary panel based on the frame
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(getParent().getWidth()/3, getParent().getHeight());
	}
}