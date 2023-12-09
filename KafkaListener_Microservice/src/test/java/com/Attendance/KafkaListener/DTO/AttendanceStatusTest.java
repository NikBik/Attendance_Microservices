package com.Attendance.KafkaListener.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AttendanceStatusTest {
    @Test
    void testAttendanceStatusGettersAndSetters() {
        AttendanceStatus attendanceStatus = new AttendanceStatus();
        LocalDate date= LocalDate.now();
        attendanceStatus.setContactCode("Test");
        attendanceStatus.setFirstName("Niket");
        attendanceStatus.setDate(date);
        attendanceStatus.setStatus("Absent");
        

        assertEquals(date, attendanceStatus.getDate(), "Date should match");
        assertEquals("Niket", attendanceStatus.getFirstName(), "First name should match");
        assertEquals("Absent", attendanceStatus.getStatus(),"Status should match");
        assertEquals("Test", attendanceStatus.getContactCode(), "Contact code should match");

    }

}
