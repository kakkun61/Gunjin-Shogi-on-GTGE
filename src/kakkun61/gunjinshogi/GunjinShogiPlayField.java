package kakkun61.gunjinshogi;


import java.awt.Transparency;

import kakkun61.gunjinshogi.scene.Arrangement;
import kakkun61.gunjinshogi.scene.Battle;
import kakkun61.gunjinshogi.scene.Configure;
import kakkun61.gunjinshogi.scene.Connecting;
import kakkun61.gunjinshogi.scene.Home;
import kakkun61.gunjinshogi.scene.Result;
import kakkun61.gunjinshogi.scene.Scene;
import kakkun61.gunjinshogi.scene.Waiting;
import kakkun61.gunjinshogi.util.SystemUtility;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;

public class GunjinShogiPlayField extends PlayField
{
	private SpriteGroup board;
	private GunjinShogiSpriteGroup home,
	                               arrangement,
	                               waiting,
	                               battle,
	                               result,
	                               configure,
	                               connectiong;

	public GunjinShogiPlayField( GunjinShogi gs )
	{
		super( new ImageBackground( ImageUtil.getImage( SystemUtility.getResource( "img/background.png" ), Transparency.TRANSLUCENT ) ) );

		board = new SpriteGroup( "board" );
		board.add( new Sprite( ImageUtil.getImage( SystemUtility.getResource( "img/board.png" ), Transparency.TRANSLUCENT ) ) );
		home = new Home( gs );
		arrangement = new Arrangement( gs );
		waiting = new Waiting( gs );
		battle = new Battle( gs );
		result = new Result( gs );
		configure = new Configure( gs );
		connectiong = new Connecting( gs );

		addGroup( board );
		addGroup( home );
		addGroup( arrangement );
		addGroup( waiting );
		addGroup( battle );
		addGroup( result );
		addGroup( configure );
		addGroup( connectiong );
	}

	public void setScene( Scene s )
	{
		switch( s )
		{
			case HOME:
				home.init();
				home.setActive( true );
				board.setActive( false );
				arrangement.setActive( false );
				waiting.setActive( false );
				battle.setActive( false );
				result.setActive( false );
				configure.setActive( false );
				connectiong.setActive( false );
				break;
			case ARRANGEMENT:
				arrangement.init();
				home.setActive( false );
				board.setActive( true );
				arrangement.setActive( true );
				waiting.setActive( false );
				battle.setActive( false );
				result.setActive( false );
				configure.setActive( false );
				connectiong.setActive( false );
				break;
			case WAITING:
				waiting.init();
				home.setActive( false );
				board.setActive( true );
				arrangement.setActive( false );
				waiting.setActive( true );
				battle.setActive( false );
				result.setActive( false );
				configure.setActive( false );
				connectiong.setActive( false );
				break;
			case BATTLE:
				battle.init();
				home.setActive( false );
				board.setActive( true );
				arrangement.setActive( false );
				waiting.setActive( false );
				battle.setActive( true );
				result.setActive( false );
				configure.setActive( false );
				connectiong.setActive( false );
				break;
			case RESULT:
				result.init();
				home.setActive( false );
				board.setActive( true );
				arrangement.setActive( false );
				waiting.setActive( false );
				battle.setActive( false );
				result.setActive( true );
				configure.setActive( false );
				connectiong.setActive( false );
				break;

			case CONFIGURE:
				configure.init();
				home.setActive( false );
				board.setActive( false );
				arrangement.setActive( false );
				waiting.setActive( false );
				battle.setActive( false );
				result.setActive( true );
				configure.setActive( true );
				connectiong.setActive( false );
				break;

			case CONNECTING:
				connectiong.init();
				home.setActive( false );
				board.setActive( false );
				arrangement.setActive( false );
				waiting.setActive( false );
				battle.setActive( false );
				result.setActive( false );
				configure.setActive( false );
				connectiong.setActive( true );
				break;
		}
	}
}
