package sample.java.security;

import org.junit.Test;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * TODO: Bouncy Castle Provider
 */
public class AES {
	private String plainText = "password";
	private String cipherText = "97noXxs+OzOIw2mvsVsOJA==";

	private String algorithm = "AES";
	private String transformation = "AES/CBC/PKCS5Padding"; // TODO: PKCS #5

	private String key = "RCGKqX+6TI99H8kkQGXpMg==";
	private String iv = "15Zx6IruxskqBJLOsvvcDA==";

	@Test
	public void key() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

		SecretKey secretKey = keyGenerator.generateKey();

		System.out.println("key: " + Base64.encodeBase64String(secretKey.getEncoded()));
	}

	@Test
	public void iv() {
		byte[] bytes = new byte[16];

		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(bytes);

		System.out.println("iv: " + Base64.encodeBase64String(bytes));
	}

	@Test
	public void encrypt() throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(this.key), algorithm);
		IvParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(this.iv));

		Cipher cipher = Cipher.getInstance(transformation);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);

		System.out.println("encrypt: " + Base64.encodeBase64String(cipher.doFinal(plainText.getBytes())));
	}

	@Test
	public void decrypt() throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		SecretKeySpec key = new SecretKeySpec(Base64.decodeBase64(this.key), algorithm);
		IvParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(this.iv));

		Cipher cipher = Cipher.getInstance(transformation);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);

		System.out.println("decrypt: " + new String(cipher.doFinal(Base64.decodeBase64(cipherText))));
	}
}
