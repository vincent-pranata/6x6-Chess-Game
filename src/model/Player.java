package model;

public class Player 
{
	private String userName;
	private String password;
	private int points;
	
	//setting the values for each player
	public Player(String userName, String password)
	{
		this.userName=userName;
		this.password=password;	
		points=0;
	}
	
	//set the username for each player
	public void setUserName(String userName)
	{
		this.userName=userName;
	}
	
	//sending the username for each player
	public String getUserName()
	{
		return userName;
	}
	
	//set the password for each player
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	//sending the password for each player
	public String getPassword()
	{
		return password;
	}
		
	//adding the points to the points that
	//each player has
	public void addPoints(int points)
	{
		this.points+=points;
	}
	
	//sending the points for each player
	public int getPoints()
	{
		return points;
	}
	
	//reset the points of each player 
	public void resetPoints()
	{
		points=0;
	}

	//sending the value of username for each player
	public String toString()
	{
		return "\nUsername:	"+userName;
	}
	
	//setting the code for each player so it can be 
	//saved when the program is closed
	public String getCode()
	{
		return userName+":"+password;
	}
}
