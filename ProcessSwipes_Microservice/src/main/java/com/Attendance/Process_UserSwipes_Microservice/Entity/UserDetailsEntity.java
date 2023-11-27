package com.Attendance.Process_UserSwipes_Microservice.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "USER_DETAILS")
@Entity
@Access(AccessType.PROPERTY)
public class UserDetailsEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String firstName;
	private String lastName;
	private List<UserSwipesEntity> userSwipes;
	private String contactCode;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	public Integer getUserId() {
		return userId;
	}

	@Column(name="CONTACT_CODE")
	public String getContactCode() {
		return contactCode;
	}

	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@OneToMany(mappedBy="userId")
	public List<UserSwipesEntity> getUserSwipes() {
		return userSwipes;
	}

	public void setUserSwipes(List<UserSwipesEntity> userSwipes) {
		this.userSwipes = userSwipes;
	}

	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserDetailsEntity [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userSwipes=" + userSwipes + ", contactCode=" + contactCode + "]";
	}
}
