package kakkun61.gunjinshogi;


import java.awt.image.BufferedImage;

import kakkun61.gunjinshogi.util.SystemUtility;

public class ConfigureButton extends HomeButton
{
	BufferedImage[] img;

	public ConfigureButton( GunjinShogi gs )
	{
		super( SystemUtility.getResource( "img/configure.png" ), 360d, 610d, gs );

	}

	@Override
	public void mouseClicked()
	{
		System.err.println( "click configure." );
	}
}
