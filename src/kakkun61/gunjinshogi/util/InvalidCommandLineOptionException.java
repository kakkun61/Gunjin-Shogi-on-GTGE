package kakkun61.gunjinshogi.util;

public class InvalidCommandLineOptionException extends IllegalArgumentException
{
	public InvalidCommandLineOptionException()
	{
		super();
	}

	public InvalidCommandLineOptionException( String message, Throwable cause )
	{
		super( message, cause );
	}

	public InvalidCommandLineOptionException( String s )
	{
		super( s );
	}

	public InvalidCommandLineOptionException( Throwable cause )
	{
		super( cause );
	}
}
