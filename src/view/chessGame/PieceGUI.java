package view.chessGame;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import model.enumeration.Colour;
import model.enumeration.PieceType;

public class PieceGUI extends JLabel{

	Image image;

	public PieceGUI(PieceType piece,Colour color) {
		try 
		{
		  this.image = ImageIO.read(new File("image/" + piece + color + ".png"));
		} catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(getParent().getWidth(),getParent().getHeight());
		
	}
}