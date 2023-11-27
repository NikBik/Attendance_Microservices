package com.Attendance.Process_UserSwipes_Microservice.DaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipe;
import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipeInOutTimings;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserDetailsEntity;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserSwipesEntity;

class ProcessSwipeRecordsImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private ProcessSwipeRecordsImpl processSwipeRecords;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }
    
    @Test
    void testGenerateAttendance() {
        List<UserSwipeInOutTimings> expectedAttendance = new ArrayList<>(); // Add sample UserSwipeInOutTimings objects

        // Set up the behavior for session.createQuery to return a list of objects
        when(session.createQuery(anyString())).thenReturn(mock(org.hibernate.query.Query.class));
        when(session.createQuery(anyString()).list()).thenReturn(expectedAttendance);

        List<UserSwipeInOutTimings> result = processSwipeRecords.generateAttendance();

        assertEquals(expectedAttendance, result, "Should return a list of UserSwipeInOutTimings objects");
    }
}
