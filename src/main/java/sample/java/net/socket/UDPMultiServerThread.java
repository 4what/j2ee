package sample.java.net.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPMultiServerThread extends Thread {
	private DatagramSocket socket;

	private boolean running = true;

	public UDPMultiServerThread() throws SocketException {
		int port = 8888;

		socket = new DatagramSocket(port);
	}

	@Override
	public void run() {
		while (running) {
			try {
				//
				byte[] input = new byte[256];

				DatagramPacket packet = new DatagramPacket(input, input.length);

				System.out.println("***** listening *****");

				socket.receive(packet);

				System.out.println(new String(input, 0, packet.getLength()));


				//
				byte[] output = packet.getAddress().getHostAddress().getBytes();

				socket.send(new DatagramPacket(output, output.length, packet.getAddress(), packet.getPort()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		socket.close();
	}
}
