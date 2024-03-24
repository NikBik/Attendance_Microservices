package com.Attendance.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

		http.authorizeExchange().anyExchange().authenticated().and()
		.oauth2Client() /*
						 * allows the application to act as
						 * an OAuth 2.0 client by performing
						 * operations like obtaining access
						 * tokens from an authorization
						 * server.
						 */
				.and().oauth2ResourceServer().jwt();
		/*
		 * This method enables and configures the OAuth 2.0 resource server support in
		 * the application. A resource server is responsible for serving protected
		 * resources to clients that provide valid access tokens. In this case, the
		 * resource server is configured to validate JSON Web Tokens (JWT) as access
		 * tokens.
		 */
		/*
		 * .authorizeExchange(exchanges -> exchanges
		 * .pathMatchers("/public/**").permitAll()
		 * .pathMatchers("/admin/**").hasRole("ADMIN") .anyExchange().authenticated() //
		 * Require authentication for all other requests )
		 */

		return http.build();
	}
}
