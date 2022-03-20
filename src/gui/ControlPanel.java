package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ControlPanel extends JPanel {
	private JButton btnNewGame;
	private JTextField textFieldNumIter;
	private JButton btnTrainAi;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public ControlPanel() {
		setLayout( new GridLayout( 0, 1, 0, 0 ) );

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap( 1 );
		flowLayout.setVgap( 1 );
		add( panel );

		btnNewGame = new JButton( "New Game" );
		panel.add( btnNewGame );

		btnTrainAi = new JButton( "Train AI" );
		panel.add( btnTrainAi );

		textFieldNumIter = new JTextField( "10000" );
		panel.add( textFieldNumIter );
		textFieldNumIter.setHorizontalAlignment( SwingConstants.CENTER );
		textFieldNumIter.setPreferredSize( new Dimension( 150, 20 ) );
	}

	public JButton getBtnNewGame() {
		return btnNewGame;
	}

	public JButton getBtnTrainAI() {
		return btnTrainAi;
	}

	public JTextField getTextFieldNumIter() {
		return textFieldNumIter;
	}
}
