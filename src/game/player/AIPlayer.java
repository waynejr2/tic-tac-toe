package game.player;

import game.Board;
import game.Mark;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player implements AutoPlayable {

	private ArrayList<Board> badBoards;

	class Move {
		int row;
		int col;

		Move( int row, int col ) {
			this.row = row;
			this.col = col;
		}
	}

	public AIPlayer( Board board, Mark mark ) {
		super( board, mark );
		badBoards = new ArrayList<Board>();
	}

	/**
	 * Train the AI player to get better by collecting lots of examples of bad
	 * moves
	 * 
	 * @param numIter
	 *            - the number of iterations to train for
	 */
	public void train( int numIter ) {

		// Create a random player for testing purposes
		RandomPlayer rp = new RandomPlayer( this.theBoard, myOpponent.getMark() );
		rp.setOpponentPlayer( this );

		// Repeat numIter moves
		for ( int i = 0; i < numIter; i++ ) {
			while ( theBoard.isPlayable() ) {
				rp.makeMove();
				this.makeMove();
			}
			theBoard.clear();
		}
	}

	/*
	 * The controlling program will need to add the bad board that the agent
	 * took when it learns that the other team member won
	 */
	public void addBadBoard( Board b, int winRow, int winColumn ) {
		Board boardCopy = new Board( b );
		boardCopy.clear( winRow, winColumn );
		if ( !checkForBadBoard( boardCopy ) )
			badBoards.add( boardCopy );
	}

	private boolean checkForBadBoard( Board b ) {
		for ( Board bad : badBoards ) {
			if ( bad.equals( b ) )
				return true;
		}
		return false;
	}

	public boolean makeMove() {
		if ( !theBoard.isPlayable() )
			return false;

		System.out.println( "Checking against bad boards: " + badBoards.size() );

		// Gather a list of all potential moves that are not doomed
		ArrayList<Move> movesToTry = new ArrayList<Move>();
		for ( int row = 0; row < 3; row++ ) {
			for ( int col = 0; col < 3; col++ ) {
				if ( theBoard.isEmpty( row, col ) ) {
					Board boardTest = new Board( this.theBoard );
					boardTest.setMark( row, col, myMark );
					if ( !checkForBadBoard( boardTest ) )
						movesToTry.add( new Move( row, col ) );
				}
			}
		}

		// First, get an empty square
		Random rand = new Random();
		Move move = null;

		if ( movesToTry.isEmpty() ) {
			// We're f'ed, so try any move at random
			do {
				move = new Move( rand.nextInt( 3 ), rand.nextInt( 3 ) );
			} while ( !theBoard.isEmpty( move.row, move.col ) );
		}
		else {
			// Get one of the good potential moves
			move = movesToTry.get( rand.nextInt( movesToTry.size() ) );
		}

		return theBoard.setMark( move.row, move.col, myMark );

	}
}
