package com.Attendance.Security;

import java.util.List;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class OAuth2TokenCustomValidator implements OAuth2TokenValidator<Jwt> {

	private String audience;
	
	OAuth2TokenCustomValidator(String audience) {
		this.audience = audience;
	}

	public OAuth2TokenValidatorResult validate(Jwt jwt) {
		List<String> audiences = jwt.getAudience();
		if (audiences.contains(this.audience)) {
			return OAuth2TokenValidatorResult.success();
		}
		OAuth2Error err = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN);
		return OAuth2TokenValidatorResult.failure(err);
	}
}
