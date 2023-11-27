package com.Attendance.UserSwipes.ExceptionHandler;

public class RateLimiterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RateLimiterException(String msg) {
		super(msg);
	} 
	
	

}
