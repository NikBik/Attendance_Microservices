package com.Attendance.KafkaListener.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.Attendance.KafkaListener.DTO.AttendanceStatus;
import com.Attendance.KafkaListener.DTO.UserDetails;
import com.Attendance.KafkaListener.DaoImpl.ProcessAttendance;

public class AttendanceSeviceImpl {

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
				if (attDate != null && attDate.isAfter(date)) {
					u.setDate(attDate);
					u.setStatus((String) attendance[3]);
				} else {
					// u.userId ,max(a.date)
					// ,a.timeStamp,a.status,u.firstName,u.lastName,u.contactCode
					// if attendance date ie. 1 is not of same day mark as Not available
					u.setDate(null);
					u.setStatus("NA");
				}
				attendanceStatus.add(u);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " " + e);
		}
		return attendanceStatus;
	}
}