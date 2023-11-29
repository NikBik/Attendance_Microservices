package com.Attendance.KafkaListener.Service;

import static org.mockito.ArgumentMatchers.any;
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

import com.Attendance.KafkaListener.DTO.UserDetails;
import com.Attendance.KafkaListener.DaoImpl.ProcessAttendance;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class KafkaAttendanceListenerTest {

	@Mock
	private ProcessAttendance processAttendance;

	@Mock
	private ObjectMapper objectMapper;

	@Mock
	private Logger logger;

	@Mock
	private MessageListenerContainer messageListenerContainer;

	@InjectMocks
	private KafkaAttendanceListener kafkaAttendanceListener;
	@Mock
	private Logger loggerMock;

	@Test
	public void testGetAttendance() throws Exception {
		String jsonPayload = "{\"userId\": 123, \"firstName\": \"John\", \"lastName\": \"Doe\", \"contactCode\": \"ABC\", \"status\": \"Present\"}";

		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(123);
		userDetails.setFirstName("John");
		userDetails.setLastName("Doe");
		userDetails.setContactCode("ABC");
		userDetails.setStatus("Present");

		when(processAttendance.processAttendance(any(UserDetails.class))).thenReturn(1);

		kafkaAttendanceListener.getAttendance(jsonPayload, null);

		verify(processAttendance, times(1)).processAttendance(any(UserDetails.class));
	}

}