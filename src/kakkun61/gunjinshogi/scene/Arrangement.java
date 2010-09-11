package kakkun61.gunjinshogi.scene;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import kakkun61.gunjinshogi.Button;
import kakkun61.gunjinshogi.FinishButon;
import kakkun61.gunjinshogi.GunjinShogi;
import kakkun61.gunjinshogi.GunjinShogiSpriteGroup;
import kakkun61.gunjinshogi.Hand;
import kakkun61.gunjinshogi.Piece;
import kakkun61.gunjinshogi.Piece.Type;
import kakkun61.gunjinshogi.scene.Board.Area;
import kakkun61.gunjinshogi.scene.Board.Coordinate;


public class Arrangement extends GunjinShogiSpriteGroup
{
	private Button finish;
	private Coordinate selectCoord;
	private Coordinate[] exchangableCoordinates;

	private Board board;
	private Hand hand;

	public Arrangement( GunjinShogi gs )
	{
		super( "arrangement", gs );
		finish = new FinishButon( gs );
		board = getGunjinShogi().getBoard();
		hand = getGunjinShogi().getHand();
		for( Piece p : board.pieces )
			add( p );
		add( finish );
	}

	@Override
	public void init()
	{
		super.init();

		hand.initBoard();
		finish.setActive( false );
	}

	@Override
	public void render( Graphics2D g )
	{
		super.render( g );
		hand.render( g );
	}

	@Override
	public void update( long elapsedTime )
	{
		super.update( elapsedTime );

		if( getGunjinShogi().click() )
		{
			if( selectCoord == null )
			{
				Coordinate coord = hand.getMouseBoardCoordinate();
				if( coord != null && board.getPiece( coord ) != null )
				{
					selectCoord = coord;
					hand.setBlueIndicatorCoordinte( selectCoord );
					exchangableCoordinates = getExchangableCoordinates( selectCoord );
					hand.setGreenIndicatorCoordinates( exchangableCoordinates );
				}
			}
			else
			{
				Coordinate destCoord = hand.getMouseBoardCoordinate();
				if( destCoord != null )
				{
					if( destCoord.area == Board.Area.MY_DEFEAT_PIECES )
						hand.exchange( selectCoord, destCoord );
					else
					for( Coordinate c : exchangableCoordinates )
						if( destCoord.equals( c ) )
						{
							hand.exchange( selectCoord, destCoord );
							break;
						}
				}
				selectCoord = null;
				hand.setBlueIndicatorCoordinte( null );
				hand.setGreenIndicatorCoordinates( null );
			}

			// 完了判定
			int count = board.myDefeatPieces.length * board.myDefeatPieces[0].length;
			for( Piece[] ps : board.myDefeatPieces )
				for( Piece p : ps )
					if( p == null )
						count--;
			if( count == 0 )
			{
				if( !finish.isActive() )
					finish.setActive( true );
			}
			else
				if( finish.isActive() )
					finish.setActive( false );
		}
	}

	private Coordinate[] getExchangableCoordinates( Coordinate selectCoord )
	{
		if( selectCoord.area == Board.Area.MY_DEFEAT_PIECES )
			return getMovableCoordinates( board.getPiece( selectCoord ) );
		else
		{
			List<Coordinate> coords = new ArrayList<Coordinate>( 23 );
			for( Coordinate c0 : getMovableCoordinates( board.getPiece( selectCoord ) ) )
			{
				Piece objPiece = board.getPiece( c0 );
				if( objPiece == null )
					coords.add( c0 );
				else
					for( Coordinate c1 : getMovableCoordinates( objPiece ) )
						if( c1.equals( selectCoord ) )
							coords.add( c0 );
			}
			return coords.toArray( new Coordinate[0] );
		}
	}

	private Coordinate[] getMovableCoordinates( Piece p )
	{
		if( p == null )
			return null;

		if( p.type == Piece.Type.JIRAI )
			return new Coordinate[]{
					new Coordinate( Board.Area.BATTLEFIELD, 1, 3 ),
					new Coordinate( Board.Area.BATTLEFIELD, 3, 3 ),
					new Coordinate( Board.Area.BATTLEFIELD, 4, 3 ),
					new Coordinate( Board.Area.BATTLEFIELD, 6, 3 ),
					new Coordinate( Board.Area.BATTLEFIELD, 0, 4 ),
					new Coordinate( Board.Area.BATTLEFIELD, 1, 4 ),
					new Coordinate( Board.Area.BATTLEFIELD, 2, 4 ),
					new Coordinate( Board.Area.BATTLEFIELD, 3, 4 ),
					new Coordinate( Board.Area.BATTLEFIELD, 4, 4 ),
					new Coordinate( Board.Area.BATTLEFIELD, 5, 4 ),
					new Coordinate( Board.Area.BATTLEFIELD, 6, 4 ),
					new Coordinate( Board.Area.BATTLEFIELD, 7, 4 ),
					new Coordinate( Board.Area.BATTLEFIELD, 0, 5 ),
					new Coordinate( Board.Area.BATTLEFIELD, 1, 5 ),
					new Coordinate( Board.Area.BATTLEFIELD, 2, 5 ),
					new Coordinate( Board.Area.BATTLEFIELD, 3, 5 ),
					new Coordinate( Board.Area.BATTLEFIELD, 5, 5 ),
					new Coordinate( Board.Area.BATTLEFIELD, 6, 5 ),
					new Coordinate( Board.Area.BATTLEFIELD, 7, 5 ),
			};
		if( p.type == Piece.Type.GUNKI )
			return new Coordinate[]{
				new Coordinate( Board.Area.BATTLEFIELD, 1, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 3, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 4, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 6, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 0, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 1, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 2, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 3, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 4, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 5, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 6, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 7, 4 ),
			};
		return new Coordinate[]{
				new Coordinate( Board.Area.BATTLEFIELD, 0, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 1, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 2, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 3, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 4, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 5, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 6, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 7, 3 ),
				new Coordinate( Board.Area.BATTLEFIELD, 0, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 1, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 2, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 3, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 4, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 5, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 6, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 7, 4 ),
				new Coordinate( Board.Area.BATTLEFIELD, 0, 5 ),
				new Coordinate( Board.Area.BATTLEFIELD, 1, 5 ),
				new Coordinate( Board.Area.BATTLEFIELD, 2, 5 ),
				new Coordinate( Board.Area.BATTLEFIELD, 3, 5 ),
				new Coordinate( Board.Area.BATTLEFIELD, 5, 5 ),
				new Coordinate( Board.Area.BATTLEFIELD, 6, 5 ),
				new Coordinate( Board.Area.BATTLEFIELD, 7, 5 ),
		};
	}
}
