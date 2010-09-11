package kakkun61.gunjinshogi;


import java.awt.Graphics2D;

import kakkun61.gunjinshogi.net.Communicator;
import kakkun61.gunjinshogi.scene.Board;
import kakkun61.gunjinshogi.scene.Scene;

import com.golden.gamedev.Game;

public class GunjinShogi extends Game
{
	private GunjinShogiPlayField playfield;
	private Board board;
	private Hand hand;
	private Communicator commu;

	@Override
	public void initResources()
	{
		board = new Board();
		hand = new Hand( board, this );
		playfield = new GunjinShogiPlayField( this );
		setFPS( 60 );
		setScene( Scene.HOME );
	}

	@Override
	public void render( Graphics2D g )
	{
		playfield.render( g );
	}

	@Override
	public void update( long elapsedTime )
	{
		playfield.update( elapsedTime );
	}

	public void setScene( Scene s )
	{
		playfield.setScene( s );
	}

	public Board getBoard()
	{
		return board;
	}

	public Hand getHand()
	{
		return hand;
	}

	public Communicator getCommunicator()
	{
		return commu;
	}
}
