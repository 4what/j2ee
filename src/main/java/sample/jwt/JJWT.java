package sample.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.HashMap;
import java.util.Map;

public class JJWT {


	public static void main(String[] args) {
		String secretKey = "password";


		/* encode */
		Map<String, Object> payload = new HashMap<>();
		payload.put("key", "value");

		String token = Jwts.builder()
			.setClaims(payload)
			.signWith(SignatureAlgorithm.HS512, secretKey)
			.compact();
		System.out.println("token: " + token);


		/* decode */
		try {
			System.out.println("payload: " + Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody());
		} catch (SignatureException e) {
			e.printStackTrace();
		}
	}
}
