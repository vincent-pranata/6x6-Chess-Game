package controller;

import java.util.Scanner;

import model.enumeration.Colour;

public class ChessGameTextController
{
	private Scanner scan = new Scanner(System.in);
	private String playerName1;
	private String playerName2;

	public ChessGameTextController(String playerName1, String playerName2)
	{
		this.playerName1 = playerName1;
		this.playerName2 = playerName2;
	}

	// Prompts use to select a piece
	public String selectPiece(Colour teamColour)
	{
		Boolean valid = false;
		String move = "";
		do
		{
			if(teamColour == Colour.WHITE)
			{
				System.out.println("\n" + playerName1 + "(Uppercase) select piece to move:");
			}
			else
			{
				System.out.println("\n" + playerName2 + "(Lowercase) select piece to move:");
			}
			
			move = scan.nextLine();
			
			// Checks if input is in the right format 
			// Eg. A1, a1, f4, D3..
			if(move.matches("^([A-Fa-f][1-6])$"))
			{
				valid = true;
			}
			else
			{
				System.out.println("Invalid input");
			}
		} while (!valid);
		
		return textToPosition(move);
	}
	
	// Converts text (eg. B3) to an integer format the program can read
	// A -> 0, B -> 1 etc.
	// 0 -> 6, 2 -> 4 etc.
	private String textToPosition(String pos)
	{
		final String[] positions = {"A", "B", "C", "D", "E", "F"};
		String xPos = "";
		String yPos = "";
		
		for(int i = 0; i < positions.length; i++)
		{
			if(pos.substring(0,1).toUpperCase().equals(positions[i]))
			{
				xPos = String.valueOf(i);
			}
		}
		
		yPos = String.valueOf(6-Integer.parseInt(pos.substring(1)));
		return xPos+yPos;
	}
	
	// Prompts the user to select a location
	public String selectLocation(Colour teamColour)
	{
		Boolean valid = false;
		String move = "";
		do
		{
			if(teamColour == Colour.WHITE)
			{
				System.out.println("\n" + playerName1 + "(Uppercase) select move location:");
			}
			else
			{
				System.out.println("\n" + playerName2 + "(Lowercase) move location:");
			}
			
			move = scan.nextLine();
			
			// Checks if input is in the right format 
			// Eg. A1, a1, f4, D3..
			if(move.matches("^([A-Fa-f][1-6])$"))
			{
				valid = true;
			}
			else
			{
				System.out.println("Invalid input");
			}
		} while (!valid);
		
		return textToPosition(move);
	}
	
	// Get's the user's chosen max moves and calcuates the max move
	public int getMaxMoves()
	{
		int maxMove1 = getPlayerMaxMove(playerName1);
		int maxMove2 = getPlayerMaxMove(playerName2);
		
		return (int)((maxMove1+maxMove2)/2);
	}
	
	// Prompts the user to enter their max moves
	private int getPlayerMaxMove(String playerName)
	{
		int maxMove = 0;
		Boolean valid;
		
		// Loops until valid input
		do
		{
			valid = true;
			System.out.println(playerName + " select amount of moves:");
			try
			{
				maxMove = Integer.parseInt(scan.nextLine());
				if(!(maxMove > 0))
				{
					valid = false;
				}
			}
			catch(NumberFormatException e)
			{
				valid = false;
			}
			
			if(!valid)
			{
				System.out.println("Invalid input, please enter a positive integer");
			}
		} while (!valid);
		return maxMove;
	}
}
