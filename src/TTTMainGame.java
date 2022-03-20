import game.Game;
import game.TTTController;
import game.player.Player.PlayerType;
import gui.TTTMainView;

import java.awt.EventQueue;

public class TTTMainGame {

	/**
	 * Launch the application.
	 */
	public static void main( String[] args ) {
		Game theGame = new Game( PlayerType.HUMAN, PlayerType.AI );
		final TTTMainView frame = new TTTMainView();
		TTTController controller = new TTTController( theGame, frame );

		EventQueue.invokeLater( new Runnable() {
			public void run() {
				try {
					frame.setVisible( true );
				}
				catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		} );
	}
}
