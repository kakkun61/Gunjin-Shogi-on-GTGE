package kakkun61.gunjinshogi;


import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import kakkun61.debug.Debug;
import kakkun61.gunjinshogi.scene.Board;
import kakkun61.gunjinshogi.util.SystemUtility;

import com.golden.gamedev.util.ImageUtil;


public class Hand
{
	private Board board;
	private GunjinShogi gs;
	private BufferedImage blue,
	                      normalBlue,
	                      smallBlue,
	                      green;
	private Point bluePoint;
	private Point[] greenPoints;

	public Hand( Board board, GunjinShogi gs )
	{
		this.board = board;
		this.gs = gs;
		normalBlue = blue = ImageUtil.getImage( SystemUtility.getResource( "img/blue.png" ), Transparency.TRANSLUCENT );
		smallBlue = new BufferedImage( 40, 40, blue.getType() );
		smallBlue.getGraphics().drawImage( blue, 0, 0, 40, 40, null );
		green = ImageUtil.getImage( SystemUtility.getResource( "img/green.png" ), Transparency.TRANSLUCENT );
	}

	public void initBoard()
	{
		if( !Debug.isActive() )
		for( int i=0; i<board.pieces.length; i++ )
			move( board.pieces[i], new Board.Coordinate( Board.Area.MY_DEFEAT_PIECES, i%12, i/12 ) );
		else
			for( int i=0; i<board.pieces.length; i++ )
			{
				int a = i;
				if( (5-3)*8+4 <= i )
					a++;
				move( board.pieces[i], new Board.Coordinate( Board.Area.BATTLEFIELD, a%8, 3+a/8 ) );
			}
	}

	public Board.Coordinate getMouseBoardCoordinate()
	{
		return convertToBoardCoordinate( new Point( gs.getMouseX(), gs.getMouseY() ) );
	}

	public static Board.Coordinate convertToBoardCoordinate( Point p )
	{
		Rectangle rect = new Rectangle();
		int x, y;

		// MyDefeatPieces
		for( x=0; x<12; x++ )
			for( y=0; y<2; y++ )
			{
				rect.setBounds( 493+41*x, 653+41*y, 39, 39 );
				if( rect.contains( p ) )
					return new Board.Coordinate( Board.Area.MY_DEFEAT_PIECES, x, y );
			}

		// Battlefield
		for( x=0; x<8; x++ )
		{
			// 上部
			for( y=0; y<3; y++ )
			{
				rect.setBounds( 488+63*x, 133+63*y, 61, 61 );
				if( rect.contains( p ) )
					if( x == 4 && y == 0 )
						return new Board.Coordinate( Board.Area.BATTLEFIELD, 3, 0 );
					else
						return new Board.Coordinate( Board.Area.BATTLEFIELD, x, y );
			}
			// 下部
			for( y=0; y<3; y++ )
			{
				rect.setBounds( 488+63*x, 448+63*y, 61, 61 );
				if( rect.contains( p ) )
					if( x == 4 && y+3 == 5 )
						return new Board.Coordinate( Board.Area.BATTLEFIELD, 3, 5 );
					else
						return new Board.Coordinate( Board.Area.BATTLEFIELD, x, y+3 );
			}
		}
		// 上部本陣中央
		rect.setBounds( 488+63*3+61, 133, 2, 61 );
		if( rect.contains( p ) )
			return new Board.Coordinate( Board.Area.BATTLEFIELD, 3, 0 );
		// 下部本陣中央
		rect.setBounds( 488+63*3+61, 448+63*5, 2, 61 );
		if( rect.contains( p ) )
			return new Board.Coordinate( Board.Area.BATTLEFIELD, 3, 5 );
		// 浮島
		rect.setBounds( 551, 354, 61, 61 );
		if( rect.contains( p ) )
			return new Board.Coordinate( Board.Area.BATTLEFIELD, 4, 0 );
		rect.setBounds( 866, 354, 61, 61 );
		if( rect.contains( p ) )
			return new Board.Coordinate( Board.Area.BATTLEFIELD, 4, 5 );

		return null;
	}

	public static Point convertToScreenLocation( Board.Coordinate coord )
	{
		int x = coord.x,
		    y = coord.y;

		if( coord.area == Board.Area.MY_DEFEAT_PIECES )
			return new Point( 493+41*x, 653+41*y );

		else
		{
			// 左浮島
			if( x == 4 && y == 0 )
				return new Point( 551, 354 );
			// 右浮島
			if( x == 4 && y == 5 )
				return new Point( 866, 354 );
			// 上本陣
			if( x == 3 && y == 0 )
				return new Point( 488+63*3+31, 133 );
			// 下本陣
			if( x == 3 && y == 5)
				return new Point( 488+63*3+31, 448+63*2 );
			// 上部
			if( y <= 2 )
				return new Point( 488+63*x, 133+63*y );
			// 下部
			return new Point( 488+63*x, 448+63*(y-3) );
		}
	}

