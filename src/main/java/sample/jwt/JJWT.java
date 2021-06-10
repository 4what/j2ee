package sample.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//
// TODO: base64url
//
public class JJWT {


	public static void main(String[] args) {
		String key = Base64.encodeBase64String(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
		System.out.println("key: " + key);


		/* encode */
		Date now = new Date();

		Calendar exp = Calendar.getInstance();
		exp.setTime(now);
		exp.add(Calendar.MONTH, 1);

		Map<String, Object> data = new HashMap<>();
		data.put("key", "value");

		String token = Jwts.builder()
			.setIssuer("www.example.com")

			.setAudience("id")

			.setIssuedAt(now)
			.setExpiration(exp.getTime())

			//.claim("key", "value")
			.addClaims(data)

			.signWith(Keys.hmacShaKeyFor(key.getBytes()))

			.compact();
		System.out.println("token: " + token);


		/* decode */
		try {
			Jws<Claims> jwt = Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))

				//.requireIssuer("www.example.com")

				//.require("key", "value")

				.build()

				.parseClaimsJws(token);

			JwsHeader header = jwt.getHeader();
			System.out.println("header: " + header);

			Claims payload = jwt.getBody();
			System.out.println("payload: " + payload);

			System.out.println("iss: " + payload.getIssuer());
			System.out.println("aud: " + payload.getAudience());
			System.out.println("iat: " + payload.getIssuedAt());
			System.out.println("exp: " + payload.getExpiration());
		} catch (JwtException e) {
			e.printStackTrace();

			System.out.println("***** illegal *****");
		}
	}
}
