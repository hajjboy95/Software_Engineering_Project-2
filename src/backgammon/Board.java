package backgammon;

import java.util.Scanner;

public class Board
{
	static final int NUMBER_OF_PEGS = 24;				// initialising the constants here
	static final int BAR = 2;
	static final int OFF = 2;
	
	/*---------------------------------------------------------------------*/
	static final int RED_HOME_START = 18;
	static final int RED_HOME_END = 24;
	static final int WHITE_HOME_START = 5;
	static final int WHITE_HOME_END = -1;
	static final int TOP_OUTER_START = 12;
	static final int TOP_OUTER_END = 18;
	static final int BOTTOM_OUTER_START = 11;
	static final int BOTTOM_OUTER_END = 5;
	/*
	 * these constants hold the values of the most crucial pegs on the board,
	 * adjusted for an array index starting at 0
	 */
	
	static final String UPPPER_LINE_OF_BOARD = "13----+----+----+----+----18  BAR  19----+----+----+----+----24 OFF\n";
	static final String BOTTOM_LINE_OF_BOARD = "12----+----+----+----+----07  BAR  06----+----+----+----+----01 OFF\n";
	// end constants
	static int playingBoard[] = new int[NUMBER_OF_PEGS + BAR + OFF];
	
	public String showBlackBar()
	{
		return "  X" + playingBoard[25] + "  ";
	}
	
	public String showBlackOff()
	{
		return "X" + playingBoard[27] + "\n";
	}
	
	public String showWhiteBar()
	{
		return "  O" + playingBoard[24] + "  ";
	}
	
	public String showWhiteOff()
	{
		return "O" + playingBoard[26] + "\n\n";
	}
	
	/**
	 * self-explanatory method
	 */
	public void initialiseBoard()
	{
		for ( int i = 0; i < 24; i++ )
		{
			
			switch ( i )
			{
				case 0:
					playingBoard[i] = 2;
					break;
				case 5:
					playingBoard[i] = -5;
					break;
				case 7:
					playingBoard[i] = -3;
					break;
				case 11:
					playingBoard[i] = 5;
					break;
				case 12:
					playingBoard[i] = -5;
					break;
				case 16:
					playingBoard[i] = 3;
					break;
				case 18:
					playingBoard[i] = 5;
					break;
				case 23:
					playingBoard[i] = -2;
					break;
				default:
					playingBoard[i] = 0;
					//break;
					
			}
			
		}
	}
	
	public String printMen( int numberOfMen )
	{
		String returnValue;
		if ( numberOfMen < 0 )
			returnValue = ( "X" + Math.abs ( numberOfMen ) + "  " );	// converts negative numbers to black men
		else if ( numberOfMen > 0 )
			returnValue = ( "O" + numberOfMen + "  " );					// converts positive numbers to white men
		else returnValue = ( " |  " );
		return returnValue;
	}
	
	/**
	 * self-explanatory method
	 */
	public void printBoard()
	{
		String boardPositionCorrection;				// adds whitespaces before men on the display, for a neater look
		
		System.out.print ( UPPPER_LINE_OF_BOARD );
		
		// print the top right quarter of the board
		for ( int counter = TOP_OUTER_START; counter < TOP_OUTER_END; counter++ )
		{
			boardPositionCorrection = ( ( counter == 12 ) ? "" : " " );
			System.out.print ( boardPositionCorrection
					+ printMen ( playingBoard[counter] ) );
		}
		System.out.print ( showBlackBar () );
		
		// print the top left quarter of the board		
		for ( int counter = RED_HOME_START; counter < RED_HOME_END; counter++ )
		{
			boardPositionCorrection = ( ( counter == 18 ) ? "" : " " )
					+ ( ( counter == 23 ) ? " " : "" );
			System.out.print ( boardPositionCorrection
					+ printMen ( playingBoard[counter] ) );
		}
		System.out.print ( showWhiteOff () );
		
		// print the bottom right quarter of the board
		for ( int counter = BOTTOM_OUTER_START; counter > BOTTOM_OUTER_END; counter-- )
		{
			boardPositionCorrection = ( ( counter == 11 ) ? "" : " " );
			System.out.print ( boardPositionCorrection
					+ printMen ( playingBoard[counter] ) );
		}
		System.out.print ( showWhiteBar () );
		
		// print the bottom left quarter of the board
		for ( int counter = WHITE_HOME_START; counter > WHITE_HOME_END; counter-- )
		{
			boardPositionCorrection = ( ( counter == 5 ) ? "" : " " )
					+ ( ( counter == 0 ) ? " " : "" );
			System.out.print ( boardPositionCorrection
					+ printMen ( playingBoard[counter] ) );
		}
		System.out.print ( showBlackOff () );
		
		System.out.print ( BOTTOM_LINE_OF_BOARD );
	}
	
