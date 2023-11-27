package com.Attendance.UserSwipes.ProcessSwipeRecordsProxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Attendance.UserSwipes.DTO.UserSwipe;

@FeignClient(name="process-user-swipes")
public interface ProcessSwipeRecordsProxy {
	
	@PostMapping(value="/processSwipes/saveSwipeRecords")
	Integer  saveUserAndSwipeDetails(@RequestBody UserSwipe user);
}
