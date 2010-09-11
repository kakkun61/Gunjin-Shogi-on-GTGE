package kakkun61.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Arrays;

public class Communiation
{
	private BufferedReader reader;
	private DatagramSocket udpSock;
	private InetAddress broadcastAddress;
	private ServerSocket serverSock;
	private Socket sock;
	private SocketAddress destSockAddress;

	private static final int QUIT = 1;
	private static int udpReceivePort = 4423,
	                   serverPort = 4424,
	                   port = 4425;

	public static void main( String[] args ) throws IOException
	{
		new Communiation( args ).run();
	}

	public Communiation( String[] args ) throws IOException
	{
		parseOptions( args );
		reader = new BufferedReader( new InputStreamReader( System.in ) );
		udpSock = new DatagramSocket( udpReceivePort );
		System.err.println( "bind DatagramSocket to " + udpReceivePort );
		broadcastAddress = NetworkInterface.getNetworkInterfaces().nextElement().getInterfaceAddresses().get( 0 ).getBroadcast();
	}

	private void parseOptions( String[] args )
	{
		for( int i=0; i<args.length; )
		{
			if( "-udp".equals( args[i] ) )
			{
				udpReceivePort = Integer.parseInt( args[i+1] );
				i += 2;
			}
			else if( "-server".equals( args[i] ) )
			{
				serverPort = Integer.parseInt( args[i+1] );
				i += 2;
			}
			else
				i++;
		}

	}

	private void run() throws IOException
	{
		while( core() != QUIT );
	}

	private int core() throws IOException
	{
		System.out.print( "> " );
		String[] cmds = reader.readLine().split( " " );

		if( "quit".equals( cmds[0] ) )
		{
			System.out.println( "bye" );
			return QUIT;
		}
		else if( "broadcast".equals( cmds[0] ) || "bc".equals( cmds[0] ) )
			return doBroadcast( cmds );
		else if( "udprcv".equals( cmds[0] ) )
			return doUdprcv( cmds );
		else if( "connect".equals( cmds[0] ) || "cnct".equals( cmds[0] ) )
			return doConnect( cmds );
		else if( "accept".equals( cmds[0] ) || "acpt".equals( cmds[0] ) )
			return doAccept( cmds );

		System.err.println( cmds[0] + " は有効なコマンドではありません。" );
		return -1;
	}

	private int doBroadcast( String[] cmds ) throws IOException
	{
		if( serverSock == null )
			serverSock = new ServerSocket( serverPort );
		byte[] data = ("recruit " + serverPort).getBytes();
		if( cmds.length == 1 )
			broadcast( data, udpReceivePort );
		else
			for( int i=0; i<cmds.length-1; i++ )
				broadcast( data, Integer.parseInt( cmds[i+1] ) );
		return 0;
	}

	private int doUdprcv( String[] cmds ) throws IOException
	{
		DatagramPacket pack = new DatagramPacket( new byte[1024], 1024 );
		udpSock.receive( pack );
		destSockAddress = new InetSocketAddress( pack.getAddress(), Integer.parseInt( new String( chop( pack.getData() ) ).split( " " )[1] ) );
		System.err.println( "receive from " + pack.getSocketAddress() + ": " + new String( pack.getData() ) );
		return 0;
	}

	private byte[] chop( byte[] raw )
	{
		int len;
		for( len=0; len<raw.length; len++ )
			if( raw[len] == 0 )
				break;
		return Arrays.copyOf( raw, len );
	}

	private int doConnect( String[] cmds ) throws IOException
	{
		int firstPort = port;
		while( true )
		{
			if( port < firstPort+10 )
			{
				try
				{
					sock = new Socket( "localhost", port++ );
					break;
				}
				catch( BindException e )
				{
					System.err.println( e.getLocalizedMessage() );
				}
			}
			else
			{
				throw new SocketException( "Can't bind port: " + firstPort + "-" + (port-1) );
			}
		}
		sock.connect( destSockAddress );
		System.err.println( "connect successfully" );
		if( serverSock != null )
		{
			serverSock.close();
			serverSock = null;
			System.err.println( "serverSock closed" );
		}
		return 0;
	}

	private int doAccept( String[] cmds ) throws IOException
	{
		sock = serverSock.accept();
		System.err.println( "accept: " + sock );
		return 0;
	}

	private void broadcast( byte[] data, int port ) throws SocketException, IOException
	{
		udpSock.send( new DatagramPacket( data, data.length, new InetSocketAddress( broadcastAddress, port ) ) );
		System.err.println( "broadcast to " + port + ": " + new String( data ) );
	}
}
