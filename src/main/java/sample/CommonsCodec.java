package sample;

import org.apache.commons.codec.binary.Base64;

public class CommonsCodec {


	public static void main(String[] args) {
		String plainText = "password";


		System.out.println("Base64.encode(): " + Base64.encodeBase64String(plainText.getBytes()));
		System.out.println("Base64.decode(): " + new String(Base64.decodeBase64("cGFzc3dvcmQ=")));
	}
}
