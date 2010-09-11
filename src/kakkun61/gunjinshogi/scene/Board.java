package kakkun61.gunjinshogi.scene;

import kakkun61.gunjinshogi.Piece;
import kakkun61.gunjinshogi.Piece.Type;

public class Board
{
	/** Piece[23] */
	public Piece[] pieces;
	/** Piece[8][6] */
	public Piece[][] battlefield;
	/** Piece[12][2] */
	public Piece[][] myDefeatPieces;
	public int opponentDefeatPiecesCount;

	public Board()
	{
		pieces = new Piece[23];
		pieces[ 0] = new Piece( Piece.Type.TAISHO );
		pieces[ 1] = new Piece( Piece.Type.CHUSHO );
		pieces[ 2] = new Piece( Piece.Type.SHOSHO );
		pieces[ 3] = new Piece( Piece.Type.TAISA );
		pieces[ 4] = new Piece( Piece.Type.CHUSA );
		pieces[ 5] = new Piece( Piece.Type.SHOSA );
		pieces[ 6] = new Piece( Piece.Type.TAII );
		pieces[ 7] = new Piece( Piece.Type.TAII );
		pieces[ 8] = new Piece( Piece.Type.CHUI );
		pieces[ 9] = new Piece( Piece.Type.CHUI );
		pieces[10] = new Piece( Piece.Type.SHOI );
		pieces[11] = new Piece( Piece.Type.SHOI );
		pieces[12] = new Piece( Piece.Type.GUNKI );
		pieces[13] = new Piece( Piece.Type.KOHE );
		pieces[14] = new Piece( Piece.Type.KOHE );
		pieces[15] = new Piece( Piece.Type.JIRAI );
		pieces[16] = new Piece( Piece.Type.JIRAI );
		pieces[17] = new Piece( Piece.Type.KIHE );
		pieces[18] = new Piece( Piece.Type.KANCHO );
		pieces[19] = new Piece( Piece.Type.HIKOKI );
		pieces[20] = new Piece( Piece.Type.HIKOKI );
		pieces[21] = new Piece( Piece.Type.SENSHA );
		pieces[22] = new Piece( Piece.Type.SENSHA );
		battlefield = new Piece[8][6];
		myDefeatPieces = new Piece[12][2];
		opponentDefeatPiecesCount = 0;
	}

	public Piece getPiece( Coordinate coord )
	{
		if( coord.area == Area.MY_DEFEAT_PIECES )
			return myDefeatPieces[coord.x][coord.y];
		if( coord.area == Area.BATTLEFIELD )
			return battlefield[coord.x][coord.y];
		assert false: "Can't reach here.";
		return null;
	}

	public static enum Area
	{
		MY_DEFEAT_PIECES,
		BATTLEFIELD;
	}

	public static class Coordinate
	{
		public final Area area;
		public final int x, y;

		public Coordinate( Area area, int x, int y )
		{
			this.area = area;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString()
		{
			return super.toString() + " [" + area + ", " + x + ", " + y + "]";
		}

		@Override
		public boolean equals( Object obj )
		{
			if( !( obj instanceof Coordinate ) )
				return false;
			Coordinate c = (Coordinate)obj;
			if( c.area == area
			 && c.x == x
			 && c.y == y )
				return true;
			return false;
		}
	}
}
