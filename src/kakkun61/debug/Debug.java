package kakkun61.debug;

public class Debug
{
	private static boolean debug;

	private Debug(){}

	public static boolean isActive()
	{
		return debug;
	}

	public static void setActive( boolean debug )
	{
		Debug.debug = debug;
	}

	public static void println( String s )
	{
		if( debug )
			System.err.println( s );
	}
}
