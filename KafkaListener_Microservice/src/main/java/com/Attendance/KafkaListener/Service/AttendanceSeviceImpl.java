package com.Attendance.KafkaListener.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Attendance.KafkaListener.DTO.AttendanceStatus;
import com.Attendance.KafkaListener.DaoImpl.ProcessAttendance;
import com.Attendance.KafkaListener.ServiceInterface.AttendanceService;

@Service
public class AttendanceSeviceImpl implements AttendanceService{

	Logger LOGGER = LoggerFactory.getLogger(AttendanceSeviceImpl.class);
	@Autowired
	private ProcessAttendance processAttendance;

	public List<AttendanceStatus> fetchAttendanceData() {
		List<AttendanceStatus> attendanceStatus = new ArrayList<AttendanceStatus>();
		try {
			LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
					LocalDate.now().getDayOfMonth());
			List<Object[]> attendanceDetails = processAttendance.fetchAttendanceData();
			for (Object[] attendance : attendanceDetails) {
				AttendanceStatus u = new AttendanceStatus();
				LocalDate attDate = (LocalDate) attendance[1];
				u.setFirstName((String) attendance[4]);
				u.setContactCode((String) attendance[6]);
				u.setDate(attDate);
				if (attDate != null && attDate.equals(date)) {
					u.setStatus((String) attendance[3]);
				} else {
					u.setStatus("Absent");
				}
				attendanceStatus.add(u);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " " + e);
		}
		return attendanceStatus;
	}
}