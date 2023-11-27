package com.Attendance.DTO;

import java.util.Collection;

public class AuthenticateResponseDTO {
	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expiresAt;
	private Collection<String> authorityList;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public long getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(long expiresAt) {
		this.expiresAt = expiresAt;
	}
	public Collection<String> getAuthorityList() {
		return authorityList;
	}
	public void setAuthorityList(Collection<String> authorityList) {
		this.authorityList = authorityList;
	}
	
	public static AuthenticateResponseDTO build(String accessToken,String refreshToken,Collection<String>
	 authorityList,Long expiresAt,String userId) {
		AuthenticateResponseDTO authenticateResponseDTO= new AuthenticateResponseDTO();
		authenticateResponseDTO.setAccessToken(accessToken);
		authenticateResponseDTO.setAuthorityList(authorityList);
		authenticateResponseDTO.setExpiresAt(expiresAt);
		authenticateResponseDTO.setRefreshToken(refreshToken);
		authenticateResponseDTO.setUserId(userId);
		return authenticateResponseDTO;
	}
		

}