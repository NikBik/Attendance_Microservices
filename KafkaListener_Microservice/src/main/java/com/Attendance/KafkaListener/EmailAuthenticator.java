package com.Attendance.KafkaListener;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticator extends Authenticator {
	private String username;
	private String password;

	public EmailAuthenticator(@Value("${email.username}") String username,
			@Value("${email.password}") String password) {
		this.username = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
