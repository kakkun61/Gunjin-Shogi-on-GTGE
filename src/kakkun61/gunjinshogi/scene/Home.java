package kakkun61.gunjinshogi.scene;


import java.awt.Transparency;

import kakkun61.gunjinshogi.ConfigureButton;
import kakkun61.gunjinshogi.ConnectButton;
import kakkun61.gunjinshogi.GunjinShogi;
import kakkun61.gunjinshogi.GunjinShogiSpriteGroup;
import kakkun61.gunjinshogi.StartButton;
import kakkun61.gunjinshogi.util.SystemUtility;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;


public class Home extends GunjinShogiSpriteGroup
{
	public Home( GunjinShogi gs )
	{
		super( "home", gs );
		add( new Sprite( ImageUtil.getImage( SystemUtility.getResource( "img/title.png" ), Transparency.TRANSLUCENT ), 149, 97) );
		add( new StartButton( gs ) );
		add( new ConfigureButton( gs ) );
		add( new ConnectButton( gs ) );
	}

	@Override
	public void init()
	{
		setActable( true );
	}
}
