package kakkun61.gunjinshogi;


import java.awt.Transparency;
import java.awt.image.BufferedImage;

import kakkun61.gunjinshogi.scene.Scene;
import kakkun61.gunjinshogi.util.SystemUtility;

import com.golden.gamedev.util.ImageUtil;

public class FinishButon extends Button
{
	protected BufferedImage[] img;

	public FinishButon( GunjinShogi gs )
	{
		super( gs );
		img = ImageUtil.getImages( SystemUtility.getResource( "img/finish.png" ), 1, 2, Transparency.TRANSLUCENT );
		setImage( img[0] );
		setLocation( 679, 354 );
		setImmutable( true );
	}

	@Override
	public GunjinShogi getGame()
	{
		return (GunjinShogi)game;
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

	@Override
	public void mouseClicked()
	{
		getGame().setScene( Scene.WAITING );
	}
}
