package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import controller.ExitActionListener;
import controller.GetOnlinePlayerActionListener;
import controller.LoginActionListener;
import controller.LogoutActionListener;
import controller.PlayChessActionListener;
import controller.RegisterActionListener;
import model.MainSystem;
import view.chessGame.GamePanel;
import view.chessGame.SummaryPanel;

public class MenuBar extends JMenuBar
{
	public MenuBar(MainSystem mainSystem, GamePanel gamePanel, SummaryPanel summary)
	{
		//create a drop down menu
		JMenu menu = new JMenu("Menu");
		//add an item called register
		JMenuItem register = new JMenuItem("Register");
		register.addActionListener(new RegisterActionListener(mainSystem));
		//add an item called login
		JMenuItem login = new JMenuItem("Login");
		login.addActionListener(new LoginActionListener(mainSystem));
		//add an item called logout
		JMenuItem logout = new JMenuItem("Logout");
		logout.addActionListener(new LogoutActionListener(mainSystem));
		//add an item called get online player
		JMenuItem getOnlinePlayer= new JMenuItem("Get Login Player");
		getOnlinePlayer.addActionListener(new GetOnlinePlayerActionListener(mainSystem));
		//add an item called exit
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitActionListener(mainSystem));
		menu.add(register);
		menu.add(login);
		menu.add(logout);
		menu.addSeparator();
		menu.add(getOnlinePlayer);
		menu.addSeparator();
		menu.add(exit);
		add(menu);
	}
}
