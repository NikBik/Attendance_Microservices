package com.Attendance.Process_UserSwipes_Microservice.Scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.Attendance.Process_UserSwipes_Microservice.DTO.UserDetails;
import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipeInOutTimings;
import com.Attendance.Process_UserSwipes_Microservice.Dao.ProcessSwipeRecords;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserDetailsEntity;

@Component
public class AttendanceCalculationScheduler {

	@Autowired
	private ProcessSwipeRecords processSwipeRecords;
	
	@Value("${topic.name}")
	private String topicName;
	
	@Autowired
	private KafkaTemplate<String, UserDetails> kafkaTemplate;

	@Scheduled(cron="0 0 22 * * *")
	public void attendanceCaclulationScheduler() {
		List<UserSwipeInOutTimings> attendanceDetails = processSwipeRecords.generateAttendance();
		List<UserDetailsEntity> users = processSwipeRecords.getAllusers();
		for (int i = 0; i < users.size(); i++) {
			UserDetailsEntity user = users.get(i);
			UserDetails userDet = new UserDetails();
			BeanUtils.copyProperties(user, userDet);
			UserSwipeInOutTimings userSwipeTimings = null;
			for (UserSwipeInOutTimings att : attendanceDetails) {
				if (userDet.getUserId().equals(att.getUserId())) {
					userSwipeTimings = att;
					break;
				}
			}
			if (null == userSwipeTimings)
				userDet.setStatus("Absent");
			else {
				LocalDateTime from = userSwipeTimings.getSwipeIn();
				LocalDateTime to = userSwipeTimings.getSwipeOut();
				Duration d = Duration.between(from, to);
				Long minutes = d.toMinutes();
				if (minutes < 240)
					userDet.setStatus("Absent");
				else if (minutes >= 240 && minutes < 480)
					userDet.setStatus("Half_Day");
				else
					userDet.setStatus("Present");
			}
			this.kafkaTemplate.send(topicName,userDet);
		}
		
	}
}
