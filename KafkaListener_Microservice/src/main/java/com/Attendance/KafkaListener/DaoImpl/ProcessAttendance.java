package com.Attendance.KafkaListener.DaoImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Attendance.KafkaListener.DTO.UserDetails;
import com.Attendance.KafkaListener.Entity.AttendanceEntity;
import com.Attendance.KafkaListener.Entity.UserDetailsEntity;

@Repository
public class ProcessAttendance {

	private final String actionedBy = "SCHEDULER";
	@Autowired
	private SessionFactory sessionFactory;

	public Integer processAttendance(UserDetails user) {
		Session session = sessionFactory.getCurrentSession();
		AttendanceEntity attenEntity = new AttendanceEntity();
		attenEntity.setDate(LocalDate.now());
		UserDetailsEntity userEntity = new UserDetailsEntity();
		userEntity.setUserId(user.getUserId());
		attenEntity.setUserId(userEntity);
		attenEntity.setAttStatus(user.getStatus());
		attenEntity.setHours(user.getHours());
		attenEntity.setAuditTimestamp(LocalDateTime.now());
		attenEntity.setActionedBy(actionedBy);
		session.saveOrUpdate(attenEntity);
		return attenEntity.getAttendanceId();
	}

	public List<Object[]> fetchAttendanceData() {
		Session session = sessionFactory.getCurrentSession();
		return session
				.createQuery("select u.userId ,a.date, a.auditTimestamp,a.attStatus,u.firstName,u.lastName,u.contactCode,a.hours from "
						+ " UserDetailsEntity u left join AttendanceEntity a on a.userId=u.userId "
						+ " where (a.userId,a.auditTimestamp) in (select ad.userId,max(ad.auditTimestamp) as maxdate"
						+ " from AttendanceEntity ad group by ad.userId)")
				.list();

	}
}
