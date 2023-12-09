package com.Attendance.KafkaListener.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Attendance.KafkaListener.DTO.AttendanceStatus;
import com.Attendance.KafkaListener.DaoImpl.ProcessAttendance;

public class AttendanceServiceImplTest {

	@Mock
	private ProcessAttendance processAttendance;

	@InjectMocks
	private AttendanceSeviceImpl attendance;
	
	public AttendanceServiceImplTest() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void TestFetchAttendanceData() {
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] array= {1,LocalDate.now(),LocalDateTime.now(),"Present","Test","Niket","Test"};
		Object[] array1= {2,null,LocalDateTime.now(),"NA","Test","Sourabh","TestSrbh"};
		list.add(array1);
		list.add(array);
		when(processAttendance.fetchAttendanceData()).thenReturn(list);
		List<AttendanceStatus> attSt= attendance.fetchAttendanceData();
		assertEquals(attSt.size(), 2);
		assertEquals(attSt.get(0).getStatus(),"Absent");
		assertEquals(attSt.get(1).getStatus(),"Present");
	}
	
	@Test
	public void TestFetchAttendanceDataException() {
		List<Object[]> list = new ArrayList<Object[]>();
		Object[] array= {1,"WrongDate",LocalDateTime.now(),"Present","Test","Niket","Test"};
		Object[] array1= {2,null,LocalDateTime.now(),"NA","Test","Sourabh","TestSrbh"};
		list.add(array1);
		list.add(array);
		when(processAttendance.fetchAttendanceData()).thenReturn(list);
		try {
			attendance.fetchAttendanceData();
		}
		catch (Exception e) {
			assertEquals("class java.lang.String cannot be cast to class java.time.LocalDate (java.lang.String and java.time.LocalDate are in module java.base of loader 'bootstrap')", e.getMessage());
		}
	}
}


