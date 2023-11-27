package com.Attendance.UserSwipes.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserSwipe implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;

	@NotNull(message = "{validation.first.name.empty}")
	@Size(min = 2, max = 10,message = "{validation.first.name.notInRange}")
	private String firstName;

	@NotNull(message = "{validation.last.name.empty}")
	@Size(min = 2, max = 10,message = "{validation.last.name.notInRange}")
	private String lastName;
	
	@NotNull(message = "{validation.contact.code.empty}")
	@Size(min = 2, max = 10,message = "{validation.contact.code.inValid}")
	private String contactCode;

	@NotNull(message = "{validation.swipeIn.empty}")
	private Boolean isSwipeIn;

	private LocalDateTime time;

	public String getContactCode() {
		return contactCode;
	}

	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getIsSwipeIn() {
		return isSwipeIn;
	}

	public void setIsSwipeIn(Boolean isSwipeIn) {
		this.isSwipeIn = isSwipeIn;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "UserSwipe [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", contactCode="+ contactCode +", isSwipeIn="
				+ isSwipeIn + ", time=" + time + "]";
	}
	
}
