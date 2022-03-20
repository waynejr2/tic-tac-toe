package game;

public class Square {
	private Mark mark;
	private int row;
	private int col;
	private boolean isWin;

	public Square( int row, int col ) {
		this.row = row;
		this.col = col;
		clearMark();
	}

	public Square( Square s ) {
		this.row = s.row;
		this.col = s.col;
		this.mark = s.mark;
		this.isWin = s.isWin;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public Mark getMark() {
		return this.mark;
	}

	public void setMark( Mark mark ) {
		this.mark = mark;
	}

	public void clearMark() {
		setMark( Mark.NONE );
		this.isWin = false;
	}

	public boolean isMark( Mark mark ) {
		return this.mark == mark;
	}

	public void setWin( boolean b ) {
		this.isWin = b;
	}

	public String toString() {
		return mark.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( obj instanceof Square ) {
			Square s = (Square) obj;
			return s.row == this.row && s.col == this.col
					&& s.mark == this.mark && s.isWin == this.isWin;
		}
		return false;
	}

}
