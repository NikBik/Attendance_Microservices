package com.Attendance.KafkaListener.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AttendanceEntityTest {

    @Test
    void testAttendanceEntityGettersAndSetters() {
        AttendanceEntity attendanceEntity = new AttendanceEntity();
        attendanceEntity.setAttendanceId(1);
        attendanceEntity.setAttStatus("Present");
        attendanceEntity.setDate(LocalDate.now());
        attendanceEntity.setAuditTimestamp(LocalDateTime.now());
        attendanceEntity.setActionedBy("SCHEDULER");

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity.setUserId(123);
        attendanceEntity.setUserId(userDetailsEntity);

        assertEquals(1, attendanceEntity.getAttendanceId(), "Attendance ID should match");
        assertEquals("Present", attendanceEntity.getAttStatus(), "Status should match");
        assertNotNull(attendanceEntity.getDate(), "Date should not be null");
        assertNotNull(attendanceEntity.getAuditTimestamp(), "Audit timestamp should not be null");
        assertEquals("SCHEDULER", attendanceEntity.getActionedBy(), "Actioned by should match");
        assertEquals(userDetailsEntity, attendanceEntity.getUserId(), "User ID should match");
    }

    @Test
    void testAttendanceEntityToString() {
        AttendanceEntity attendanceEntity = new AttendanceEntity();
        attendanceEntity.setAttendanceId(1);
        attendanceEntity.setAttStatus("Present");
        attendanceEntity.setDate(LocalDate.now());
        attendanceEntity.setHours(2d);        

        String expectedString = "AttendanceEntity [userId=null, attendanceId=1, attStatus=Present, date=" + LocalDate.now() +", hours=2.0"+"]";
        assertEquals(expectedString, attendanceEntity.toString(), "toString should match the expected string");
    }
}
