package sample.java.net.socket;

import java.net.SocketException;

public class UDPMultiServer {


	public static void main(String[] args) throws SocketException {
		new UDPMultiServerThread().start();
	}
}
