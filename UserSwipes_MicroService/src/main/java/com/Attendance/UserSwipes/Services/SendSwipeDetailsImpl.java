package com.Attendance.UserSwipes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Attendance.UserSwipes.DTO.UserSwipe;
import com.Attendance.UserSwipes.Interfaces.SendSwipeDetails;
import com.Attendance.UserSwipes.ProcessSwipeRecordsProxy.ProcessSwipeRecordsProxy;

@Service
public class SendSwipeDetailsImpl implements SendSwipeDetails {

	@Autowired
	private ProcessSwipeRecordsProxy processSwipeRecordsProxy; 
	
	@Override
	public String sendSwipeDetails(UserSwipe userSwipe) {
		return processSwipeRecordsProxy.saveUserAndSwipeDetails(userSwipe).toString();
	}

}