	public int rollDice()
	{
		return ( ( ( int ) ( Math.random () * 101 ) ) % 6 ) + 1;
	}
	
	public static String printMenu()
	{
		String tab6 = "\t\t\t\t\t\t";
		
		System.out.println ( tab6 + " 1. 'N' to statrt a new game " );
		System.out.println ( tab6 + " 2. 'R' to read the rules " );
		System.out.println ( tab6 + " 3. 'Q' to quit " );
		
		Scanner keyboard = new Scanner ( System.in );
		String userChoice1 = keyboard.nextLine ();
		return userChoice1;
		
	}
	
	public static void welcome()
	{
		String newLine3 = "\n\n\n";
		String tab = "\t\t\t\t\t\t";
		
		System.out
				.println ( newLine3 + tab + "*******************************" );
		System.out.println ( tab + "*******************************" );
		System.out.println ( tab + "**** Welcome To The G's *******" );
		System.out.println ( tab + "****	 Backgammon     *******" );
		System.out.println ( tab + "*******************************" );
		System.out
				.println ( tab + "*******************************" + newLine3 );
	}
	
	public static void Rules()
	{
		
		System.out
				.println ( "Each player rolls a single die. If you rolled the highest" );
		System.out
				.println ( "number, move your Checkers according to the numbers showing on" );
		System.out
				.println ( "both dice. After the initial roll, players alternate turns, using " );
		System.out
				.println ( "both dice to determine the number of points to move the Checkers." );
		System.out
				.println ( "Always move the Checkers in the direction of your home board. " );
		System.out
				.println ( "* A Checker can move to an open point, which is a point that" );
		System.out
				.println ( "is occupied by any number of your own Checkers, or a point that" );
		System.out
				.println ( "is not occupied by two or more of your opponent’s Checkers." );
		System.out
				.println ( "* You can move one Checker the count of one die and another Checker" );
		System.out
				.println ( "the count of the other die. Or, you can move one Checker the total " );
		System.out
				.println ( "number showing on both dice, only if the count of one of the dice " );
		System.out
				.println ( "could move your piece to an available point. For example, if you roll 4 and 5, you may" );
		System.out
				.println ( "move one Checker 4 spaces and another Checker 5 spaces. Alternatively," );
		System.out
				.println ( "you may move on Checker the total of 4 and 5, or 9 spaces, only if" );
		System.out.println ( "either the fourth or fifth points are open." );
		System.out
				.println ( "* If you roll Doubles, play the number shown on the dice twice." );
		System.out
				.println ( "For example, if you roll two 5s, use any combination of Checkers to" );
		System.out.println ( "move a total of four 5s." );
		System.out
				.println ( "* When it’s not possible to move the full count of both dice" );
		System.out
				.println ( "and only one Checker can be played, you must move that Checker. " );
		System.out
				.println ( "If either number can be played but not both, play the larger one." );
		System.out
				.println ( "If neither number can be played, you lose your turn." );
		
		System.out.println ( " " );
		System.out.println ( " " );
		System.out.println ( " " );
		System.out.println ( " " );
		
		System.out.println ( "Press R to reuturn to Main Menu" );
		
		Scanner keyboard = new Scanner ( System.in );
		String userChoice2 = keyboard.nextLine ();
		
		if ( userChoice2.equalsIgnoreCase ( "R" ) )
		{
			printMenu ();
		}
		else
		{
			Rules ();
			
			System.out.println ( "Invalid Input" );
			
		}
		
	}
	
	public static boolean newGame()
	{
		
		return true;
		
	}
	
	public static void quit()
	{
		System.exit ( 0 );
	}
	
	public static void main( String[] args )
	{
		
		Board b = new Board ();
		HumanPlayer P = new HumanPlayer ();
		b.welcome ();
		String userInput = b.printMenu ();
		while ( ! ( userInput.equalsIgnoreCase ( "Q" ) ) )
		{
			if ( userInput.equalsIgnoreCase ( "N" ) )
			{
				b.initialiseBoard ();
				b.printBoard ();
				// as long as the exit condition isn't invoked, the loop condition will always be true
				while ( P.makeMove ( b.rollDice (), b.rollDice () ) )
					b.printBoard ();
				
			}
			
			else if ( userInput.equalsIgnoreCase ( "R" ) )
			{
				Rules ();
				
			}
			else
			{
				System.out.println ( "Invalid Option" );
				
			}
			userInput = b.printMenu ();
		}
		System.out.println ( " Thank you ! see you soon  " );
		quit ();
		
	}
}
