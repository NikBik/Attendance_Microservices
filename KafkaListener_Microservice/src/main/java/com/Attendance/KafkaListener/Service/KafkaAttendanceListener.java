package com.Attendance.KafkaListener.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Attendance.KafkaListener.DTO.UserDetails;
import com.Attendance.KafkaListener.DaoImpl.ProcessAttendance;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaAttendanceListener {
	
	Logger LOGGER = LoggerFactory.getLogger(KafkaAttendanceListener.class);
	@Autowired
	private ProcessAttendance processAttendance;
	
	@KafkaListener(topics="attendance-topic",groupId="group-id",containerFactory = "userListener")
			//autoStartup = "true",concurrency = "2",errorHandler = "beanName",filter = "filterBeanName",topicPattern = "",topicPartitions = {})
	@Transactional
	public void getAttendance(@Payload String user ,@Headers MessageHeaders headers) {
		LOGGER.info(user);
		try {
			UserDetails user1= (UserDetails) new ObjectMapper().readValue(user,UserDetails.class);
			LOGGER.info( "Attendance Processed "+processAttendance.processAttendance(user1)+" ,userId"+user1.getUserId());
		} catch (Exception e) {
			System.out.println("Exception while mapping JSON I/P "+e);
		}  
	}
}
