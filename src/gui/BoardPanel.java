package gui;

import game.Board;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class BoardPanel extends JPanel {

	private TTTButton[][] buttons;

	/**
	 * Create the panel.
	 */
	public BoardPanel() {
		this.setLayout( new GridLayout( 3, 3, 5, 5 ) );
		this.setBorder( new EtchedBorder( EtchedBorder.RAISED, null, null ) );

		// Add the JButtons to the display
		buttons = new TTTButton[3][3];
		for ( int row = 0; row < 3; row++ ) {
			for ( int col = 0; col < 3; col++ ) {
				buttons[row][col] = new TTTButton( row, col );
				buttons[row][col].setPreferredSize( new Dimension( 75, 75 ) );
				this.add( buttons[row][col] );
			}
		}
	}

	public TTTButton[][] getButtons() {
		return buttons;
	}

	public void updateBoard( Board theBoard ) {
		for ( int row = 0; row < 3; row++ )
			for ( int col = 0; col < 3; col++ ) {
				buttons[row][col].setText( theBoard.getMark( row, col )
						.toString() );
			}
	}
}
