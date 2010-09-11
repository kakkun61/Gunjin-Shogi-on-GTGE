package kakkun61.gunjinshogi;

import kakkun61.gunjinshogi.scene.Scene;
import kakkun61.gunjinshogi.util.SystemUtility;


public class StartButton extends HomeButton
{
	public StartButton( GunjinShogi gs )
	{
		super( SystemUtility.getResource( "img/start.png" ), 360d, 411d, gs );
	}

	@Override
	public void mouseClicked()
	{
		getGame().setScene( Scene.ARRANGEMENT );
	}
}
