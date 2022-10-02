package com.riscovirtual.mvpmatch.security;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.riscovirtual.mvpmatch.controller.UserController;
import com.riscovirtual.mvpmatch.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtilities {

	private static final Log logger = LogFactory.getLog(JwtTokenUtilities.class);

	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;

	public String generateAccessToken(User user) {
		return Jwts.builder().setSubject(user.getUsername()).setIssuer("RiscoVirtual").setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

	}

	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			logger.error("JWT expired", ex);
		} catch (IllegalArgumentException ex) {
			logger.error("Token is null, empty or only whitespace", ex);
		} catch (MalformedJwtException ex) {
			logger.error("JWT is invalid", ex);
		} catch (UnsupportedJwtException ex) {
			logger.error("JWT is not supported", ex);
		} catch (SignatureException ex) {
			logger.error("Signature validation failed");
		}

		return false;
	}

	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}

	private Claims parseClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}
