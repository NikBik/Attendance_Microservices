package com.Attendance.KafkaListener.ServiceInterface;

import java.util.List;

import com.Attendance.KafkaListener.DTO.AttendanceStatus;

public interface AttendanceService {
	public List<AttendanceStatus> fetchAttendanceData();
}
