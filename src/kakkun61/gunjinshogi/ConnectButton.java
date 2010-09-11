package kakkun61.gunjinshogi;

import kakkun61.gunjinshogi.util.SystemUtility;


public class ConnectButton extends HomeButton
{

	public ConnectButton( GunjinShogi gs )
	{
		super( SystemUtility.getResource( "img/connect.png" ), 360d, 512d, gs );
	}

	@Override
	public void mouseClicked()
	{
		System.err.println( "click connect." );
	}
}
