/**
 * @author Ruifeng Wang
 * @version 1.0
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package rwang_p1;

public class TicTacToeX {
	
	private char[][] board; // 2D array to store the game board
	private int xCounter; // Counts number of X in a row, column, diagonal
	private int oCounter; // Counts number of O in a row, column, diagonal
	private int dimension; // Stores user inputed board dimension
	
	/**
	 *	Constructor to create the game board based on user specification
	 * @param dimension		User inputed value for game board dimension 
	 */
	public TicTacToeX(int dimension) {
		this.dimension = dimension;
		board = new char[dimension][dimension];
	}
	
	/**
	 *	Gets the game board
	 * @return	The 2D array of the current game
	 */
	public char[][] getBoard() {
		return board;
	}
	
	/**
	 *	Checks if the move is valid, if so, inputs the move on the board
	 * @param x			X coordinate of the move
	 * @param y			Y coordinate of the move
	 * @param move		Which player (X, O) is making the move
	 * @return			Returns true if valid move is made, otherwise false
	 * 					if out of bound or non-empty
	 */
	public Boolean addMove(int x, int y, char move)  {
		if (x >= dimension || x < 0 || y >= dimension || y < 0) {
			return false;
		} else {
			if (board[x][y] == 0) {
				board[x][y] = move;
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 *	Gets the dimension of the board
	 * @return	The integer dimension of the board (board is square)
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 *	Checks if there is a winner
	 * @return Returns true if either X or O won, otherwise false
	 */
	public boolean checkWinner() {
		if (checkRow(xCounter, oCounter) == 'X'
			|| checkColumn(xCounter, oCounter) == 'X'
			|| checkDiagonalUp(xCounter, oCounter) == 'X' 
			|| checkDiagonalDown(xCounter, oCounter) == 'X') {
			return true;
		} else if (checkRow(xCounter, oCounter) == 'O' 
					|| checkColumn(xCounter, oCounter) == 'O'
					|| checkDiagonalUp(xCounter, oCounter) == 'O' 
					|| checkDiagonalDown(xCounter, oCounter) == 'O') {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 *	Checks each row for a winner
	 * @param xCounter	Counts number of X in a row
	 * @param oCounter	Counts number of O in a row
	 * @return			Returns X if X has filled a row, returns O if O 
	 * 					has filled a row, otherwise returns blank
	 */
	private char checkRow(int xCounter, int oCounter) {
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				if(board[row][col] == 'X') {
					xCounter++;
				} else if (board[row][col] == 'Y') {
					oCounter++;
				}
				if (xCounter == dimension) {
					return 'X';
				} else if (oCounter == dimension){
					return 'O';
				}	
			}
			xCounter = 0;
			oCounter = 0;
		}
		return ' ';
	}
	
	/**
	 *	Checks each column for a winner
	 * @param xCounter	Counts number of X in a column
	 * @param oCounter	Counts number of O in a column
	 * @return			Returns X if X has filled a column, returns O if O 
	 * 					has filled a column, otherwise returns blank
	 */
	private char checkColumn(int xCounter, int oCounter) {
		for (int col = 0; col < dimension; col++) {
			for (int row = 0; row < dimension; row++) {
				if(board[row][col] == 'X') {
					xCounter++;
				} else if (board[row][col] == 'O') {
					oCounter++;
				}
				if (xCounter == dimension) {
					return 'X';
				} else if (oCounter == dimension){
					return 'O';
				}	
			}
			xCounter = 0;
			oCounter = 0;
		}
		return ' ';
	}
	
	/**
	 *	Checks the diagonal from bottom left to top right for a winner
	 * @param xCounter	Counts number of X in the diagonal
	 * @param oCounter	Counts number of O in the diagonal
	 * @return			Returns X if X has filled the diagonal, returns O if O 
	 * 					has filled the diagonal, otherwise returns blank
	 */
	private char checkDiagonalUp(int xCounter, int oCounter) {
		for (int index = 0; index < dimension; index++) {
			if(board[dimension - index - 1][index] == 'X') {
				xCounter++;
			} else if (board[dimension - index - 1][index] == 'O') {
				oCounter++;
			}
			if (xCounter == dimension) {
				return 'X';
			} else if (oCounter == dimension){
				return 'O';
			}	
		}
		return ' ';
	}
	
	/**
	 *	Checks the diagonal from top left to bottom right for a winner
	 * @param xCounter	Counts number of X in the diagonal
	 * @param oCounter	Counts number of O in the diagonal
	 * @return			Returns X if X has filled the diagonal, returns O if O 
	 * 					has filled the diagonal, otherwise returns blank
	 */
	private char checkDiagonalDown(int xCounter, int oCounter) {
		for (int index = 0; index < dimension; index++) {
			if(board[index][index] == 'X') {
				xCounter++;
			} else if (board[index][index] == 'O') {
				oCounter++;
			}
			if (xCounter == dimension) {
				return 'X';
			} else if (oCounter == dimension){
				return 'O';
			}	
		}
		return ' ';
	}

}
