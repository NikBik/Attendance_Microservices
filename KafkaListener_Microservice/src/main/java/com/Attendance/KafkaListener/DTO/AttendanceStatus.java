package com.Attendance.KafkaListener.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class AttendanceStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String contactCode;
	private LocalDate date;
	private String status;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getContactCode() {
		return contactCode;
	}
	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
