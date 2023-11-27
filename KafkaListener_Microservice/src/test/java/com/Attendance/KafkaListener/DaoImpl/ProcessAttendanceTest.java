package com.Attendance.KafkaListener.DaoImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Attendance.KafkaListener.DTO.UserDetails;
import com.Attendance.KafkaListener.Entity.AttendanceEntity;
import com.Attendance.KafkaListener.Entity.UserDetailsEntity;

public class ProcessAttendanceTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private ProcessAttendance processAttendance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    void testProcessAttendance() {
        // Mock UserDetails object
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);
        userDetails.setStatus("Present");

        // Mock AttendanceEntity
        AttendanceEntity attendanceEntity = new AttendanceEntity();
        attendanceEntity.setDate(LocalDate.now());
        attendanceEntity.setUserId(new UserDetailsEntity());
        attendanceEntity.setAttStatus(userDetails.getStatus());
        attendanceEntity.setAuditTimestamp(LocalDateTime.now());
        attendanceEntity.setActionedBy("SCHEDULER");

        Integer result = processAttendance.processAttendance(userDetails);
        
        verify(session, times(1)).saveOrUpdate(any(AttendanceEntity.class));
    }
}
