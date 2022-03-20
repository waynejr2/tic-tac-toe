package game.player;

import game.Board;
import game.Mark;

public class Player {
	public static enum PlayerType {
		HUMAN, RANDOM, AI;
	}

	public static enum CompPlayerType {
		RANDOM("Random"), BLOCKER("Blocker"), AI("AI Player");
		private String s;

		CompPlayerType( String s ) {
			this.s = s;
		}

		public String toString() {
			return this.s;
		}
	}

	protected Board theBoard;
	protected Mark myMark;
	protected Player myOpponent;

	public Player( Board board, Mark mark ) {
		this.theBoard = board;
		this.myMark = mark;
		this.myOpponent = null;
	}

	public void setOpponentPlayer( Player opponent ) {
		this.myOpponent = opponent;
	}

	public boolean move( int row, int col ) {
		boolean result = theBoard.setMark( row, col, myMark );

		// If my opponent is actually an AI player, then let's inform them
		// that they made a bad move IF my move won!
		if ( theBoard.isWinFor( this ) && myOpponent instanceof AIPlayer ) {
			( (AIPlayer) myOpponent ).addBadBoard( theBoard, row, col );
		}
		return result;
	}

	public Mark getMark() {
		return myMark;
	}

	public String toString() {
		return myMark.toString();
	}

	public static Player playerFactory( Board b, PlayerType p, Mark m ) {
		switch ( p ) {
		case HUMAN:
			return new Player( b, m );
		case RANDOM:
			return new RandomPlayer( b, m );
		case AI:
			return new AIPlayer( b, m );
		}

		return null;
	}

}
