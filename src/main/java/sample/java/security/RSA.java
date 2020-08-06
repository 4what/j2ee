package sample.java.security;

import org.junit.Test;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {
	private String plainText = "password";
	private String cipherText = "V0RnEuyInRd6yGwwdGCXAVYWA2m9foTR/0bfY/aGLXiYuN/lfTYb2fMt6M2fFi49BRTXbVWXTlTpC2uTY2e1OrKHfhctOn1PV1zQ28zg68G5287cHXiN5+N9tIvPGjy+FXX8DdWa5IlSgAoVsVDwdhtrDPflW8/cyrwpsbueViA=";

	private String algorithm = "RSA";
	private String transformation = "RSA"; // TODO: PKCS #8

	private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIx1b0iNp0RiKmF3c547sMcBAGZG9pjphhOoVjW3yreVBi3wt4KubkW34A/hqT4YyeGCUGyBfzsSETjdoACP3t4qDpSRgTkTO3IdzQ6MQIg0NrH+et532T2kyyCFBND8dfv7gweE8O77wLbepedfL8Und/CRu9uUpenbMmuJiLyJAgMBAAECgYA0nh2lOWOq63di8eO/KC9OLaLfrb/dgsX2gT5ie0qWwV4CQcYNT5kBxyNJ78h9+b1PHIlSTKTEFT2cZvH0o/DiDMNArodp3+ixFMgAZ15+Ea5CDR5Gvnx8c7NlwfPyW5pGLmMA6sO+gNAwsaQ1bk2V/Ylm4K+JgiQtCho+fpz2jQJBAMuJa3RqrtW2LuD4beTO4sSsKdxuKDaxjVO23w9C71T/RF/TWMBXs7yXrLKUoqwh/PFGf+LE+8e+jBOM58YpfMcCQQCwqb7J+09oAXuc6sNCIjTpxtM3RAexqbUBzVhwI9+te+7gUQ/73dxnpGZHK0CbSjcqwsAWE0lZP2w6qrYw4owvAkBndzTjmjPEOfd8r20VykGmnqos/uZ8XyerraOYm3sUZys31zUfmbWpoiTNJxfGjwSJFkB+Y4pmUKveaFuN9ZsTAkEAlBhIKxQ0ddDAakPXGaqJRux5BZxPAt1sMq2MlL1N9iWtk3gA+Tf8WxnS75hPmZ52bq9BzX+vflSPiwKupn5T2wJAH+mFZsjkBp3AhlnMs/aAywUZphwOEiznMvvj6vg83zokTTygUJtr+1x+RJNA+rda4UWY3cu1TiZVFwFYKBUr9g==";
	private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMdW9IjadEYiphd3OeO7DHAQBmRvaY6YYTqFY1t8q3lQYt8LeCrm5Ft+AP4ak+GMnhglBsgX87EhE43aAAj97eKg6UkYE5EztyHc0OjECINDax/nred9k9pMsghQTQ/HX7+4MHhPDu+8C23qXnXy/FJ3fwkbvblKXp2zJriYi8iQIDAQAB";

	@Test
	public void keypair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);

		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		System.out.println("privateKey: " + Base64.encodeBase64String(privateKey.getEncoded()));

		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		System.out.println("publicKey: " + Base64.encodeBase64String(publicKey.getEncoded()));
	}

	@Test
	public void encrypt() throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(this.publicKey)));

		Cipher cipher = Cipher.getInstance(transformation);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		System.out.println("encrypt: " + Base64.encodeBase64String(cipher.doFinal(plainText.getBytes())));
	}

	@Test
	public void decrypt() throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(this.privateKey)));

		Cipher cipher = Cipher.getInstance(transformation);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		System.out.println("decrypt: " + new String(cipher.doFinal(Base64.decodeBase64(cipherText))));
	}
}
