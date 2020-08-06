package sample.java.net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPMultiServerThread extends Thread {
	private Socket socket;

	public TCPMultiServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}

				if (reader != null) {
					reader.close();
				}

				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
