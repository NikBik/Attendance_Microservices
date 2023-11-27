package com.Attendance.Process_UserSwipes_Microservice.Dao;

import java.util.List;

import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipe;
import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipeInOutTimings;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserDetailsEntity;

public interface ProcessSwipeRecords {
	
	public List<UserDetailsEntity> getAllusers();
	public Integer saveUser(UserSwipe user);
	public List<UserSwipeInOutTimings> generateAttendance();

}
