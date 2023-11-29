package com.Attendance.Process_UserSwipes_Microservice.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class UserDetailsEntityTest {

	@Test
	void testUserDetailsEntity() {
		UserDetailsEntity userDetails = new UserDetailsEntity();
		List<UserSwipesEntity> user = new ArrayList<>();
		user.add(new UserSwipesEntity());

		userDetails.setUserId(1);
		userDetails.setFirstName("John");
		userDetails.setLastName("Doe");
		userDetails.setContactCode("AB123");
		userDetails.setUserSwipes(user);

		int userId = userDetails.getUserId();
		String firstName = userDetails.getFirstName();
		String lastName = userDetails.getLastName();
		String contactCode = userDetails.getContactCode();

		assertEquals(1, userId);
		assertEquals("John", firstName);
		assertEquals("Doe", lastName);
		assertEquals("AB123", contactCode);
	}

	@Test
	void testToString() {
		UserDetailsEntity userDetails = new UserDetailsEntity();

		userDetails.setUserId(1);
		userDetails.setFirstName("John");
		userDetails.setLastName("Doe");
		userDetails.setContactCode("AB123");
		int userId = userDetails.getUserId();
		String firstName = userDetails.getFirstName();
		String lastName = userDetails.getLastName();
		String contactCode = userDetails.getContactCode();

		String user = "UserDetailsEntity [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userSwipes=" + null + ", contactCode=" + contactCode + "]";
		assertEquals(user, userDetails.toString());
	}

}
