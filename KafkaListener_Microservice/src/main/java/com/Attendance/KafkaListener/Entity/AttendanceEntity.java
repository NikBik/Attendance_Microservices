package com.Attendance.KafkaListener.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "ATTENDANCE_DETAILS")
@Entity
public class AttendanceEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private UserDetailsEntity userId;
	private Integer attendanceId;
	private String attStatus;
	private LocalDate date;
	private LocalDateTime auditTimestamp;
	private String actionedBy;
	private Double hours;
	
	@Column(name="AUDIT_TIMESTAMP")
	public LocalDateTime getAuditTimestamp() {
		return auditTimestamp;
	}
	public void setAuditTimestamp(LocalDateTime auditTimestamp) {
		this.auditTimestamp = auditTimestamp;
	}
	@Column(name="ACTIONED_BY")
	public String getActionedBy() {
		return actionedBy;
	}
	public void setActionedBy(String actionedBy) {
		this.actionedBy = actionedBy;
	}
	
	@OneToOne(targetEntity = UserDetailsEntity.class,fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID")
	public UserDetailsEntity getUserId() {
		return userId;
	}
	public void setUserId(UserDetailsEntity userId) {
		this.userId = userId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ATTENDANCE_ID")
	public Integer getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}
	@Column(name="STATUS")
	public String getAttStatus() {
		return attStatus;
	}
	public void setAttStatus(String attStatus) {
		this.attStatus = attStatus;
	}
	
	@Column(name="RECORD_DATE")
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "AttendanceEntity [userId=" + userId + ", attendanceId=" + attendanceId + ", attStatus=" + attStatus
				+ ", date=" + date + ", hours="+hours+"]";
	}
	public Double getHours() {
		return hours;
	}
	public void setHours(Double hours) {
		this.hours = hours;
	}
	
	
}
