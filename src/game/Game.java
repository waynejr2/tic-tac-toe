package game;

import game.player.AIPlayer;
import game.player.Player;
import game.player.Player.PlayerType;

public class Game {
	public static enum GameMode {
		GAME_MODE_ONE_PLAYER("Player vs. Computer"), GAME_MODE_TWO_PLAYER(
				"Player vs. Player");

		private String s;

		GameMode( String s ) {
			this.s = s;
		}

		public String toString() {
			return this.s;
		}
	}

	private Board theBoard;
	private Player[] players;
	private int iCurPlayer;

	public Game( PlayerType p1, PlayerType p2 ) {
		this.theBoard = new Board();
		players = new Player[2];
		players[0] = Player.playerFactory( theBoard, p1, Mark.X );
		players[1] = Player.playerFactory( theBoard, p2, Mark.O );
		players[0].setOpponentPlayer( players[1] );
		players[1].setOpponentPlayer( players[0] );
		iCurPlayer = 0;
	}

	public Board getBoard() {
		return theBoard;
	}

	public Player getPlayerNum( int i ) {
		return players[i];
	}

	public Player getCurPlayer() {
		return players[iCurPlayer];
	}

	public void nextPlayer() {
		iCurPlayer = ( iCurPlayer + 1 ) % 2;
	}

	public boolean trainAIPlayer( int numIter ) {
		AIPlayer p = null;
		if ( players[0] instanceof AIPlayer )
			p = (AIPlayer) players[0];
		else if ( players[1] instanceof AIPlayer )
			p = (AIPlayer) players[1];
		else
			return false;

		p.train( numIter );
		return true;
	}
}
