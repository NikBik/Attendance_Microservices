package com.Attendance.Controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Attendance.DTO.AuthenticateResponseDTO;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
	
	@GetMapping(value="/login")
	public ResponseEntity<AuthenticateResponseDTO> login(@AuthenticationPrincipal OAuth2AuthenticationToken authToken, Model model,
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client) {
		
		OAuth2AuthenticatedPrincipal oidcUser=(OidcUser) authToken.getPrincipal();
		AuthenticateResponseDTO authenticateResponse = AuthenticateResponseDTO.build(
				client.getAccessToken().getTokenValue(), client.getRefreshToken().getTokenValue(),
				oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
				client.getAccessToken().getExpiresAt().getEpochSecond(), oidcUser.getAttribute("email"));
		return new ResponseEntity<AuthenticateResponseDTO>(authenticateResponse, HttpStatus.OK);

	}
}
