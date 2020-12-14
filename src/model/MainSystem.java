package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MainSystem 
{
	private List<Player> players = new ArrayList<Player>();
	private Player player;
	private Player[] loginPlayer = new Player[2];
	private PrintWriter output;
	private Scanner scanFile;
	private File file=new File("PlayerLists.txt");
	private String details="";
	private MessageDigest digest;
	private ChessGame game;
	
	public MainSystem()
	{
		readData();
	}
	
	//A method to create the chess game if there
	//are 2 online players and shows the winner
	//and have no game
	public ChessGame playChess() 
	{
		if(game==null || game.isGameOver())
		{
			if(loginPlayer[0]!=null && loginPlayer[1]!=null)
			{
				game = new ChessGame(loginPlayer[0], loginPlayer[1]);
			}
			else
			{
				game=null;
			}
		}
		return game;
	}
	
	//A method where register a new user
	public String register(String userName, String password, String rePassword)
	{
		password=encryptPassword(password);
		if(!getAllPlayers().isEmpty())
		{
			if(getPlayer(userName)==null)
			{
				this.player = new Player(userName, password);
				addPlayer(this.player);
				details="You have been registered as "+userName;
			}
			else
			{
				details=userName+" has been used by someone else. Please choose another username";
			}
		}
		else
		{
			this.player = new Player(userName, password);
			addPlayer(this.player);
			details="You have been registered as "+userName;
		}
		return details;
	}
	
	//A method where it adds a player to the login array if it
	//there is a space
	public String login(String userName, String password)
	{	
		password=encryptPassword(password);
		if(getPlayer(userName)!=null)
		{
			if(getPlayer(userName).getUserName().equals(userName) && getPlayer(userName).getPassword().equals(password))
			{
				if(loginPlayer[0]!=null)
				{
					if(loginPlayer[1]!=null)
					{
						if((getPlayer(userName).getUserName().equals(loginPlayer[0].getUserName())) || (getPlayer(userName).getUserName().equals(loginPlayer[1].getUserName())))
						{
							details=userName+" is already logged in";
						}
						else
						{
							if(addLoginPlayer(userName))
							{
								details="You have successfully login as "+userName;
							}
							else
							{
								details="2 players are already login. Please try another player.";
							}
						}
					}
					else if(!getPlayer(userName).getUserName().equals(loginPlayer[0].getUserName())) 
					{
						if(addLoginPlayer(userName))
						{
							details="You have successfully login as "+userName;
						}
					}
					else
					{
						details=userName+" is already logged in";
					}
				}
				else
				{
					if(addLoginPlayer(userName))
					{
						details="You have successfully login as "+userName;
					}
				}
			}
			else
			{
				details="Invalid username or password. Please try again";
			}
		}
		else
		{
			details="Invalid username or password. Please try again";
		}
		return details;
	}
	
	//A method where if remove player from the array 
	//if it is true
	public String logout(String userName)
	{		
		//check if player exists
		for(Player player:getAllPlayers())
		{
			if((player.getUserName().equals(userName)))
			{
				boolean detail = removeLoginPlayer(userName);
				if(detail)
				{
					details="You have successfully logout from "+userName;
				}
				else
				{
					details=userName+" is not logged in.";
				}
				break;
			}
			else
			{
				details=userName+" does not exist. Please try again";
			}	
		}
		return details;
	}
	
	//Add 2 player to an array so they are able to play
	private boolean addLoginPlayer(String userName)
	{
		for(Player player:players)
		{
			if(player.getUserName().equals(userName))
			{
				if(loginPlayer[0]==null)
				{
					loginPlayer[0]=player;
					return true;
				}
				else if(loginPlayer[1]==null)
				{
					loginPlayer[1]=player;
					return true;
				}
			}
		}
		return false;
	}
	
	//get player based on the username
	public Player getPlayer(String userName)
	{
		for(Player player:players)
		{
			if(player.getUserName().equals(userName))
			{
				this.player=player;
				break;
			}
			else
			{
				this.player=null;
			}
		}
		return player;
	}
	
	//remove player from loginPlayer array so another player can login
	private boolean removeLoginPlayer(String userName)
	{
		if(loginPlayer[0]!=null)
		{
			if(loginPlayer[0].getUserName().equals(userName))
			{
				loginPlayer[0]=null;
				return true;
			}
		}
		if(loginPlayer[1]!=null)
		{
			if(loginPlayer[1].getUserName().equals(userName))
			{
				loginPlayer[1]=null;
				return true;
			}
		}
		return false;
	}
	
	//Getting the username of the login players
	public String getOnlinePlayer()
	{
		String detail="";
		for(int i=0; i<2; i++)
		{
			if(loginPlayer[1]==null && loginPlayer[0]==null)
			{
				detail="There is no player logged in.";
			}
			else if(loginPlayer[i]!=null)
			{
				detail+=loginPlayer[i].toString();
			}
		}
		return detail;
	}
	
	//Adding player to the list players
	public void addPlayer(Player player)
	{
		players.add(player);
	}
	
	//get the list off all players in the array list
	public Collection<Player> getAllPlayers() 
	{
		return Collections.unmodifiableList(players);
	}
	
	//the method that is called to save the data of players in a txt file
	public String writeData()
	{
		//the code that  is supposed to be try to be checked of any error 
		try
		{
			//making a new object called output of PrintWriter class
			output = new PrintWriter(file);
			for(Player player:players)
			{
				output.println(player.getCode());
			}
			//closing the output
			output.close();
			details="Players registered are saved.";
		}
		//what to do if there is an errors
		catch(IOException e)
		{
			details="Error - Unable to save player lists";
		}
		return details;
	}
	
	//the method that is called from facade to make a business booking
	private String readData()
	{
		String playerData;
		//the code that  is supposed to be try to be checked of any error 
		try
		{
			//creating a new object called scanFile of scanner class
			scanFile = new Scanner(file);
		}
		//what to do if there is an errors
		catch(FileNotFoundException e)
		{
			details="Error - "+file+" not found\n"+"No data has been added";
		}
		//what to do if scanFile is not null
		if(scanFile!=null)
		{
			//a loop that keeps on going while scanFile has a line
			while(scanFile.hasNext())
			{
				//getting the value of playerData
				playerData = scanFile.next();
				//creating an array that is split by :
				String data[] = playerData.split(":");
				//assigning the value of data[0]
				String username = data[0];
				//assigning the value of data[1]
				String password = data[1];
				player = new Player(username, password);
				addPlayer(player);
			}
			details="Players in "+file+" has been inputted";
		}
		return details;
	}
	
	//encrypt the password to make sure no one can steal it
	private String encryptPassword(String password) 
	{
		try 
		{
			//encode it using SHA-256
			digest = MessageDigest.getInstance("SHA-256");
		}
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		//getting the bytes value for password
		byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		//changing the value of encodedHash from bytes to hex
		StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < encodedHash.length; i++) 
	    {
	    	String hex = Integer.toHexString(0xff & encodedHash[i]);
	    	if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	};
	
	//get the chessGame if it has been made
	public ChessGame getChessGame()
	{
		return game;
	}
	
	//get the chessGame if it has been made
	public Player getLoginPlayer1()
	{
		return loginPlayer[0];
	}
	
	//get the chessGame if it has been made
	public Player getLoginPlayer2()
	{
		return loginPlayer[1];
	}
}
