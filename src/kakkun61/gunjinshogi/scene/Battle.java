package kakkun61.gunjinshogi.scene;


import java.awt.Graphics2D;

import kakkun61.gunjinshogi.GunjinShogi;
import kakkun61.gunjinshogi.GunjinShogiSpriteGroup;
import kakkun61.gunjinshogi.Hand;
import kakkun61.gunjinshogi.Piece;


public class Battle extends GunjinShogiSpriteGroup
{
	private Board board;
	private Hand hand;

	public Battle( GunjinShogi gs )
	{
		super( "battle", gs );
		board = getGunjinShogi().getBoard();
		hand = getGunjinShogi().getHand();
		for( Piece p : board.pieces )
			add( p );
	}

	@Override
	public void render( Graphics2D g )
	{
		super.render( g );
		hand.render( g );
	}
}
