package sample.java.security.signature;

import org.junit.Test;

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;

public class ECDSA extends Signature {
	public ECDSA() {
		this.data = "password";
		this.signature = "hZ9h4laf5B/tVysrQIg540NXSV6bqmihbdZ94Oucc1Ts60QkSHbD5VqPtVIq+urv+tU0Fb7jExKAcKKIsqrzBqkvxUU3whFNV8I84hZbyTmIAPudHmiTBPB/a45VcMiQGuSGDFDenYIlnhIrFkgPb3Mb9gCWY2yCloYJQLfEqZ8=";

		this.algorithm = "EC";
		this.signatureAlgorithm = "SHA1withECDSA";

		this.privateKey = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCBf209cPJO3DXucZHLmf0OU6tBSLETWN38LPZLKITfuQQ==";
		this.publicKey = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEmdLXOGQY2j8/siqCVGEAI2xC8BEjeXYWuMFoQ3GloWvSwpXXZvluD8ZnNmMNu8AQ8ny5ihh12HBT7GpRN5AQnA==";
	}

	@Test
	public void keypair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);

		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
		System.out.println("privateKey: " + Base64.encodeBase64String(privateKey.getEncoded()));

		ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
		System.out.println("publicKey: " + Base64.encodeBase64String(publicKey.getEncoded()));
	}

	@Test
	public void sign() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, SignatureException {
		super.sign();
	}

	@Test
	public void verify() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, SignatureException {
		super.verify();
	}
}
