package kakkun61.gunjinshogi.scene;


import java.awt.Transparency;

import kakkun61.gunjinshogi.GunjinShogi;
import kakkun61.gunjinshogi.GunjinShogiSpriteGroup;
import kakkun61.gunjinshogi.Piece;
import kakkun61.gunjinshogi.util.SystemUtility;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

public class Waiting extends GunjinShogiSpriteGroup
{
	public Waiting( GunjinShogi gs )
	{
		super( "preparation", gs );
		for( Piece p : getGunjinShogi().getBoard().pieces )
			add( p );
		add( new Sprite( ImageUtil.getImage( SystemUtility.getResource( "img/wait.png" ), Transparency.TRANSLUCENT ) ) );
	}
}
