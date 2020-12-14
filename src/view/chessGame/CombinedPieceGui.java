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

public class CombinedPieceGui extends JLabel{

	Image image1;
	Image image2;

	public CombinedPieceGui(PieceType piece1,PieceType piece2,Colour color) {
		try 
		{
		  this.image1 = ImageIO.read(new File("image/" + piece1 + color + ".png"));
		} catch (IOException e) 
		{
		    e.printStackTrace();
		}
		try 
		{
		  this.image2 = ImageIO.read(new File("image/" + piece2 + color + ".png"));
		} catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		drawCombine(g);
	}
	
	private void drawCombine(Graphics g) {
		g.drawImage(image1, 0, 0, getWidth()/2, getHeight()/2,this);
		g.drawImage(image2, getWidth()/2, getHeight()/2, getWidth()/2, getHeight()/2,this);


	}
	
	
	public Dimension getPreferredSize()
	{
		return new Dimension(getParent().getWidth(),getParent().getHeight());
		
	}
	
}
