package sample.java.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class URL {


	public static void main(String[] args) throws IOException {
		java.net.URL url = new java.net.URL("http://www.example.com/index.html?key=value#hash");

		System.out.println("getAuthority(): " + url.getAuthority());
		System.out.println("getContent(): " + url.getContent());
		System.out.println("getDefaultPort(): " + url.getDefaultPort());
		System.out.println("getFile(): " + url.getFile());
		System.out.println("getHost(): " + url.getHost());
		System.out.println("getPath(): " + url.getPath());
		System.out.println("getPort(): " + url.getPort());
		System.out.println("getProtocol(): " + url.getProtocol());
		System.out.println("getQuery(): " + url.getQuery());
		System.out.println("getRef(): " + url.getRef());
		System.out.println("getUserInfo(): " + url.getUserInfo());


		//
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		try {
			String data;

			while ((data = reader.readLine()) != null) {
				System.out.println(data);
			}
		} finally {
			reader.close();
		}
	}
}
