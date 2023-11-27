package com.Attendance.KafkaListener.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserDetailsEntityTest {

    @Test
    void testUserDetailsEntityGettersAndSetters() {
        // Create UserDetailsEntity object
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity.setUserId(1);
        userDetailsEntity.setFirstName("John");
        userDetailsEntity.setLastName("Doe");
        userDetailsEntity.setContactCode("ABC123");

        AttendanceEntity attendanceEntity = new AttendanceEntity();
        attendanceEntity.setAttendanceId(100);
        userDetailsEntity.setAttendanceEntity(attendanceEntity);

        // Test getters
        assertEquals(1, userDetailsEntity.getUserId(), "User ID should match");
        assertEquals("John", userDetailsEntity.getFirstName(), "First name should match");
        assertEquals("Doe", userDetailsEntity.getLastName(), "Last name should match");
        assertEquals("ABC123", userDetailsEntity.getContactCode(), "Contact code should match");
        assertEquals(attendanceEntity, userDetailsEntity.getAttendanceEntity(), "Attendance entity should match");
    }

    @Test
    void testUserDetailsEntityToString() {
        // Create UserDetailsEntity object
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity.setUserId(1);
        userDetailsEntity.setFirstName("John");
        userDetailsEntity.setLastName("Doe");
        userDetailsEntity.setContactCode("ABC123");

        String expectedString = "UserDetailsEntity [userId=1, firstName=John, lastName=Doe, attendanceEntity=null, contactCode=ABC123]";
        assertEquals(expectedString, userDetailsEntity.toString(), "toString should match the expected string");
    }
}
