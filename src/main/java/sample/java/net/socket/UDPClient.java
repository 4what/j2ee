package sample.java.net.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPClient {


	public static void main(String[] args) throws IOException {
		DatagramSocket socket = null;

		try {
			int port = 8888;

			socket = new DatagramSocket();


			//
			byte[] output = String.valueOf(new Date()).getBytes();

			DatagramPacket packet = new DatagramPacket(output, output.length, InetAddress.getByName("localhost"), port);

			socket.send(packet);


			//
			byte[] input = new byte[256];

			packet = new DatagramPacket(input, input.length);

			System.out.println("***** listening *****");

			socket.receive(packet);

			System.out.println(new String(input, 0, packet.getLength()));
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
}
