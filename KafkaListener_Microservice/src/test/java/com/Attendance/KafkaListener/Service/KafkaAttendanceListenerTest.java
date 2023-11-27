package com.Attendance.KafkaListener.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import com.Attendance.KafkaListener.DTO.UserDetails;
import com.Attendance.KafkaListener.DaoImpl.ProcessAttendance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class KafkaAttendanceListenerTest {

	@Mock
	private ProcessAttendance processAttendance;

	@Mock
	private ObjectMapper objectMapper;

	@Mock
	private Logger logger; // Mock the Logger

	@Mock
	private MessageListenerContainer messageListenerContainer;

	@InjectMocks
	private KafkaAttendanceListener kafkaAttendanceListener;
	@Mock
	private Logger loggerMock;
	
	@Test
	public void testGetAttendance() throws Exception {
		String jsonPayload = "{\"userId\": 123, \"firstName\": \"John\", \"lastName\": \"Doe\", \"contactCode\": \"ABC\", \"status\": \"Present\"}";

		// Mock the ObjectMapper behavior
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(123);
		userDetails.setFirstName("John");
		userDetails.setLastName("Doe");
		userDetails.setContactCode("ABC");
		userDetails.setStatus("Present");

		// Mock the processAttendance behavior
		when(processAttendance.processAttendance(any(UserDetails.class))).thenReturn(1); // Modify as per your method
																							// return type

		// Call the method
		kafkaAttendanceListener.getAttendance(jsonPayload, null);

		// Verify that the processAttendance method was called
		verify(processAttendance, times(1)).processAttendance(any(UserDetails.class));
	}
	
}