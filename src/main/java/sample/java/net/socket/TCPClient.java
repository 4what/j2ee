package sample.java.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TCPClient {


	public static void main(String[] args) throws IOException {
		Socket socket = null;

		PrintWriter writer = null;
		BufferedReader reader = null;

		try {
			int port = 4444;

			socket = new Socket("localhost", port);


			//
			writer = new PrintWriter(socket.getOutputStream());

			String output = String.valueOf(new Date());

			writer.write(output);

			writer.flush();

			socket.shutdownOutput();


			//
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String input;

			while ((input= reader.readLine()) != null) {
				System.out.println(input);
			}

			socket.shutdownInput();
		} finally {
			if (reader != null) {
				reader.close();
			}

			if (writer != null) {
				writer.close();
			}

			if (socket != null) {
				socket.close();
			}
		}
	}
}
