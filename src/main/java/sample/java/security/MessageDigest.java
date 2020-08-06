package sample.java.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;

public class MessageDigest {


	public static void main(String[] args) throws NoSuchAlgorithmException {
		String plainText = "password";


		java.security.MessageDigest md5 = java.security.MessageDigest.getInstance("MD5");
		System.out.println("MD5: " + Hex.encodeHexString(md5.digest(plainText.getBytes())));

		System.out.println(DigestUtils.md5Hex(plainText));


		java.security.MessageDigest sha1 = java.security.MessageDigest.getInstance("SHA-1");
		System.out.println("SHA1: " + Hex.encodeHexString(sha1.digest(plainText.getBytes())));

		System.out.println(DigestUtils.sha1Hex(plainText));
	}
}
