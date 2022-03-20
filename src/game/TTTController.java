package game;

import game.player.AutoPlayable;
import gui.BoardPanel;
import gui.ControlPanel;
import gui.TTTButton;
import gui.TTTMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TTTController implements ActionListener {
	private Game theGame;
	private BoardPanel theBoardPanel;
	private ControlPanel theControlPanel;
	private TTTMainView theView;

	class TrainButtonListener implements ActionListener {

		@Override
		public void actionPerformed( ActionEvent e ) {
			int numIter = Integer.parseInt( theControlPanel
					.getTextFieldNumIter().getText() );
			theGame.trainAIPlayer( numIter );
			JOptionPane.showMessageDialog( theView, "Training finished!",
					"Train Status", JOptionPane.INFORMATION_MESSAGE );
		}

	}

	public TTTController( final Game theGame, TTTMainView theView ) {
		this.theGame = theGame;
		this.theView = theView;
		this.theBoardPanel = theView.getBoardPanel();
		this.theControlPanel = theView.getControlPanel();

		for ( int row = 0; row < 3; row++ )
			for ( int col = 0; col < 3; col++ )
				theBoardPanel.getButtons()[row][col].addActionListener( this );

		theControlPanel.getBtnTrainAI().addActionListener(
				new TrainButtonListener() );

		theView.getControlPanel().getBtnNewGame()
				.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed( ActionEvent evt ) {
						theGame.getBoard().clear();
						theBoardPanel.updateBoard( theGame.getBoard() );
					}

				} );
	}

	@Override
	public void actionPerformed( ActionEvent evt ) {
		TTTButton tb = (TTTButton) evt.getSource();
		// System.out.println( "Button - " + tb.getRow() + "," + tb.getCol() );

		if ( !theGame.getBoard().isPlayable() ) {
			JOptionPane.showMessageDialog( theBoardPanel,
					"Game over! Start a new game!", "Game over!",
					JOptionPane.ERROR_MESSAGE );
			return;
		}

		if ( !theGame.getCurPlayer().move( tb.getRow(), tb.getCol() ) ) {
			JOptionPane.showMessageDialog( theBoardPanel,
					"Can not play there!", "Bad move!",
					JOptionPane.ERROR_MESSAGE );
			return;
		}

		tb.setText( theGame.getCurPlayer().toString() );

		// Playable? Then update the next player
		if ( theGame.getBoard().isPlayable() ) {
			theGame.nextPlayer();

			// FORCE the AutoPlayable player to go
			if ( theGame.getCurPlayer() instanceof AutoPlayable ) {
				( (AutoPlayable) ( theGame.getCurPlayer() ) ).makeMove();
				theGame.nextPlayer();
			}
		}

		// Update the board and stats
		theView.getStatsPanel().getLblTurnXO()
				.setText( theGame.getCurPlayer().toString() );
		theView.getBoardPanel().updateBoard( theGame.getBoard() );

		// Are we finished?
		if ( !theGame.getBoard().isPlayable() ) {
			JLabel lblScore = null;
			if ( theGame.getBoard().getState() == Board.State.WIN ) {
				if ( theGame.getBoard().isWinFor( theGame.getPlayerNum( 0 ) ) ) {
					lblScore = theView.getStatsPanel().getLblNumWins();
					JOptionPane.showMessageDialog( theView, theGame
							.getPlayerNum( 0 ).toString() + " WINS!",
							"Winner!", JOptionPane.INFORMATION_MESSAGE );
				}
				else {
					lblScore = theView.getStatsPanel().getLblNumLosses();
					JOptionPane.showMessageDialog( theView, theGame
							.getPlayerNum( 1 ).toString() + " WINS!",
							"Winner!", JOptionPane.INFORMATION_MESSAGE );
				}
			}
			else if ( theGame.getBoard().isDraw() ) {
				JOptionPane.showMessageDialog( theView, "Nobody wins", "Draw!",
						JOptionPane.INFORMATION_MESSAGE );
				lblScore = theView.getStatsPanel().getLblNumDraws();
			}
			lblScore.setText( Integer.toString( Integer.parseInt( lblScore
					.getText() ) + 1 ) );
		}
		// System.out.println( theGame.getBoard() );
	}
}
