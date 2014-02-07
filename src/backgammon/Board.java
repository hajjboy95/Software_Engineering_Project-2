package backgammon;

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
	
	int playingBoard[] = new int[NUMBER_OF_PEGS + BAR + OFF];
	
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
    /**
     * self explanatory
     * @param die1 the first die values
     * @param die2 second die values
     */
	public void makeMove(int die1, int die2)
	{
        
		playerDeterminator++;
		
		System.out.println(playerDeterminator%2 + "\n");
		
		if (playerDeterminator%2 == 1)
			whiteMove(die1,die2);
		else
			blackMove(die2,die2);
		
	}
    
	
	public void blackMove(int die1, int die2)
	{
		Scanner bUserMove = new Scanner(System.in);
		int position;
		int move = die1 + die2;
		boolean flag = false;
		System.out.println("Black Turn");
		System.out.println("You rolled a " + move);
		System.out.print("Enter number to Move\n");
		position = bUserMove.nextInt() - 1;
        
		while (!flag) {
			if (position > -1 && position < 26) {
				//checks if a black man exists in that position
				//if (playingBoard[posi])
				// This checks if the user chose a black man to move
				if (playingBoard[position] < 0) {
					playingBoard[position] += 1;
                    
					if ((position - move) < 0) {
						System.out.println(" Array out of bounds retry move ");
						flag = false;
                        
					}
                    
                    
					//If the peg has no men
					if (playingBoard[position - move] < 1) {
                        playingBoard[position - move] -= 1;// adds a new
                        // white man to
                        // the board
                        System.out.println(" Valid move");
                        flag = true;
                    }
                    
					
					// if 1 white man in the position black man moved
					else if (playingBoard[position - move] == 1) {
                        
						System.out.println("You killed him");
						playingBoard[position + move] -= 2; // Replace Black man
                        // with a white Man
                        // if and only if
						//updates bar for black man									// has has 1 piece
						playingBoard[24] -= 1;
						flag = true;
					}
                    
					// this final else statement is triggererd if the black man is
					// moved but there is no black that exists in that position and theres a stack of white men
					else {
						System.out.println("Invalid move You are attempting to kill more then 1 man!");
						System.out.print("Enter number to Move\n");
						position = bUserMove.nextInt() - 1;
						playingBoard[position] -= 1;
						flag = false;// So user has to try again
					}
				}
			}
			else{
				flag = false;
				System.out.println("Within Else Statement");
				
			}
            
		}
        
	}
    
	public void whiteMove(int die1, int die2) {
		Scanner wUserMove = new Scanner(System.in);
		int position;
		int move = die1 + die2;
		boolean flag = false;
		System.out.println("White Turn");
		System.out.println("You rolled a " + move);
		System.out.print("Enter number to Move\n");
		position = wUserMove.nextInt() - 1;
        
		while (!flag) {
			if (position > -1 && position < 26) {
				// This checks if the user chose a white man to move
				if (playingBoard[position] > 0) {
					playingBoard[position] += -1;
                    
					if ((position + move) > 23) {
						System.out.println(" Array out of bounds retry move \n ");
						flag = false;
                        
					}
                    
					
					if (playingBoard[position + move] > -1) {
                        playingBoard[position + move] += 1;// adds a new
                        // white man to
                        // the board
                        System.out.println(" Valid move");
                        flag = true;
                    }
                    
					
					// if 1 black in the position the white moved
					else if (playingBoard[position + move] == -1) {
                        
						System.out.println("You killed him");
						playingBoard[position + move] += 2; // Replace Black man
                        // with a white Man
                        // if and only if
                        // has 1 black piece
						playingBoard[25] += 1;
						flag = true;
					}
                    
					// this final else statement is triggered if the white is
					// moved but there is no white that exists in that position
					else {
						// playingBoard[position+move] +=0;
						System.out.println("Invalid move You are attempting to kill more then 1 black man!");
						System.out.print("\nEnter number to Move\n");
						position = wUserMove.nextInt() - 1;
						playingBoard[position] += 1;
						flag = false;// So user has to try again
					}
				}
			}
            
		}
        
	}
	
	public static void main( String[] args )
	{
		
		Board b  = new Board();
		b.initialiseBoard();
		b.printBoard();
		
		while (1==1)
		{
			
            b.makeMove(b.rollDice(), b.rollDice());
            b.printBoard();
		}
	}
}
