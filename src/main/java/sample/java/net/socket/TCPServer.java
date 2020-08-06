package sample.java.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {


	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		Socket socket = null;

		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
			int port = 4444;

			serverSocket = new ServerSocket(port);

			System.out.println("***** listening *****");

			socket = serverSocket.accept();


			//
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String input;

			while ((input = reader.readLine()) != null) {
				System.out.println(input);
			}

			socket.shutdownInput();


			//
			writer = new PrintWriter(socket.getOutputStream());

			String output = socket.getInetAddress().getHostAddress();

			writer.write(output);

			writer.flush();

			socket.shutdownOutput();
		} finally {
			if (writer != null) {
				writer.close();
			}

			if (reader != null) {
				reader.close();
			}

			if (socket != null) {
				socket.close();
			}

			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}
}
