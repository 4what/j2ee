package sample.java.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URLConnection;

/**
 * https://docs.oracle.com/javase/6/docs/technotes/guides/net/proxies.html
 */
public class Proxy {


	public static void main(String[] args) throws IOException {
		String proxyHost = "192.168.90.254";
		int proxyPort = 8080;

		java.net.URL url = new java.net.URL("http://localhost:8080/");

		URLConnection conn;


		// System Properties
		System.setProperty("http.proxyHost", proxyHost);
		System.setProperty("http.proxyPort", String.valueOf(proxyPort));

		conn = url.openConnection();


		// Proxy class
		SocketAddress address = new InetSocketAddress(proxyHost, proxyPort);
		java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, address);

		//conn = url.openConnection(proxy);


		System.out.println(conn.getInputStream());
	}
}
