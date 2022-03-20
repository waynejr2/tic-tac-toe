package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TTTMainView extends JFrame {

	private JPanel contentPane;
	private PlayerPanel playerPanel;
	private BoardPanel boardPanel;
	private StatsPanel statsPanel;
	private ControlPanel controlPanel;

	/**
	 * Create the frame.
	 */
	public TTTMainView() {
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setLayout( new BorderLayout( 0, 0 ) );
		setContentPane( contentPane );

		playerPanel = new PlayerPanel();
		contentPane.add( playerPanel, BorderLayout.NORTH );

		boardPanel = new BoardPanel();
		contentPane.add( boardPanel, BorderLayout.CENTER );

		statsPanel = new StatsPanel();
		contentPane.add( statsPanel, BorderLayout.WEST );

		controlPanel = new ControlPanel();
		contentPane.add( controlPanel, BorderLayout.SOUTH );

		// Set up the title of the frame
		this.setTitle( "Tic Tac Toe" );

		// Set the location to be in the center of the screen
		this.setLocationRelativeTo( null );

		this.pack();
	}

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public StatsPanel getStatsPanel() {
		return statsPanel;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

}
