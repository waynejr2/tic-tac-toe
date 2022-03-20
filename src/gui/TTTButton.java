package gui;

import java.awt.Font;

import javax.swing.JButton;

public class TTTButton extends JButton {
	private int row;
	private int col;

	public TTTButton( int row, int col ) {
		this.row = row;
		this.col = col;
		this.setFont( new Font( "Arial", Font.BOLD, 50 ) );
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
