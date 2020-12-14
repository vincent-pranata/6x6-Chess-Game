package driver;

import javax.swing.SwingUtilities;

import view.ChessFrame;

public class TestDriver 
{
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new ChessFrame();
			}
		});
	}
}
