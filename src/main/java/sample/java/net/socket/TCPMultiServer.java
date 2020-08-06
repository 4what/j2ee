package sample.java.net.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class TCPMultiServer {


	public static void main(String[] args) throws IOException {
		int port = 4444;

		ServerSocket serverSocket = new ServerSocket(port);

		System.out.println("***** listening *****");

		boolean listening = true;

		while (listening) {
			new TCPMultiServerThread(serverSocket.accept()).start();
		}
	}
}
