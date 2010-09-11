package kakkun61.gunjinshogi;

import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.net.URL;

import com.golden.gamedev.util.ImageUtil;

public class HomeButton extends Button
{
	protected BufferedImage[] img;
	protected GunjinShogi gs;

	public HomeButton( URL url, double x, double y, GunjinShogi gs )
	{
		super( gs );
		this.gs = gs;
		img = ImageUtil.getImages( url, 1, 2, Transparency.TRANSLUCENT );
		setImage( img[0] );
		setLocation( x, y );
		setMouseListeningArea( new Rectangle( 80, 0, 145, 70 ) );
	}

	@Override
	public GunjinShogi getGame()
	{
		return gs;
	}

	@Override
	public void mouseEntered()
	{
		setImage( img[1] );
	}

	@Override
	public void mouseLeft()
	{
		setImage( img[0] );
	}
}
