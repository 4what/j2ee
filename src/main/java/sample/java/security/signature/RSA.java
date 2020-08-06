package sample.java.security.signature;

import org.junit.Test;

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

public class RSA extends Signature {
	public RSA() {
		this.data = "password";
		this.signature = "hZ9h4laf5B/tVysrQIg540NXSV6bqmihbdZ94Oucc1Ts60QkSHbD5VqPtVIq+urv+tU0Fb7jExKAcKKIsqrzBqkvxUU3whFNV8I84hZbyTmIAPudHmiTBPB/a45VcMiQGuSGDFDenYIlnhIrFkgPb3Mb9gCWY2yCloYJQLfEqZ8=";

		this.algorithm = "RSA";
		this.signatureAlgorithm = "SHA1withRSA"; // TODO: MD5withRSA

		this.privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIx1b0iNp0RiKmF3c547sMcBAGZG9pjphhOoVjW3yreVBi3wt4KubkW34A/hqT4YyeGCUGyBfzsSETjdoACP3t4qDpSRgTkTO3IdzQ6MQIg0NrH+et532T2kyyCFBND8dfv7gweE8O77wLbepedfL8Und/CRu9uUpenbMmuJiLyJAgMBAAECgYA0nh2lOWOq63di8eO/KC9OLaLfrb/dgsX2gT5ie0qWwV4CQcYNT5kBxyNJ78h9+b1PHIlSTKTEFT2cZvH0o/DiDMNArodp3+ixFMgAZ15+Ea5CDR5Gvnx8c7NlwfPyW5pGLmMA6sO+gNAwsaQ1bk2V/Ylm4K+JgiQtCho+fpz2jQJBAMuJa3RqrtW2LuD4beTO4sSsKdxuKDaxjVO23w9C71T/RF/TWMBXs7yXrLKUoqwh/PFGf+LE+8e+jBOM58YpfMcCQQCwqb7J+09oAXuc6sNCIjTpxtM3RAexqbUBzVhwI9+te+7gUQ/73dxnpGZHK0CbSjcqwsAWE0lZP2w6qrYw4owvAkBndzTjmjPEOfd8r20VykGmnqos/uZ8XyerraOYm3sUZys31zUfmbWpoiTNJxfGjwSJFkB+Y4pmUKveaFuN9ZsTAkEAlBhIKxQ0ddDAakPXGaqJRux5BZxPAt1sMq2MlL1N9iWtk3gA+Tf8WxnS75hPmZ52bq9BzX+vflSPiwKupn5T2wJAH+mFZsjkBp3AhlnMs/aAywUZphwOEiznMvvj6vg83zokTTygUJtr+1x+RJNA+rda4UWY3cu1TiZVFwFYKBUr9g==";
		this.publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMdW9IjadEYiphd3OeO7DHAQBmRvaY6YYTqFY1t8q3lQYt8LeCrm5Ft+AP4ak+GMnhglBsgX87EhE43aAAj97eKg6UkYE5EztyHc0OjECINDax/nred9k9pMsghQTQ/HX7+4MHhPDu+8C23qXnXy/FJ3fwkbvblKXp2zJriYi8iQIDAQAB";
	}

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
	public void sign() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, SignatureException {
		super.sign();
	}

	@Test
	public void verify() throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, SignatureException {
		super.verify();
	}
}
