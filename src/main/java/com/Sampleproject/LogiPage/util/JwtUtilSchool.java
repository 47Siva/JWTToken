package com.Sampleproject.LogiPage.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.Sampleproject.LogiPage.entity.UserLoginSchool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtilSchool {

	private static String secret = "this is secret";
	private static long expiryDuration = 60 * 60;

	public String generateJwt(UserLoginSchool userLogin) {

		long millTime = System.currentTimeMillis();
		long expiryTime = millTime + expiryDuration * 1000;

		Date issuedAt = new Date(millTime);
		Date expiryAt = new Date(expiryTime);

		int integer = userLogin.getId();
		String issuer = Integer.toString(integer);
		String ISSUER = issuer;

		// clamis
		Claims claims = Jwts.claims().setIssuer(ISSUER).setIssuedAt(issuedAt).setExpiration(expiryAt);
		claims.put("name", userLogin.getUsername());

		// generat jwt using claims
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public void verify(String Authorization) throws Exception {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(Authorization);
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
 