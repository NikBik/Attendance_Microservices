package com.Attendance.KafkaListener.DaoImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Attendance.KafkaListener.DTO.UserDetails;
import com.Attendance.KafkaListener.Entity.AttendanceEntity;
import com.Attendance.KafkaListener.Entity.UserDetailsEntity;

@Repository
public class ProcessAttendance {
	
	private final String actionedBy="SCHEDULER";
	@Autowired
	private SessionFactory sessionFactory;
	
	public Integer processAttendance(UserDetails user) {
		Session session= sessionFactory.getCurrentSession();
		AttendanceEntity attenEntity = new AttendanceEntity();
		attenEntity.setDate(LocalDate.now());
		UserDetailsEntity userEntity= new UserDetailsEntity();
		userEntity.setUserId(user.getUserId());
		attenEntity.setUserId(userEntity);
		attenEntity.setAttStatus(user.getStatus());
		attenEntity.setAuditTimestamp(LocalDateTime.now());
		attenEntity.setActionedBy(actionedBy);
		session.saveOrUpdate(attenEntity);
		return attenEntity.getAttendanceId();
	} 
}
