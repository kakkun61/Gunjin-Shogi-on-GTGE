package kakkun61.gunjinshogi;

import java.awt.Point;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

public class Piece extends Sprite
{
	private static BufferedImage[] normalImgs,
	                               smallImgs;

	public Type type;

	public Piece( Type t )
	{
		if( normalImgs == null )
		{
			normalImgs = ImageUtil.getImages( getClass().getClassLoader().getResource( "img/pieces.png" ), 4, 4, Transparency.TRANSLUCENT );
			smallImgs = new BufferedImage[normalImgs.length];
			int type = normalImgs[0].getType();
			for( int i=0; i<smallImgs.length; i++ )
			{
				smallImgs[i] = new BufferedImage( 40, 40, type );
				smallImgs[i].getGraphics().drawImage( normalImgs[i], 0, 0, 40, 40, null );
			}
		}
		type = t;
		setImage( normalImgs[t.value] );
	}

	public void setSmall( boolean s )
	{
		if( s )
		{
			if( getImage() != smallImgs[type.value] )
				setImage( smallImgs[type.value] );
		}
		else
			if( getImage() != normalImgs[type.value] )
				setImage( normalImgs[type.value] );
	}

	@Override
	public boolean equals( Object obj )
	{
		if( !(obj instanceof Piece) )
			return false;
		return type == ((Piece)obj).type;
	}

	public void setLocation( Point point )
	{
		setLocation( point.x, point.y );
	}

	@Override
	public String toString()
	{
		return super.toString() + "[type=" + type + ", x=" + getX() + ", y=" + getY() + "]";
	}

	public static enum Type
	{
		TAISHO(  0 ),
		CHUSHO(  1 ),
		SHOSHO(  2 ),
		TAISA (  3 ),
		CHUSA (  4 ),
		SHOSA (  5 ),
		TAII  (  6 ),
		CHUI  (  7 ),
		SHOI  (  8 ),
		GUNKI (  9 ),
		KOHE  ( 10 ),
		JIRAI ( 11 ),
		KIHE  ( 12 ),
		KANCHO( 13 ),
		HIKOKI( 14 ),
		SENSHA( 15 );

		public final int value;

		private Type( int value )
		{
			this.value = value;
		}
	}
}
