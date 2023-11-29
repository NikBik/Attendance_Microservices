package com.Attendance.Security;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class OAuth2TokenCustomValidatorTest {

	private OAuth2TokenCustomValidator validator;

	@BeforeEach
	public void setUp() {
		String expectedAudience = "expectedAudience";
		validator = new OAuth2TokenCustomValidator(expectedAudience);
	}

	@Test
	public void testValidateTokenWithMatchingAudience() {
		String tokenValue = "sampleTokenValue";
        Instant issuedAt = Instant.now(); 
        Instant expiresAt = issuedAt.plusSeconds(3600); 

        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", "user123");
        claims.put("scope", "read");
        claims.put("aud", Arrays.asList("expectedAudience"));
        Jwt jwt = new Jwt(tokenValue, issuedAt, expiresAt, headers, claims);

		OAuth2TokenValidatorResult validationResult = validator.validate(jwt);

		Assertions.assertFalse(validationResult.hasErrors());
	}

	@Test
	public void testValidateTokenWithMismatchingAudience() {
        String tokenValue = "sampleTokenValue";
        Instant issuedAt = Instant.now(); 
        Instant expiresAt = issuedAt.plusSeconds(3600); 

        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");

        Map<String, Object> claims = new HashMap<>();

        claims.put("sub", "user123");
        claims.put("scope", "read");
        claims.put("aud", Arrays.asList("otherAudience"));

        Jwt jwt = new Jwt(tokenValue, issuedAt, expiresAt, headers, claims);
		OAuth2TokenValidatorResult validationResult = validator.validate(jwt);
		Assertions.assertTrue(validationResult.hasErrors());
		OAuth2Error error = validationResult.getErrors().iterator().next();
		Assertions.assertEquals(OAuth2ErrorCodes.INVALID_TOKEN, error.getErrorCode());
	}
}
