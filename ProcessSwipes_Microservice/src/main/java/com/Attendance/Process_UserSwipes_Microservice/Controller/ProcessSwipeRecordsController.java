package com.Attendance.Process_UserSwipes_Microservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipe;
import com.Attendance.Process_UserSwipes_Microservice.Dao.ProcessSwipeRecords;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserDetailsEntity;
import com.Attendance.Process_UserSwipes_Microservice.Scheduler.AttendanceCalculationScheduler;

@RestController
@RequestMapping("/processSwipes")
public class ProcessSwipeRecordsController {
	
	@Autowired
	private ProcessSwipeRecords processSwipeRecords;
	
	@Autowired
	private AttendanceCalculationScheduler attendanceCalculationScheduler;
	
	@GetMapping(value="/getAllUsers")
	public List<UserDetailsEntity>  getAllUsers(){
		return processSwipeRecords.getAllusers();
	}
	
	@PostMapping(value="/saveSwipeRecords")
	public Integer  saveUserAndSwipeDetails(@RequestBody UserSwipe user){
		return processSwipeRecords.saveUser(user);
	}
	
	@GetMapping(value="/manualGenerateAttendance")
	public Integer generateAttendance(){
		attendanceCalculationScheduler.attendanceCaclulationScheduler();
		return 1;
	}
}
