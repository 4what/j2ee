package sample.java.net;

import java.net.UnknownHostException;

public class InetAddress {


	public static void main(String[] args) throws UnknownHostException {
		java.net.InetAddress inetAddress = java.net.InetAddress.getByName("localhost");

		System.out.println("getHostAddress(): " + inetAddress.getHostAddress());
		System.out.println("getHostName(): " + inetAddress.getHostName());
	}
}
