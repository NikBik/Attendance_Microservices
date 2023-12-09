package com.Attendance.KafkaListener.DaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(1);
		userDetails.setStatus("Present");

		AttendanceEntity attendanceEntity = new AttendanceEntity();
		attendanceEntity.setDate(LocalDate.now());
		attendanceEntity.setUserId(new UserDetailsEntity());
		attendanceEntity.setAttStatus(userDetails.getStatus());
		attendanceEntity.setAuditTimestamp(LocalDateTime.now());
		attendanceEntity.setActionedBy("SCHEDULER");

		Integer result = processAttendance.processAttendance(userDetails);

		verify(session, times(1)).saveOrUpdate(any(AttendanceEntity.class));
	}
	
	@Test
	public void testfetchAttendanceData() {
		List<Object[]> list = new ArrayList<>();
		Object[] array= {"Test",1,2};
		list.add(array);
		when(session.createQuery(anyString())).thenReturn(mock(org.hibernate.query.Query.class));
		when(session.createQuery(anyString()).list()).thenReturn(list);
		List<Object[]> result=processAttendance.fetchAttendanceData();
		assertEquals(result.size(), 1);
	} 
}
