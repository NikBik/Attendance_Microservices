package com.Attendance.Process_UserSwipes_Microservice.DTO;

import java.time.LocalDateTime;

public class UserSwipeInOutTimings {
	
	private Integer userId;
	private LocalDateTime swipeIn;
	private LocalDateTime swipeOut;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public LocalDateTime getSwipeIn() {
		return swipeIn;
	}
	public void setSwipeIn(LocalDateTime swipeIn) {
		this.swipeIn = swipeIn;
	}
	public LocalDateTime getSwipeOut() {
		return swipeOut;
	}
	public void setSwipeOut(LocalDateTime swipe) {
		this.swipeOut = swipe;
	}
	

}
