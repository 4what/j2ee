package sample.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.Base64;

import java.util.Calendar;
import java.util.Date;

public class JavaJWT {


	public static void main(String[] args) {
		Algorithm algorithm = Algorithm.HMAC256("password");


		/* encode */
		Date now = new Date();

		Calendar exp = Calendar.getInstance();
		exp.setTime(now);
		exp.add(Calendar.MONTH, 1);

		String token = JWT.create()
			.withIssuer("www.example.com")

			.withAudience("id")

			.withIssuedAt(now)
			.withExpiresAt(exp.getTime())

			.withClaim("key", "value")

			.sign(algorithm);
		System.out.println("token: " + token);


		/* decode */
		try {
			JWTVerifier verifier = JWT
				.require(algorithm)

				//.withIssuer("www.example.com")

				//.withClaim("key", "value")

				.build();

			DecodedJWT jwt = verifier.verify(token);

			System.out.println("header: " + new String(Base64.decodeBase64(jwt.getHeader())));

			System.out.println("payload: " + new String(Base64.decodeBase64(jwt.getPayload())));

			System.out.println("iss: " + jwt.getIssuer());
			System.out.println("aud: " + jwt.getAudience());
			System.out.println("iat: " + jwt.getIssuedAt());
			System.out.println("exp: " + jwt.getExpiresAt());
		} catch (JWTVerificationException e) {
			e.printStackTrace();

			System.out.println("***** illegal *****");
		}


		/* Note that this method doesn't verify the token's signature! Use it only if you trust the token or you already verified it. */
		System.out.println(new String(Base64.decodeBase64(JWT.decode(token).getPayload())));
	}
}
