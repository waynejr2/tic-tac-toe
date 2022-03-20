package gui;

import game.Game.GameMode;
import game.player.Player.CompPlayerType;

import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8319489027333955979L;

	/**
	 * Create the panel.
	 */
	public PlayerPanel() {
		setLayout( new GridLayout( 2, 4 ) );

		JLabel lblGameMode = new JLabel( "Game Mode: " );
		lblGameMode.setHorizontalAlignment( SwingConstants.CENTER );
		add( lblGameMode );

		JComboBox comboBoxGameMode = new JComboBox();
		comboBoxGameMode
				.setModel( new DefaultComboBoxModel( GameMode.values() ) );
		add( comboBoxGameMode );

		JLabel lblCompPlayer = new JLabel( "Computer Player:" );
		lblCompPlayer.setHorizontalAlignment( SwingConstants.CENTER );
		add( lblCompPlayer );

		JComboBox comboBoxCompPlayer = new JComboBox();
		comboBoxCompPlayer.setModel( new DefaultComboBoxModel( CompPlayerType
				.values() ) );
		add( comboBoxCompPlayer );

	}

}
