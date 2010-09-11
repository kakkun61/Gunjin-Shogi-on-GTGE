package kakkun61.gunjinshogi.util;

import java.util.ArrayList;
import java.util.List;

public class CommandLineOptionParser
{
	private List<CommandLineOption> opts = new ArrayList<CommandLineOption>();

	public void addOption( CommandLineOption opt )
	{
		opts.add( opt );
	}

	public void addOptions( CommandLineOption[] opts )
	{
		for( CommandLineOption opt : opts )
			this.opts.add( opt );
	}

	public void parse( String[] args ) throws InvalidCommandLineOptionException
	{
		ARG_LOOP: for( int i=0; i<args.length; )
		{
			for( CommandLineOption opt: opts )
				if( opt.match( args[i] ) )
				{
					if( opt.isNullary() )
						i++;
					else
					{
						opt.setParameter( args[i+1] );
						i+=2;
					}
					continue ARG_LOOP;
				}
			throw new InvalidCommandLineOptionException( "invalid command line option: " + args[i] );
		}
	}
}
