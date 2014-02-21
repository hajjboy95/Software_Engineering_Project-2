package backgammon;

import java.util.Scanner;

public class HumanPlayer
{
	static int playerDeterminator;
	
	HumanPlayer()
	{
		playerDeterminator = 0;
	}
	
	/**
	 * self explanatory
	 * 
	 * @param die1
	 *            the first die values
	 * @param die2
	 *            second die values
	 */
	public boolean makeMove( int die1, int die2 )
	{
		
		boolean returnValue;
		playerDeterminator++ ;
		
		System.out.println ( playerDeterminator % 2 + "\n" );
		
		if ( playerDeterminator % 2 == 1 )
			returnValue = whiteMove ( die1, die2 );
		else returnValue = blackMove ( die2, die2 );
		return returnValue;
	}
	
	public boolean blackMove( int die1, int die2 )
	{
		Scanner bUserMove = new Scanner ( System.in );
		int position;
		int move;
		boolean flag = false;
		System.out.println ( "Black Turn" );
		System.out.println ( "You rolled a " + die1 + " and a " + die2 );
		System.out.print ( "Enter number to Move\n" );
		
		String userInput = bUserMove.nextLine ();					// raw input from the user
		String[] separateUserMoves = userInput.split ( " " );		// stores all the pairs
		String[] pair = separateUserMoves[0].split ( "-" );			// stores a position-move pair
		position = Integer.parseInt ( pair[0] );
		move = Integer.parseInt ( pair[1] );
		
		while ( !flag )
		{
			if ( position > -1 && position < 26 )
			{
				//checks if a black man exists in that position
				// This checks if the user chose a black man to move
				if ( Board.playingBoard[position] < 0 )
				{
					Board.playingBoard[position] += 1;
					
					if ( ( position - move ) < 0 )
					{
						System.out
								.println ( " Array out of bounds retry move " );
						flag = false;
						
					}
					
					//If the peg has no men
					if ( Board.playingBoard[position - move] < 1 )
					{
						Board.playingBoard[position - move] -= 1;// adds a new
						// white man to
						// the board
						System.out.println ( " Valid move" );
						flag = true;
					}
					
					// if 1 white man in the position black man moved
					else if ( Board.playingBoard[position - move] == 1 )
					{
						
						System.out.println ( "You killed him" );
						Board.playingBoard[position + move] -= 2; // Replace Black man
						// with a white Man
						// if and only if
						//updates bar for black man									// has has 1 piece
						Board.playingBoard[24] -= 1;
						flag = true;
					}
					
					// this final else statement is triggererd if the black man is
					// moved but there is no black that exists in that position and theres a stack of white men
					else
					{
						System.out
								.println ( "Invalid move You are attempting to kill more then 1 man!" );
						System.out.print ( "Enter number to Move\n" );
						position = bUserMove.nextInt () - 1;
						Board.playingBoard[position] -= 1;
						flag = false;// So user has to try again
					}
				}
			}
			else if ( position < 0 )
				return false;               // exit condition
				
		}
		return flag;            // loop only exits when flag == true
		
	}
	
	public boolean whiteMove( int die1, int die2 )
	{
		Scanner wUserMove = new Scanner ( System.in );
		int position;
		int move = die1 + die2;
		boolean flag = false;
		System.out.println ( "White Turn" );
		System.out.println ( "You rolled a " + move );
		System.out.print ( "Enter number to Move\n" );
		position = wUserMove.nextInt () - 1;
		
		while ( !flag )
		{
			if ( position > -1 && position < 26 )
			{
				// This checks if the user chose a white man to move
				if ( Board.playingBoard[position] > 0 )
				{
					Board.playingBoard[position] += -1;
					
					if ( ( position + move ) > 23 )
					{
						System.out
								.println ( " Array out of bounds retry move \n " );
						flag = false;
						
					}
					
					if ( Board.playingBoard[position + move] > -1 )
					{
						Board.playingBoard[position + move] += 1;// adds a new
						// white man to
						// the board
						System.out.println ( " Valid move" );
						flag = true;
					}
					
					// if 1 black in the position the white moved
					else if ( Board.playingBoard[position + move] == -1 )
					{
						
						System.out.println ( "You killed him" );
						Board.playingBoard[position + move] += 2; // Replace Black man
						// with a white Man
						// if and only if
						// has 1 black piece
						Board.playingBoard[25] += 1;
						flag = true;
					}
					
					// this final else statement is triggered if the white is
					// moved but there is no white that exists in that position
					else
					{
						System.out
								.println ( "Invalid move You are attempting to kill more then 1 black man!" );
						System.out.print ( "\nEnter number to Move\n" );
						position = wUserMove.nextInt () - 1;
						Board.playingBoard[position] += 1;
						flag = false;// So user has to try again
					}
				}
			}
			else if ( position < 0 )
				return false;               // exit condition
				
		}
		return flag; // see above
	}
}
