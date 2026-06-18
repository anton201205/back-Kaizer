error id: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/auth/JwtService.java:_empty_/`<any>`#issuedAt#expiration#claim#
file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/auth/JwtService.java
empty definition using pc, found symbol in pc: _empty_/`<any>`#issuedAt#expiration#claim#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1246
uri: file:///C:/Users/USER/Downloads/Kaizer-Back-main/Kaizer-Back-main/src/main/java/com/example/Kaizer_Back/auth/JwtService.java
text:
```scala
package com.example.Kaizer_Back.auth;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.Kaizer_Back.usuario.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final SecretKey key;
	private final long expirationMinutes;

	public JwtService(
			@Value("${app.jwt.secret}") String secret,
			@Value("${app.jwt.expiration-minutes}") long expirationMinutes
	) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		this.expirationMinutes = expirationMinutes;
	}

	public String generateToken(UserDetails userDetails) {
		Instant now = Instant.now();
		Instant exp = now.plus(expirationMinutes, ChronoUnit.MINUTES);

		var builder = Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(Date.from(now))
				.expiration(Date.from(exp));

				
		if (userDetails instanceof Usuario usuario) {
			builder.claim@@("userId", usuario.getId());
		}

		return builder.signWith(key).compact();
	}

	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	public Long extractUserId(String token) {
		Claims claims = extractAllClaims(token);
		Object userId = claims.get("userId");
		if (userId == null) return null;
		if (userId instanceof Long l) return l;
		if (userId instanceof Integer i) return i.longValue();
		return Long.valueOf(userId.toString());
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/`<any>`#issuedAt#expiration#claim#