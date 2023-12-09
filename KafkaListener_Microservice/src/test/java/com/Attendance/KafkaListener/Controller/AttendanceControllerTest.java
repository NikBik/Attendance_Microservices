package com.Attendance.KafkaListener.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Attendance.Controller.AttendanceController;
import com.Attendance.KafkaListener.DTO.AttendanceStatus;
import com.Attendance.KafkaListener.ServiceInterface.AttendanceService;

public class AttendanceControllerTest {

	@Mock
	private AttendanceService attendanceSevice;;

	@InjectMocks
	private AttendanceController attendanceController;

	public AttendanceControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getAttendanceDataTest() {
		List<AttendanceStatus> list = new ArrayList<AttendanceStatus>();
		AttendanceStatus a = new AttendanceStatus();
		a.setContactCode("Test");
		a.setDate(LocalDate.now());
		a.setFirstName("Test");
		a.setStatus("Present");
		list.add(a);

		when(attendanceSevice.fetchAttendanceData()).thenReturn(list);
		List<AttendanceStatus> attendanceStatus = attendanceController.getAttendanceData();
		assertEquals(attendanceStatus.size(), 1);
	}

}
