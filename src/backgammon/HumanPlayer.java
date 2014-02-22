package backgammon;

import java.util.Scanner;

public class HumanPlayer
{
	static int playerDeterminator;
	
	HumanPlayer()
	{
		playerDeterminator = -1;// initialise to -1 because the first move will increase it to 0
	}
	
	int getPlayer()		// returns 1 for white and -1 for black. that's because in the array white men are represented by 1, and black men by -1
	{
		return ( int ) Math.pow ( -1, playerDeterminator % 2 );	// this is a safe cast, since the power is always 0 or 1
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
		
		playerDeterminator++ ;
		
		System.out.println ( playerDeterminator % 2 + "\n" );
		
		if ( playerDeterminator % 2 == 1 )
			System.out.println ( "Black Turn" );
		else System.out.println ( "White Turn" );
		return Move ( die2, die2 );
	}
	
	public boolean Move( int die1, int die2 )
	{
		int movingColour = getPlayer ();
		Scanner bUserMove = new Scanner ( System.in );
		int position;
		int move;
		boolean flag = false;
		
		System.out.println ( "You rolled a " + die1 + " and a " + die2 );
		System.out
				.print ( "Enter pairs of numbers to Move: example: '1-2 2-3'\n" );
		
		while ( !flag )
		{
			String userInput = bUserMove.nextLine ();					// raw input from the user
			String[] separateUserMoves = userInput.split ( " " );		// stores all the pairs
			
			for ( int counter = 0; counter < separateUserMoves.length; counter++ )
			{
				String[] pair = separateUserMoves[counter].split ( "-" );// stores a position-move pair
				position = Integer.parseInt ( pair[0] );
				move = movingColour * Integer.parseInt ( pair[1] );
				
				if ( position > -1 && position < 26 )
				{
					//checks if a black man exists in that position
					// This checks if the user chose a black man to move
					if ( Board.playingBoard[position] >= movingColour )
					{
						if ( ( position + move ) < 0 )
						{
							System.out
									.println ( " Array out of bounds retry move " );
							flag = false;
							
						}
						
						//If the peg has no men
						if ( Board.playingBoard[position + move] == 0 )
						{
							Board.playingBoard[position] -= movingColour;
							Board.playingBoard[position - move] += movingColour;// adds a new black man to the board
							System.out.println ( " Valid move" );
							flag = true;
						}
						
						// if 1 man in the position
						else if ( Board.playingBoard[position + move] == ( -1 * movingColour ) )
						{
							
							System.out.println ( "You killed him" );
							Board.playingBoard[position + move] += 2 * movingColour; // Replace opponent man with user Man
							//updates bar for opponent
							Board.playingBoard[24 + ( ( playerDeterminator + 1 ) % 2 )] += ( -1 * movingColour );
							flag = true;
						}
						
						// this final else statement is triggered if the man is
						// moved but there is a stack of opponent men
						else
						{
							System.out
									.println ( "Invalid move You are attempting to kill more then 1 man!" );
							System.out.print ( "Enter number to Move\n" );
							flag = false;// So user has to try again
						}
					}
				}
				else if ( position < 0 )
					return false;               // exit condition
			}
			
		}
		return flag;            // loop only exits when flag == true
		
	}
	
	//	public boolean whiteMove( int die1, int die2 )
	//	{
	//		Scanner wUserMove = new Scanner ( System.in );
	//		int position;
	//		int move = die1 + die2;
	//		boolean flag = false;
	//		System.out.println ( "White Turn" );
	//		System.out.println ( "You rolled a " + move );
	//		System.out.print ( "Enter number to Move\n" );
	//		position = wUserMove.nextInt () - 1;
	//		
	//		while ( !flag )
	//		{
	//			if ( position > -1 && position < 26 )
	//			{
	//				// This checks if the user chose a white man to move
	//				if ( Board.playingBoard[position] > 0 )
	//				{
	//					Board.playingBoard[position] += -1;
	//					
	//					if ( ( position + move ) > 23 )
	//					{
	//						System.out
	//								.println ( " Array out of bounds retry move \n " );
	//						flag = false;
	//						
	//					}
	//					
	//					if ( Board.playingBoard[position + move] > -1 )
	//					{
	//						Board.playingBoard[position + move] += 1;// adds a new
	//						// white man to
	//						// the board
	//						System.out.println ( " Valid move" );
	//						flag = true;
	//					}
	//					
	//					// if 1 black in the position the white moved
	//					else if ( Board.playingBoard[position + move] == -1 )
	//					{
	//						
	//						System.out.println ( "You killed him" );
	//						Board.playingBoard[position + move] += 2; // Replace Black man
	//						// with a white Man
	//						// if and only if
	//						// has 1 black piece
	//						Board.playingBoard[25] += 1;
	//						flag = true;
	//					}
	//					
	//					// this final else statement is triggered if the white is
	//					// moved but there is no white that exists in that position
	//					else
	//					{
	//						System.out
	//								.println ( "Invalid move You are attempting to kill more then 1 black man!" );
	//						System.out.print ( "\nEnter number to Move\n" );
	//						position = wUserMove.nextInt () - 1;
	//						Board.playingBoard[position] += 1;
	//						flag = false;// So user has to try again
	//					}
	//				}
	//			}
	//			else if ( position < 0 )
	//				return false;               // exit condition
	//				
	//		}
	//		return flag; // see above
	//	}
}
