package kakkun61.gunjinshogi.net;

import java.io.BufferedReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

public class Communicator
{// TODO
	private InetSocketAddress udpReceiveAddress;
	private InetAddress broadcastAddress;
	private static final int DEFULT_UDP_RECEIVE_PORT = 4423;
	private int serverPort = 4424,
	            generalPort = 4425;

	private BufferedReader reader;
	private DatagramSocket udpSock;

	public Communicator() throws SocketException
	{
		broadcastAddress = NetworkInterface.getNetworkInterfaces().nextElement().getInterfaceAddresses().get( 0 ).getBroadcast();
		udpReceiveAddress = new InetSocketAddress( broadcastAddress, DEFULT_UDP_RECEIVE_PORT );
	}

	public void setUdpReceivePort( int udpReceivePort )
	{
		if( udpReceiveAddress.getPort() != udpReceivePort )
			udpReceiveAddress = new InetSocketAddress( broadcastAddress, udpReceivePort );
	}

	public void setServerPort( int serverPort )
	{
		this.serverPort = serverPort;
	}

	public void setGeneralPort( int generalPort )
	{
		this.generalPort = generalPort;
	}

}
