package kakkun61.gunjinshogi;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class GunjinShogiSpriteGroup extends SpriteGroup
{
	private GunjinShogi gs;

	public GunjinShogiSpriteGroup( String name, GunjinShogi gs )
	{
		super( name );
		this.gs = gs;
	}

	public void init(){}

	public void setActable( boolean actable )
	{
		for( Sprite s : getSprites() )
		{
			if( s instanceof Button )
				( (Button)s ).setActable( actable );
		}
	}

	public GunjinShogi getGunjinShogi()
	{
		return gs;
	}
}
