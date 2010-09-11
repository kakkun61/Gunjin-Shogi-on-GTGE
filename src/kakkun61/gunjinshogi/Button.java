package kakkun61.gunjinshogi;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;

public class Button extends Sprite
{
	protected Game game;
	protected Rectangle mouseListeningArea;
	private boolean actable = true;

	public Button( Game game )
	{
		this.game = game;
	}

	/**
	 * このボタンの左上隅を (0, 0) とする。
	 * @param mla
	 */
	public void setMouseListeningArea( Rectangle mla )
	{
		if( mla == null )
		{
			this.mouseListeningArea = new Rectangle( getWidth(), getHeight() );
			return;
		}
		this.mouseListeningArea = mla;
	}

	@Override
	public void setImage( BufferedImage image )
	{
		super.setImage( image );
		if( mouseListeningArea == null )
			mouseListeningArea = new Rectangle( getWidth(), getHeight() );
	}

	private boolean prevMouseOver;

	@Override
	public void update( long elapsed )
	{
		super.update( elapsed );

		if( actable )
		{
			if( mouseListeningArea != null?
					game.checkPosMouse( (int)getX()+mouseListeningArea.x, (int)getY()+mouseListeningArea.y,
							(int)getX()+mouseListeningArea.x+mouseListeningArea.width, (int)getY()+mouseListeningArea.y+mouseListeningArea.height):
								game.checkPosMouse( (int)getX(), (int)getY(), (int)getX()+width, (int)getY()+height ) )
			{
				if( game.click() )
					mouseClicked();
				else if( !prevMouseOver )
				{
					mouseEntered();
					prevMouseOver = true;
				}
			}
			else if( prevMouseOver )
			{
				mouseLeft();
				prevMouseOver = false;
			}
		}
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame( Game game )
	{
		this.game = game;
	}

	public boolean isActable()
	{
		return actable;
	}

	public void setActable( boolean actable )
	{
		this.actable = actable;
	}

	public void mouseEntered(){}

	public void mouseLeft(){}

	public void mouseClicked(){}
}
