package backgammon;

import java.util.Scanner;

public class Test_test
{
	public static void main( String[] args )
	{
		Scanner bUserMove = new Scanner ( System.in );
		bUserMove.useDelimiter ( "\n" );
		String userInput = bUserMove.next ();					// raw input from the user
		String[] separateUserMoves = userInput.split ( " " );		// stores all the pairs
		for ( int counter = 0; counter < separateUserMoves.length; counter++ )
		{
			String[] pair = separateUserMoves[counter].split ( "-" );			// stores a position-move pair
			int position = Integer.parseInt ( pair[0] );
			int move = Integer.parseInt ( pair[1] );
			System.out.println ( "you move from position " + position
					+ " the following amount " + move );
		}
	}
}
