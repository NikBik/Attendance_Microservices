package com.Attendance.Process_UserSwipes_Microservice.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="SWIPE_RECORDS")
@Entity
public class UserSwipesEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer swipeRecordId;
	private UserDetailsEntity userId;
	private LocalDateTime timeStamp;
	private Boolean isSwipeIn;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SWIPE_RECORD_ID")
	public Integer getSwipeRecordId() {
		return swipeRecordId;
	}
	public void setSwipeRecordId(Integer swipeRecordId) {
		this.swipeRecordId = swipeRecordId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL) //cascade helps that all state transitions should be cascaded from parent to child 
	@JoinColumn(name="USER_ID",nullable=false)
	public UserDetailsEntity getUserId() {
		return userId;
	}
	public void setUserId(UserDetailsEntity userId) {
		this.userId = userId;
	}
	
	@Column(name="TIMESTAMP_INFO")
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@Column(name="IS_SWIPE_IN")
	public Boolean getIsSwipeIn() {
		return isSwipeIn;
	}
	public void setIsSwipeIn(Boolean isSwipeIn) {
		this.isSwipeIn = isSwipeIn;
	}
}
