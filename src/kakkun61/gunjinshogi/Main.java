package kakkun61.gunjinshogi;


import java.awt.Dimension;

import kakkun61.debug.Debug;
import kakkun61.gunjinshogi.util.CommandLineOption;
import kakkun61.gunjinshogi.util.CommandLineOptionParser;

import com.golden.gamedev.GameLoader;


public class Main
{
	public static void main( String[] args )
	{
		parseOption( args );
		GameLoader gl = new GameLoader();
		gl.setup( new GunjinShogi(), new Dimension( 1024, 768 ), false );
		gl.start();
	}

	private static void parseOption( String[] args )
	{
		CommandLineOptionParser parser = new CommandLineOptionParser();
		CommandLineOption debagOpt = new CommandLineOption( new String[]{"-d", "--debug"}, true );
		parser.addOption( debagOpt );
		parser.parse( args );
		Debug.setActive( debagOpt.isExisting() );

		/*for( int i=0; i<args.length; )
		{
			if( "-d".equals( args[i] ) )
			{
				Debag.setDebag( true );
				i++;
			}
		}*/
	}
}
