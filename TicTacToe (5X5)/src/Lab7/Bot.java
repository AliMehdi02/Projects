package Lab7;

public class Bot
{
	private int BotSpot;

	public Bot( int BotSpot) //Making The bot player
	{
		this.BotSpot = BotSpot;
	}
	public int knowBotSpot()
	{
		return BotSpot; //Know the spot of bot
	}
	public void BotPlay(The_GUI_Board board)
	{
		SmartBot(board);  // calling the method for the bot to play smart (AI)
	}   


	// medium level player
	private void SmartBot(The_GUI_Board board)
	{
		int SpotForUser = The_GUI_Board.Human_Player;
		if (SpotForUser == BotSpot)
			SpotForUser = The_GUI_Board.Bot_Player;


		// find winning position
		for (int i = 0; i < board.getBoard().length; i++)  // if i is valid
			for (int j = 0; j < board.getBoard().length; j++) // if j is valid
				if (board.getBoard()[i][j] == 0) // if intersection is empty
				{
					// try to place
					board.putvalueXorO(i, j, BotSpot); // calling method putvalueXorO
					if (board.MaximumLlength(i, j) == 5) //calling method MaximumLlength
						return;
					// recover
					board.putvalueXorO(i, j, 0); 
				} 

		// the below method finds if user is winning that is only 1 box left to win
		for (int i = 0; i < board.getBoard().length; i++) // if i is in valid
			for (int j = 0; j < board.getBoard().length; j++) // if j is valid
				if (board.getBoard()[i][j] == 0) // if intersection is empty
				{
					//block the user from winning
					board.putvalueXorO(i, j, SpotForUser);
					if (board.MaximumLlength(i, j) == 5)
					{
						// place here the spot of the Bot which will prevent user from winning
						board.putvalueXorO(i, j, BotSpot);
						return;
					}
					// recover
					board.putvalueXorO(i, j, 0); // recovering the spot
				} 
		// place computers spot 5 in vertically or horizontally
		for (int length = 5; length >= 1; length--) 
		{
			for (int i = 0; i < board.getBoard().length; i++) // i is valid and in range
				for (int j = 0; j < board.getBoard().length; j++) // j is valid and in range
					if (board.getBoard()[i][j] == 0) // if the intersection is empty
					{
						// put the bot spot
						board.putvalueXorO(i, j, BotSpot);
						if (board.MaximumLlength(i, j) == length)
							return;
						// recover
						board.putvalueXorO(i, j, 0); // recovering the spot
					} 
		}
		// play randomly if above dosen't work-out the below method does that.
		int row = 0; //initialising variable
		int column = 0; //initialising variable

		do // do at least once 
		{
			row = (int)(5 * Math.random()); // randomly choose a row index
			column = (int)(5 * Math.random()); // randomly choose a column index
		} while (board.getBoard()[row][column] != 0); // while intersection is not empty
		board.putvalueXorO(row, column, BotSpot); // put the spot for bot
	}
}
