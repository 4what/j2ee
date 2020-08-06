package sample.java.security.signature;

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Signature {
	protected String data;
	protected String signature;

	protected String algorithm;
	protected String signatureAlgorithm;

	protected String privateKey;
	protected String publicKey;

	protected void sign() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, SignatureException {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(this.privateKey)));

		java.security.Signature signature = java.security.Signature.getInstance(signatureAlgorithm);
		signature.initSign(privateKey);

		signature.update(data.getBytes());

		System.out.println("sign: " + Base64.encodeBase64String(signature.sign()));
	}

	protected void verify() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, SignatureException {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

		PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(this.publicKey)));

		java.security.Signature signature = java.security.Signature.getInstance(signatureAlgorithm);
		signature.initVerify(publicKey);

		signature.update(data.getBytes());

		System.out.println("verify: " + signature.verify(Base64.decodeBase64(this.signature.getBytes())));
	}
}
