package view.chessGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.enumeration.Colour;

public class SquareGUI extends JPanel
{
	private Colour colour;
	private boolean validMove = false;
	private JLabel piece;
	
	public SquareGUI(Colour colour)
	{
		this.colour = colour;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		Color c = colour == Colour.BLACK?new Color(254,206,158):new Color(209,139,70);
		if(validMove)
		{
			setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		}
		else
		{
			setBorder(BorderFactory.createEmptyBorder());
		}
		g2d.setColor(c);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		

	}
	
	public void setValid(boolean b)
	{
		validMove = b;
		repaint();
		revalidate();
	}
	
	public void addPiece(JLabel piece)
	{
		this.piece = piece;
		add(piece);
		repaint();
		revalidate();
	}
	
	public void removePiece()
	{
		if(piece != null)
		{
			remove(piece);
			piece = null;
		}
	}
}