	public void exchange( Board.Coordinate c0, Board.Coordinate c1 )
	{
		Piece tmp = board.getPiece( c1 );
		move( board.getPiece( c0 ), c1 );
		move( tmp, c0 );
	}

	public void move( Piece piece, Board.Coordinate coord )
	{
		if( piece != null )
		{
			if( coord.area ==  Board.Area.MY_DEFEAT_PIECES )
				piece.setSmall( true );
			else if( coord.area == Board.Area.BATTLEFIELD )
				piece.setSmall( false );
			piece.setLocation( convertToScreenLocation( coord ) );
		}

		if( coord.area ==  Board.Area.MY_DEFEAT_PIECES )
			board.myDefeatPieces[coord.x][coord.y] = piece;
		else if( coord.area == Board.Area.BATTLEFIELD )
			board.battlefield[coord.x][coord.y] = piece;
	}

	public void setBlueIndicatorCoordinte( Board.Coordinate coord )
	{
		if( coord == null )
		{
			bluePoint = null;
			return;
		}

		if( coord.area == Board.Area.MY_DEFEAT_PIECES )
			blue = smallBlue;
		else
			blue = normalBlue;
		bluePoint = convertToScreenLocation( coord );
	}

	public void setGreenIndicatorCoordinates( Board.Coordinate[] coords )
	{
		if( coords == null )
		{
			greenPoints = null;
			return;
		}

		greenPoints = new Point[coords.length];
		for( int i=0; i<coords.length; i++ )
		{
			greenPoints[i] = convertToScreenLocation( coords[i] );
		}
	}

	public Point[] getRelativeMovablePoint( Piece piece )
	{
		// TODO 駒を飛び越えないように
		if( piece.type == Piece.Type.TAISHO
		 || piece.type == Piece.Type.CHUSHO
		 || piece.type == Piece.Type.SHOSHO
		 || piece.type == Piece.Type.TAISA
		 || piece.type == Piece.Type.CHUSA
		 || piece.type == Piece.Type.SHOSA
		 || piece.type == Piece.Type.TAII
		 || piece.type == Piece.Type.CHUI
		 || piece.type == Piece.Type.SHOI
		 || piece.type == Piece.Type.KANCHO )
			return new Point[]{
					new Point(  0, -1 ),
					new Point(  0,  1 ),
					new Point( -1,  0 ),
					new Point(  1,  0 ),
			};
		if( piece.type == Piece.Type.HIKOKI )
			return new Point[]{
				new Point(  0, -7 ),
				new Point(  0, -6 ),
				new Point(  0, -5 ),
				new Point(  0, -4 ),
				new Point(  0, -3 ),
				new Point(  0, -2 ),
				new Point(  0, -1 ),
				new Point(  0,  1 ),
				new Point(  0,  2 ),
				new Point(  0,  3 ),
				new Point(  0,  4 ),
				new Point(  0,  5 ),
				new Point(  0,  6 ),
				new Point(  0,  7 ),
				new Point( -1,  0 ),
				new Point(  1,  0 )
			};
		if( piece.type == Piece.Type.SENSHA
		 || piece.type == Piece.Type.KIHE )
			return new Point[]{
				new Point(  0, -2 ),
				new Point(  0, -1 ),
				new Point(  0,  1 ),
				new Point(  0,  2 ),
				new Point( -2,  0 ),
				new Point( -1,  0 ),
				new Point(  1,  0 ),
				new Point(  2,  0 ),
			};
		if( piece.type == Piece.Type.KOHE )
			return new Point[]{
				new Point(  0, -7 ),
				new Point(  0, -6 ),
				new Point(  0, -5 ),
				new Point(  0, -4 ),
				new Point(  0, -3 ),
				new Point(  0, -2 ),
				new Point(  0, -1 ),
				new Point(  0,  1 ),
				new Point(  0,  2 ),
				new Point(  0,  3 ),
				new Point(  0,  4 ),
				new Point(  0,  5 ),
				new Point(  0,  6 ),
				new Point(  0,  7 ),
				new Point( -7,  0 ),
				new Point( -6,  0 ),
				new Point( -5,  0 ),
				new Point( -4,  0 ),
				new Point( -3,  0 ),
				new Point( -2,  0 ),
				new Point( -1,  0 ),
				new Point(  1,  0 ),
				new Point(  2,  0 ),
				new Point(  3,  0 ),
				new Point(  4,  0 ),
				new Point(  5,  0 ),
				new Point(  6,  0 ),
				new Point(  7,  0 ),
			};
		assert piece.type == Piece.Type.GUNKI || piece.type == Piece.Type.JIRAI;
		return new Point[0];
	}

	public void render( Graphics2D g )
	{
		if( bluePoint != null )
			g.drawImage( blue, bluePoint.x, bluePoint.y, null );

		if( greenPoints != null )
			for( Point p : greenPoints )
				if( !p.equals( bluePoint ) )
					g.drawImage( green, p.x, p.y, null );
	}
}
