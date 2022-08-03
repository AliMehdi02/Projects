package Lab7;
// Necessary Importations
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// I was able to successfully create the game and a smart bot however i was not able to implement 1 novel feature.
//Whereby a user can choose whether he/she want to be X or O

public class The_Game extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	// the below is main method to run the game
	public static void main(String[] args)
	{
		The_Game Game = new The_Game();
		Game.setSize(655, 280); // the size
		Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Game.setVisible(true); // make it visible  
	}

	//below designing and creating the game options
	JButton[][] ButtonGrid = new JButton[5][5]; // ---creating a 5 by 5 grid
	JButton PlayButton = new JButton("PLAY"); // ---creating a button
	JButton StopButton = new JButton("LEAVE"); //  ---creating a button
	JButton ButtonfO =new JButton("Choose-O-COLOR"); //   ---creating a button
	JButton ButtonfX =new JButton("Choose-X-COLOR"); //  ---creating a button
	JCheckBox ComuterPlays = new JCheckBox("I Want To Let Computer Play First"); // ---creating a check-box
	JPanel firstpanel = new JPanel(); //  ---creating a panel
	JPanel secondpanel = new JPanel(); // ---creating a panel
	JPanel thirdpanel = new JPanel(); // ---creating a panel
	JLabel text = new JLabel(); // ---creating a label
	JLabel text1 = new JLabel(); // ---creating a panel

	int first;  // Variable to know who will play first
	Color O; // Variable to know what colour is chosen for O 
	Color X; // Variable to know what colour is chosen for X
	Bot bot; // Variable bot with type Bot
	The_GUI_Board board; //variable board with type The_GUI_Board
	The_GUI_Board.playingboard playingboard = null; //method playingboard called from The_GUI_Board class

	// The Below method is for getting button grid
	public JButton[][] getButtonGrid() {
		return ButtonGrid;
	}

	//In the method below i am creating a design for buttons to avoid re-writing each time
	public static void ButtonDesign(JButton button)
	{
		button.setSize(100, 50); // the size 100 representing the width and 50 representing the height
		button.setFont(new Font("Cavolini", Font.BOLD, 18)); // the font styles
		button.setBackground(Color.gray); // background colour
		button.setForeground(Color.GREEN); // foreground colour
	}

	//In the method below i am creating a design for labels to avoid re-writing each time
	public static void TextDesign(JLabel Label)
	{
		Label.setForeground(Color.RED); // colour is red
		Label.setFont(new Font("Imprint MT Shadow",Font.ITALIC,15)); // the font styles
		Label.setHorizontalAlignment(JLabel.CENTER);  // aligned in center.
	}

	public The_Game() //constructor
	{
		setTitle("Lab 7 Final Assignment Project"); 
		setLayout(new BorderLayout());
		setLocation(200,200); //location when it opens

		JPanel container = new JPanel(new GridLayout(4,1)); // Creating a grid with 4 rows 1 column

		// Adding play button used for playing game to first panel
		ButtonDesign(PlayButton);
		PlayButton.addActionListener(this);
		firstpanel.add(PlayButton);

		// Adding stop button used for playing game to first panel
		ButtonDesign(StopButton);
		StopButton.addActionListener(this);
		firstpanel.add(StopButton);        

		// Adding to third panel button used for changing colour of 'O'
		ButtonDesign(ButtonfO);
		ButtonfO.addActionListener(this);
		thirdpanel.add(ButtonfO); 

		// Adding to third panel button used for changing colour of 'X'
		ButtonDesign(ButtonfX);
		ButtonfX.addActionListener(this);
		thirdpanel.add(ButtonfX); 

		//Adding to second panel a check-box which clicked will make the bot play first
		ComuterPlays.setForeground(Color.green);
		ComuterPlays.setBackground(Color.gray);
		ComuterPlays.setFont(new Font("Cavolini",Font.BOLD,18));
		secondpanel.add(ComuterPlays);

		//Adding a text label for some information  
		TextDesign(text1);
		text1.setText("Please Choose Your Favoirate Colours For 'X' AND 'O' From The Appropriate Buttons Below");

		//Adding a text label for some information 
		TextDesign(text);
		text.setText("Human Plays First However You Can Change This");

		//Panels background being set
		firstpanel.setBackground(Color.LIGHT_GRAY);
		secondpanel.setBackground(Color.LIGHT_GRAY);
		thirdpanel.setBackground(Color.LIGHT_GRAY);

		// All panels added to the container panel displayed well 
		add(firstpanel,BorderLayout.PAGE_START);
		container.add(text);
		container.add(secondpanel);
		container.add(text1);
		container.add(thirdpanel);

		add(container,BorderLayout.CENTER); // every panel aligned center as container has all panels 

		playingboard = new The_GUI_Board.playingboard(this); //Object being created
	}

	// Below we are telling what happens or what to do when a certain button is clicked
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == StopButton) // if stop button is clicked
		{
			System.exit(0); // exits the GUI
		}   
		else if (e.getSource() == PlayButton) // if play button is clicked
		{
			createNewGame(); // method to create a new game with the empty board and blank buttons.
		}
		else if (e.getSource() == ButtonfO) // if buttonfO is clicked 
		{
			//Allowing the user to choose a colour for 'O'
			Color ColorfO = JColorChooser.showDialog(this, "Choose the color for O of your choice", O);
			if (ColorfO!=null)
				O=ColorfO;
		}
		else if (e.getSource() == ButtonfX) // if buttonfX is clicked 
		{
			//Allowing a user to choose a colour for 'X'
			Color ColorfX = JColorChooser.showDialog(this, "Choose the color for X of your choice", X);
			if (ColorfX!=null)
				X=ColorfX;
		}
	}

	// the method below is used to create a new blank game with empty buttons.
	public void createNewGame()
	{
		playingboard.setVisible(true); // setting the board to be visible
		for (int i = 0; i < ButtonGrid.length; i++)
		{
			for (int j = 0; j < ButtonGrid.length; j++)
			{
				ButtonGrid[i][j].setEnabled(true);
				ButtonGrid[i][j].setText("");
			}
		}
		board = new The_GUI_Board(); // the game being created

		// Below we check whether the bot will play first or the human.
		boolean botfirst = ComuterPlays.isSelected();
		first = (botfirst ? The_GUI_Board.Bot_Player : The_GUI_Board.Human_Player);

		bot = new Bot(botfirst ? The_GUI_Board.Human_Player : The_GUI_Board.Bot_Player);

		// if botfirst is true then bot plays first
		if (botfirst)
		{
			bot.BotPlay(board);  // method Botplay called from Bot class
		}
		displayBoard(); // method on how and what to display on board
	}

	//The below method specifies on how and and what to display on the board
	public void displayBoard()
	{
		for (int i = 0; i < ButtonGrid.length; i++)
		{
			for (int j = 0; j < ButtonGrid.length; j++)
			{
				if (board.getBoard()[i][j] == The_GUI_Board.Human_Player) // If it is human player and human plays first else vicecersa
				{
					ButtonGrid[i][j].setText("X"); // put X
					ButtonGrid[i][j].setForeground(X); // Colour of X as choosen
					ButtonGrid[i][j].setFont(new Font("Castellar",Font.BOLD,50)); // X design
				}
				else if (board.getBoard()[i][j] == The_GUI_Board.Bot_Player) // If it is bot playing after human else viceversa
				{
					ButtonGrid[i][j].setText("O");
					ButtonGrid[i][j].setForeground(O); // Colour of O as choosen
					ButtonGrid[i][j].setFont(new Font("Castellar",Font.BOLD,50));  // O desing
				}
			}
		}
		playingboard.setSize(500,500);  // Size of board
		playingboard.setVisible(true);  // Visible
		playingboard.setLocation(300, 130); // location when displayed
		this.setVisible(false);  // invisible it after pressing ok and then display the options.      
	}

	private void gameisDraw() 
	{
		for (int i = 0; i < ButtonGrid.length; i++)
			for (int j = 0; j < ButtonGrid.length; j++)
				ButtonGrid[i][j].setEnabled(false); // makes all buttons disabled
		JOptionPane.showMessageDialog(this, "DRAW...!!!!"); // the display message
		this.setVisible(true);
		playingboard.setVisible(false); // don't display the board until when user presses play again
	}

	private void whenOneWins(int winner)
	{
		for (int i = 0; i < ButtonGrid.length; i++) // if i is valid
			for (int j = 0; j < ButtonGrid.length; j++) // if j is valid
				ButtonGrid[i][j].setEnabled(false);   // set buttons non-functional

		if (winner == bot.knowBotSpot()) // if the bot won den display some kind of text
		{
			JOptionPane.showMessageDialog(this, "You Lost Better Luck Next Time !!!"); // the message
		}
		else // if human wins display some kind of text
		{
			JOptionPane.showMessageDialog(this, "Hoooooraaayyy You Won !!!"); // the message
		}
		this.setVisible(true);
		playingboard.setVisible(false); // invisible the board
	}

	// fixing errors and detecting some outcomes
	public void humanPress(int row, int col)
	{
		if (board.getBoard()[row][col] != 0) // if a human presses something that is not empty
		{
			JOptionPane.showMessageDialog(this, "Please Click The Empty Box...!!!"); // display this erroe message
			return;
		}

		board.putvalueXorO(row, col, first); //after each value if put, display the same board showing where human played
		displayBoard();

		if (board.getifWon() == first) // if human won
		{
			whenOneWins(first); // if human won the method does necessary steps.
		}
		else if (board.isitfilled())
		{
			gameisDraw(); // method when game is draw
		}
		else 
		{
			bot.BotPlay(board); //after each value if put, display the same board showing where bot played
			displayBoard();

			if (board.getifWon() == bot.knowBotSpot()) // if bot won
			{
				whenOneWins(bot.knowBotSpot()); // if human bot the method does necessary steps.
			}
			else if (board.isitfilled())  // if board has no empty box and no one has won. 
			{
				gameisDraw(); // method when game is draw
			}
		}        
	}
}
