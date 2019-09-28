/**
 * @author Ruifeng Wang
 * @version 1.0
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package rwang_p1;

import java.util.Scanner;

public class P1X {
	
	/**
	 * This program allows 2 players to play Tic-Tac-Toe on a grid of 9 spaces
	 * (a 3x3 board). Players alternate between X or O, taking turns. A player 
	 * wins when they fill an entire row, column, or diagonal. The game ends 
	 * in a tie if all spaces have been filled.
	 * @param args A string array containing the command line arguments
	 */		
	public static void main(String[] args) {
		
		char result; // Keeps track of who won a game (X or O)
		char keepPlaying = 'Y'; // Initializing continuous play
		int scoreTrack = 0; // Records who won a particular game. X = 1, Y = 2,
							// Tie = 3
		int xWins = 0; // Keeps track of total number of games player X has won
		int oWins = 0; // Keeps track of total number of games player O has won
		int ties = 0; // Keeps track of total number of tie games that have
		  			  // happened
		int dimension = 0; // Initializes the user specified board dimension
		
		Scanner keyboard = new Scanner(System.in);
		
		printWelcome();

		while (keepPlaying == 'Y' || keepPlaying == 'y') {	
			System.out.print("How big of a board do you want to play on? "
					+ "(Pick an odd number between 3-25). ");
			dimension = keyboard.nextInt();
			while(dimension % 2 != 1 || dimension < 3) {
				System.out.print("How big of a board do you want to play on? "
						+ "(Pick an odd number between 3-25). ");
				dimension = keyboard.nextInt();
			}
			TicTacToeX game = new TicTacToeX(dimension);
			printBoard(game);
			result = fullGame(game, keyboard);
			scoreTrack = printResult(result);
			if(scoreTrack == 1) {
				xWins += 1;
			} else if (scoreTrack == 2) {
				oWins += 1;
			} else {
				ties += 1;
			}
			gameStats(xWins, oWins, ties);
			System.out.print("Do you want to play again? ");
			keepPlaying = keyboard.next().charAt(0);
		}
		printGoodbye();
		keyboard.close();
	}
	
	/**
	 *	Prints the welcome message to the game. 
	 */
	public static void printWelcome( ) {
		System.out.println("Welcome to TicTacToe!\n");
		System.out.println("Please input the location you would like to place"
				+ " your move!");
		System.out.println("Fill a whole row, column or diagonal to win.");
		System.out.println("The game is a tie if all spaces get filled!\n");
	}
	
	/**
	 * Prints the goodbye message to the game.
	 */
	public static void printGoodbye() {
		System.out.println("\nThanks for playing the game!");
		System.out.println("Hopefully you both enjoyed.");
		System.out.println("Please play again whenever you're ready.");
	}
	
	/**
	 * Prints out the game board for the players to view
	 * @param game	The object of the current game being played
	 */
	public static void printBoard(TicTacToeX game) {
		char[][] single = game.getBoard();
		System.out.println();
		for (int i = 0; i < single.length; i++) {
			if (i == 0) {
				System.out.printf("%5d", i);
			} else {
				System.out.printf("%3d", i);
			}
		}
		System.out.println();
		
		for (int row = 0; row < single.length; row++) {
			System.out.printf("%3d", row);
			for (int col = 0; col < single.length; col++) {
				if(single[row][col] == 0) {	
					System.out.printf("%2c |", single[row][col]);	
				} else {
					System.out.printf("%2c|", single[row][col]);	
				}
			}
			System.out.println();
			System.out.print("   ");
			for (int i = 0; i < 3 * game.getDimension(); i++) {
				System.out.print("-");
			}
			System.out.println();
		}		
	}
	
	/**
	 * Plays a full game of Tic-Tac-Toe.
	 * @param game			The object of the current game being played
	 * @param keyboard		Scanner object
	 * @return				Returns the player who won (X, O, or Z for tie)
	 */
	public static char fullGame(TicTacToeX game, Scanner keyboard) {
		
		char player; // The character to input for the current move of the game
		boolean move = false; // Checks to see if a valid move was made
		boolean gameWinner = false; // Checks to see if someone has won
		String playerTracker = "xMove"; // Changes based on which players turn
										// it is. Always starts with X
		int moveTracker = 0; // Counts the total number of moves in the game
		
		// Continues playing as long as there is no game winner
		while(!gameWinner) {
			if (playerTracker.equals("xMove")) {
				player = 'X';
				// Continues as player "X" turn as long as valid 
				// move has not been made yet
				while (!move) {
					move = singleMove(player, game, keyboard);
					gameWinner = game.checkWinner();
				}
				// Valid move was made, now it is player "O" turn
				playerTracker = "oMove";
				// Resets to move not being made
				move = false;
				// Tracks a valid move was made
				moveTracker++;
				
				// If X is the winner or gameboard is full, tracks accordingly
				if(gameWinner) {
					return 'X';
				} else if(moveTracker == game.getDimension() * 
						game.getDimension()) {
					return 'Z';
				}
			} else {
				player = 'O';
				// Continues as player "O" turn as long as valid 
				// move has not been made yet
				while (!move) {
					move = singleMove(player, game, keyboard);
					gameWinner = game.checkWinner();
				} 
				// Valid move was made, now it is player "X" turn
				playerTracker = "xMove";
				// Resets to move not being made
				move = false;
				// Tracks a valid move was made
				moveTracker++;
				
				// If O is the winner or gameboard is full, tracks accordingly
				if(gameWinner) {
					return 'O';
				} else if (moveTracker == game.getDimension() * 
						game.getDimension()) {
					return 'Z';
				}
			}
		}
		return 'Z';
	}
	
	/**
	 * Allows the current player to make a single move
	 * @param player		Character (X, O) of the current player
	 * @param game			The object of the current game being played
	 * @param keyboard		Scanner object
	 * @return				Returns True if move was successfully made
	 * 						False if move was made in invalid spot
	 */
	public static Boolean singleMove(char player, TicTacToeX game, 
			Scanner keyboard) {
		int row; // Stores user input of row
		int column; // Stores user input of column

		System.out.println(player + ", it is your turn.");
		System.out.print("Which row? ");
		row = keyboard.nextInt();
		System.out.print("Which column? ");
		column = keyboard.nextInt();
		if(!game.addMove(row, column, player)) {
			System.out.println("Bad location, try again...");
			printBoard(game);
			return false;
		}
		printBoard(game);
		return true;
	}
	
	/**
	 * Calculates result of whether X won, O won, or there was a tie and prints
	 * a message to the players
	 * @param result	Receives X, O, Z based on X winning, O winning, tie
	 * @return			Returns 1 for X winning, 2 for O winning, 3 for tie
	 */
	public static int printResult(char result) {
		if(result == 'X') {
			System.out.println("X won!");
			return 1;
		} else if (result == 'O') {
			System.out.println("O won!");
			return 2;
		} else {
			System.out.println("No winner - it was a tie!");
			return 3;
		}
	}
	
	/**
	 * Calculates, stores and prints cumulative results
	 * @param xWins		Total number of times X has won
	 * @param oWins		Total number of times O has won
	 * @param ties		Total number of tie games
	 */
	public static void gameStats(int xWins, int oWins, int ties) {
		System.out.println("Game Stats");
		System.out.println("X has won " + xWins + " games.");
		System.out.println("O has won " + oWins + " games.");
		if (ties == 1) {
			System.out.println("There has been 1 tie game.");
		} else {
			System.out.println("There have been " + ties + " tie games.");
		}
	}
}
