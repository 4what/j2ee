package sample.java.net.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {


	public static void main(String[] args) throws IOException {
		DatagramSocket socket = null;

		try {
			int port = 8888;

			socket = new DatagramSocket(port);


			//
			byte[] input = new byte[256];

			DatagramPacket packet = new DatagramPacket(input, input.length);

			System.out.println("***** listening *****");

			socket.receive(packet);

			System.out.println(new String(input, 0, packet.getLength()));


			//
			byte[] output = packet.getAddress().getHostAddress().getBytes();

			socket.send(new DatagramPacket(output, output.length, packet.getAddress(), packet.getPort()));
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
}
