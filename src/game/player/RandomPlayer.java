package game.player;

import game.Board;
import game.Mark;

import java.util.Random;

public class RandomPlayer extends Player implements AutoPlayable {
	public RandomPlayer( Board board, Mark mark ) {
		super( board, mark );
	}

	public boolean makeMove() {
		if ( !theBoard.isPlayable() )
			return false;

		Random rand = new Random();
		int row, col;

		// Loop until we find a free space randomly
		do {
			row = rand.nextInt( 3 );
			col = rand.nextInt( 3 );
		} while ( !theBoard.isEmpty( row, col ) );

		boolean result = theBoard.setMark( row, col, myMark );

		// If my opponent is actually an AI player, then let's inform them
		// that they made a bad move IF my move won! This is particularly
		// important for the random player if they are training
		if ( theBoard.isWinFor( this ) && myOpponent instanceof AIPlayer ) {
			( (AIPlayer) myOpponent ).addBadBoard( theBoard, row, col );
		}
		return result;
	}
}
