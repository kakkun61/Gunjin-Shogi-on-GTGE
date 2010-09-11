package kakkun61.gunjinshogi.util;

import java.net.URL;

import kakkun61.debug.Debug;


public class SystemUtility
{
	private SystemUtility(){}

	public static URL getResource( String path )
	{
		URL rsc = SystemUtility.class.getClassLoader().getResource( path );
		if( rsc == null )
			exitWithErrer( 1, path + " が見つかりません。" );
		return rsc;
	}

	public static void exitWithErrer( int status, String message )
	{
		System.err.println( message );
		if( Debug.isActive() )
			new Throwable().printStackTrace();
		System.exit( status );
	}
}
