package com.Attendance.KafkaListener.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserDetailsTest {

    @Test
    void testUserDetailsGettersAndSetters() {
        // Create UserDetails object
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);
        userDetails.setFirstName("John");
        userDetails.setLastName("Doe");
        userDetails.setContactCode("ABC123");
        userDetails.setStatus("Present");

        // Test getters
        assertEquals(1, userDetails.getUserId(), "User ID should match");
        assertEquals("John", userDetails.getFirstName(), "First name should match");
        assertEquals("Doe", userDetails.getLastName(), "Last name should match");
        assertEquals("ABC123", userDetails.getContactCode(), "Contact code should match");
        assertEquals("Present", userDetails.getStatus(), "Status should match");
    }

    @Test
    void testUserDetailsToString() {
        // Create UserDetails object
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);
        userDetails.setFirstName("John");
        userDetails.setLastName("Doe");
        userDetails.setContactCode("ABC123");
        userDetails.setStatus("Present");

        // Test toString method
        String expectedString = "UserDetails [userId=1, firstName=John, lastName=Doe, contactCode=ABC123, status=Present]";
        assertEquals(expectedString, userDetails.toString(), "toString should match the expected string");
    }
}
