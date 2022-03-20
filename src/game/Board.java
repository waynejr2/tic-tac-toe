package game;

import game.player.Player;

public class Board {

	enum State {
		NEW, PLAYING, WIN, DRAW;
	}

	private Square[][] board;
	private State state;
	private Mark winningPlayer;

	public Board() {
		board = new Square[3][3];
		for ( int row = 0; row < 3; row++ ) {
			for ( int col = 0; col < 3; col++ ) {
				board[row][col] = new Square( row, col );
			}
		}
		winningPlayer = Mark.NONE;
		clear();
	}

	public Board( Board boardToCopy ) {
		this.board = new Square[3][3];
		for ( int row = 0; row < 3; row++ ) {
			for ( int col = 0; col < 3; col++ ) {
				this.board[row][col] = new Square( boardToCopy.board[row][col] );
			}
		}
		this.winningPlayer = boardToCopy.winningPlayer;
		this.state = boardToCopy.state;
	}

	public void clear( int row, int col ) {
		board[row][col].clearMark();
		updateBoardState();
	}

	public void clear() {
		for ( int row = 0; row < 3; row++ ) {
			for ( int col = 0; col < 3; col++ ) {
				board[row][col].clearMark();
			}
		}
		updateBoardState();
	}

	public boolean setMark( int row, int col, Mark m ) {
		if ( isPlayable() && isEmpty( row, col ) ) {
			board[row][col].setMark( m );
			updateBoardState();
			return true;
		}
		return false;
	}

	public boolean setMark( Square s, Mark m ) {
		return setMark( s.getRow(), s.getCol(), m );
	}

	public Mark getMark( int row, int col ) {
		return board[row][col].getMark();
	}

	public Mark getMark( Square s ) {
		return getMark( s.getRow(), s.getCol() );
	}

	public State getState() {
		return this.state;
	}

	/**
	 * Is this board playable?
	 * 
	 * @return <code>true</code> if the board can still be played
	 */
	public boolean isPlayable() {
		return ( this.state == State.NEW || this.state == State.PLAYING );
	}

	public boolean isEmpty( int row, int col ) {
		return getMark( row, col ) == Mark.NONE;
	}

	public boolean isWinFor( Mark player ) {
		if ( this.state == State.WIN && winningPlayer == player )
			return true;
		return false;
	}

	public boolean isWinFor( Player player ) {
		return isWinFor( player.getMark() );
	}

	public boolean isDraw() {
		return this.state == State.DRAW;
	}

	private Mark isRowSame( int row ) {
		if ( board[row][0].getMark() == board[row][1].getMark()
				&& board[row][1].getMark() == board[row][2].getMark() )
			return board[row][0].getMark();
		return Mark.NONE;
	}

	private Mark isColSame( int col ) {
		if ( board[0][col].getMark() == board[1][col].getMark()
				&& board[1][col].getMark() == board[2][col].getMark() )
			return board[0][col].getMark();
		return Mark.NONE;
	}

	private void updateBoardState() {
		// Clear all of the win markings
		for ( int row = 0; row < 3; row++ )
			for ( int col = 0; col < 3; col++ )
				board[row][col].setWin( false );
		this.state = State.NEW;
		this.winningPlayer = Mark.NONE;

		// Check for all possible win combinations
		for ( int i = 0; i < 3; i++ ) {
			Mark m;

			m = isRowSame( i );
			if ( m != Mark.NONE ) {
				this.state = State.WIN;
				this.winningPlayer = m;
				board[i][0].setWin( true );
				board[i][1].setWin( true );
				board[i][2].setWin( true );
			}

			// Check rows
			m = isColSame( i );
			if ( m != Mark.NONE ) {
				this.state = State.WIN;
				this.winningPlayer = m;
				board[0][i].setWin( true );
				board[1][i].setWin( true );
				board[2][i].setWin( true );
			}
		}

		// Check diagonals
		if ( board[0][0].getMark() == board[1][1].getMark()
				&& board[1][1].getMark() == board[2][2].getMark()
				&& board[0][0].getMark() != Mark.NONE ) {
			this.state = State.WIN;
			this.winningPlayer = board[0][0].getMark();
			board[0][0].setWin( true );
			board[1][1].setWin( true );
			board[2][2].setWin( true );
		}

		if ( board[2][0].getMark() == board[1][1].getMark()
				&& board[1][1].getMark() == board[0][2].getMark()
				&& board[2][0].getMark() != Mark.NONE ) {
			this.state = State.WIN;
			this.winningPlayer = board[2][0].getMark();
			board[2][0].setWin( true );
			board[1][1].setWin( true );
			board[0][2].setWin( true );
		}

		if ( this.state != State.WIN ) {
			// Check for a possible draw
			boolean hasEmptySquare = false;
			boolean hasAllEmptySquares = true;
			for ( int i = 0; i < 3; i++ ) {
				for ( int j = 0; j < 3; j++ ) {
					if ( board[i][j].isMark( Mark.NONE ) )
						hasEmptySquare = true;
					else
						hasAllEmptySquares = false;
				}
			}
			if ( !hasEmptySquare )
				this.state = State.DRAW;
			else if ( hasAllEmptySquares )
				this.state = State.NEW;
			else
				this.state = State.PLAYING;
		}
	}

	public String toString() {
		String s = "";
		for ( int row = 0; row < 3; row++ ) {
			s += board[row][0].toString();
			for ( int col = 1; col < 3; col++ ) {
				s += " | " + board[row][col].toString();
			}
			if ( row < 2 )
				s += "\n--+---+--\n";
		}
		s += "\nState: " + state.name();
		s += "\nWinning Player: " + winningPlayer.name();
		return s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( obj instanceof Board ) {
			Board b = (Board) obj;
			for ( int row = 0; row < 3; row++ ) {
				for ( int col = 0; col < 3; col++ ) {
					if ( !b.board[row][col].equals( this.board[row][col] ) )
						return false;
				}
			}
			return true;
		}
		return false;
	}

}
