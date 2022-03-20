package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class StatsPanel extends JPanel {
	private JLabel lblNumWins;
	private JLabel lblTurnXO;
	private JLabel lblNumLosses;
	private JLabel lblNumDraws;

	/**
	 * Create the panel.
	 */
	public StatsPanel() {
		setBorder( new EtchedBorder( EtchedBorder.RAISED, null, null ) );
		setLayout( new GridLayout( 0, 1, 5, 5 ) );

		JLabel lblTurn = new JLabel( "Next:" );
		lblTurn.setHorizontalAlignment( SwingConstants.CENTER );
		add( lblTurn );

		lblTurnXO = new JLabel( "X" );
		lblTurn.setLabelFor( lblTurnXO );
		lblTurnXO.setHorizontalAlignment( SwingConstants.CENTER );
		Font f = lblTurn.getFont();
		lblTurnXO.setFont( new Font( f.getName(), Font.BOLD, f.getSize() + 1 ) );
		add( lblTurnXO );

		JSeparator separator_1 = new JSeparator();
		add( separator_1 );

		JLabel lblWins = new JLabel( "X Wins" );
		lblWins.setHorizontalAlignment( SwingConstants.CENTER );
		add( lblWins );

		lblNumWins = new JLabel();
		lblNumWins.setText( "0" );
		lblWins.setLabelFor( lblNumWins );
		lblNumWins.setBorder( new LineBorder( Color.BLACK ) );
		lblNumWins.setHorizontalAlignment( SwingConstants.CENTER );
		add( lblNumWins );

		add( new JSeparator() );

		JLabel lblLosses = new JLabel( "O Wins" );
		lblLosses.setHorizontalAlignment( SwingConstants.CENTER );
		add( lblLosses );

		lblNumLosses = new JLabel( "0" );
		lblLosses.setLabelFor( lblNumLosses );
		lblNumLosses.setBorder( new LineBorder( Color.BLACK ) );
		lblNumLosses.setHorizontalAlignment( SwingConstants.CENTER );
		add( lblNumLosses );

		add( new JSeparator() );

		JLabel lblDraws = new JLabel( "Draws" );
		lblDraws.setHorizontalAlignment( SwingConstants.CENTER );
		add( lblDraws );

		lblNumDraws = new JLabel( "0" );
		lblDraws.setLabelFor( lblNumDraws );
		lblNumDraws.setBorder( new LineBorder( Color.BLACK ) );
		lblNumDraws.setHorizontalAlignment( SwingConstants.CENTER );
		add( lblNumDraws );
	}

	public JLabel getLblTurnXO() {
		return lblTurnXO;
	}

	public JLabel getLblNumWins() {
		return lblNumWins;
	}

	public JLabel getLblNumLosses() {
		return lblNumLosses;
	}

	public JLabel getLblNumDraws() {
		return lblNumDraws;
	}
}
