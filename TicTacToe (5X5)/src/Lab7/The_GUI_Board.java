package Lab7;
//Necessary Importation 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class The_GUI_Board
{
	public static int Human_Player = 1; // This is for the human player.
	public static int Bot_Player = 2; // this is for the computer player

	public int[][] board; //this is the board

	public The_GUI_Board() //constructor
	{
		board = new int[5][5]; // 5 by 5 board constructor
	}

	// the below method Returns the board
	public int[][] getBoard()
	{
		return board; // board is returned
	}

	public int getvalue(int row, int column) // get the value
	{
		return board[row][column]; //returns the row and column of the board
	}

	public void putvalueXorO(int row, int column, int value) //this method is used to assign value
	{
		board[row][column] = value; // value is equal to the row and the column of board
	}

	public boolean isitfilled()// this method check if board is full or not
	{
		for (int row = 0; row < board.length; row++){ // if row is in range
			for (int column = 0; column < board.length; column++) //if column is in range
				if (board[row][column] == 0) // if it is empty return false
					return false;  //false returned
		}
		return true;  //if full true is returned
	}

	// get the line length at (r, c) and the direction
	public int getLineLength(int row, int column, int row_dir, int column_dir)
	{
		if (row < 0 || row >= board.length ||
				column < 0 || column >= board.length)  // if the row or column is out of index or invalid return 0
			return 0; // 0 is returned
		if (board[row][column] == 0) // if the intersection is empty return 0
			return 0; //  0 is returned 

		int length = 1; //counts length
		int on_row = row + row_dir; //adding offset to given row to get next row
		int on_column = column + column_dir; //adding offset to given column to get next column

		while (on_row >= 0 && on_row < board.length &&
				on_column >= 0 && on_column < board.length &&
				board[on_row][on_column] == board[row][column]) //if row and column is valid  
		{
			length++; //counts length
			on_row += row_dir; //now it adds   offset to get next row, on_row is the current row
			on_column += column_dir; //adds offset to get next column, on_column is the current column
		}

		on_row = row - row_dir; //adding offset to given row to get next row
		on_column = column - column_dir; //adding offset to given column to get next column
		while (on_row >= 0 && on_row < board.length &&
				on_column >= 0 && on_column < board.length &&
				board[on_row][on_column] == board[row][column]) // if row and column are valid
		{
			length++; //counts length
			on_row -= row_dir; //now it adds   offset to get next row, on_row is the current row
			on_column -= column_dir; //adds offset to get next column, on_column is the current column
		}
		return length; //length is returned
	}

	// this get the maximum length of the line at (r, c)
	public int MaximumLlength(int row, int column)
	{
		if (row < 0 || row >= board.length ||
				column < 0 || column >= board.length) // if the row or column is out of range or invalid return 0
			return 0; // 0 is returned
		if (board[row][column] == 0) // if location is empty return 0
			return 0; //0 is returned
		int maximumLlenght = 0; //initialising variable

		//checking horizontally vertically and diagonally, total of 4 direction
		int[] row_dir = {0, 1, 1, 1}; //offset of surrounding cell
		int[] column_dir = {1, 0, 1, -1}; // offset of surrounding cell
		//this loop checks all adjacent cells to given locations
		for (int i = 0; i < 4; i++) {  //until i is less than 4
			int length = getLineLength(row, column, row_dir[i], column_dir[i]); // the method getLineLength is called
			if (length > maximumLlenght) // if length is greater than maximumLlength
				maximumLlenght = length; // length is assigned to maximumLlength    
		}        
		return maximumLlenght; //maximumLlength is returned
	}

	//The below method checks if someone won
	public int getifWon()
	{
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++)
				if (MaximumLlength(row, column) == 5)
					return board[row][column];
		}
		return 0;//returned 0 if no one won
	}

	public static class playingboard extends JFrame implements ActionListener{
		private static final long serialVersionUID = 1L;

		public The_Game Game = null;
		public playingboard(final The_Game Lab7Game)
		{
			this.Game = Lab7Game;  // assigning a variable name for the game object
			setLayout(new BorderLayout());
			JButton[][] ButtonGrid = Lab7Game.getButtonGrid(); //getting button grid
			JLabel top = new JLabel ();
			JPanel topp = new JPanel();
			JPanel firstpanel = new JPanel(new GridLayout(5, 5)); //creating new panel with grid 5 by 5 

			for (int i = 0; i < 5; i++) // until i is less than 5
			{
				for (int j = 0; j < 5; j++) // until j is less than 5
				{
					ButtonGrid[i][j] = new JButton(); // creating button in line with button grid 
					ButtonGrid[i][j].addActionListener(this);
					firstpanel.add(ButtonGrid[i][j]); // adding button grid to the first panel.
				}
			}
			top.setText("Human's Turn"); // displaying that its human turn as bot always play automatically
			The_Game.TextDesign(top);  // calling the textdesign method for the style of the text
			topp.add(top); // adding label to panel
			add(topp,BorderLayout.PAGE_START); //adding text to the start of page
			add(firstpanel, BorderLayout.CENTER); // placing first-panel aligned in centre.

			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					setVisible(false);
					Lab7Game.setVisible(true);
					super.windowClosing(e);
				}
			});
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 5; i++) // until i is less than 5
			{
				for (int j = 0; j < 5; j++) // until j is less than 5
				{
					if (e.getSource() == Game.getButtonGrid()[i][j])  //getting button grid
					{
						Game.humanPress(i, j); // calling humanPress object
						return;
					}                        
				}
			}
		}
	}
}
