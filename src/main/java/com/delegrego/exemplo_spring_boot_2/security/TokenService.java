package com.delegrego.exemplo_spring_boot_2.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	private final JwtEncoder jwtEncoder;
	private final String issuer;
	private final long expirationMinutes;

	public TokenService(JwtEncoder jwtEncoder, @Value("${jwt.issuer}") String issuer,
			@Value("${jwt.expiration-minutes:60}") long expirationMinutes) {

		this.jwtEncoder = jwtEncoder;
		this.issuer = issuer;
		this.expirationMinutes = expirationMinutes;
	}

	public String generateToken(Authentication authentication) {

		Instant now = Instant.now();

		// Extract authenticated user
		FuncionarioDetails userDetails = (FuncionarioDetails) authentication.getPrincipal();

		// Extract roles (ROLE_MANAGER -> MANAGER)
		String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.filter(authority -> authority.startsWith("ROLE_")).map(authority -> authority.substring(5))
				.collect(Collectors.joining(" "));

		JwtClaimsSet claims = JwtClaimsSet.builder().issuer(issuer).issuedAt(now)
				.expiresAt(now.plus(expirationMinutes, ChronoUnit.MINUTES)).subject(authentication.getName())
				.claim("name", userDetails.getName()).claim("roles", roles).build();

		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}