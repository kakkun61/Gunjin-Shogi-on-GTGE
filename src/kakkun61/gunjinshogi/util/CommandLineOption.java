package kakkun61.gunjinshogi.util;


public class CommandLineOption
{
	private String[] names;
	private boolean nullary;
	private boolean existing;
	private String parameter;

	public CommandLineOption( String[] names, boolean nullary )
	{
		this.names = names;
		this.nullary = nullary;
	}

	public boolean match( String arg )
	{
		for( int i=0; i<names.length; i++ )
			if( names[i].equals( arg ) )
				return existing = true;
		return false;
	}

	boolean isNullary()
	{
		return nullary;
	}

	public boolean isExisting()
	{
		return existing;
	}

	void setExisting( boolean existing )
	{
		this.existing = existing;
	}

	public String getParameter()
	{
		return parameter;
	}

	void setParameter( String parameter )
	{
		this.parameter = parameter;
	}
}
