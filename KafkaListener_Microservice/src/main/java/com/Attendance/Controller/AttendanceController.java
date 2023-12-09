package com.Attendance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Attendance.KafkaListener.DTO.AttendanceStatus;
import com.Attendance.KafkaListener.ServiceInterface.AttendanceService;

@RestController
@RequestMapping("/attendanceService")
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceSevice;

	@GetMapping(value="/getAttendanceData")
	public List<AttendanceStatus>  getAttendanceData(){
		return attendanceSevice.fetchAttendanceData();
	}
}
