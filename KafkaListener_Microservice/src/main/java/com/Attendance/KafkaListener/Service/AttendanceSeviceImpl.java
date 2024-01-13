package com.Attendance.KafkaListener.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Attendance.KafkaListener.EmailAuthenticator;
import com.Attendance.KafkaListener.DTO.AttendanceStatus;
import com.Attendance.KafkaListener.DaoImpl.ProcessAttendance;
import com.Attendance.KafkaListener.ServiceInterface.AttendanceService;

@Service
public class AttendanceSeviceImpl implements AttendanceService{

	Logger LOGGER = LoggerFactory.getLogger(AttendanceSeviceImpl.class);
	
	private final String smtpHost = "smtp.gmail.com";
	private final String smtpPort = "587";
	private final String emailUsername = "suspicious872@gmail.com";
	private final String recipientEmail = "niketsourabh767@gmail.com";

	@Autowired
	private ProcessAttendance processAttendance;
	
	@Autowired
	private EmailAuthenticator emailAuthenticator;
	
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
				u.setHours((Double) attendance[7]);
				u.setDate(attDate);
				if (attDate != null && attDate.equals(date)) {
					u.setStatus((String) attendance[3]);
				} else {
					u.setStatus("Absent");
				}
				attendanceStatus.add(u);
			}
			sendEmail(attendanceStatus);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " " + e);
		}
		return attendanceStatus;
	}
	
	private void sendEmail(List<AttendanceStatus> attendanceStatus) {
				Properties properties = new Properties();
				properties.put("mail.smtp.host", smtpHost);
				properties.put("mail.smtp.port", smtpPort);
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
		
			try {
					Session session = Session.getDefaultInstance(properties, emailAuthenticator);
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(emailUsername));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
					message.setSubject("Attendance Status Email");
		
					StringBuilder emailBody = new StringBuilder();
					emailBody.append("Attendance data for:").append(attendanceStatus.get(0).getDate()).append("\n\n");
		
					for (AttendanceStatus status : attendanceStatus) {
						emailBody.append("Name: ").append(status.getFirstName()).append("; Contact_Code: ")
								.append(status.getContactCode()).append("\n");
						emailBody.append("Status: ").append(status.getStatus()).append("\n");
						emailBody.append("Hours: ").append(status.getHours())
								.append("\n\n");
			}
		
					message.setText(emailBody.toString());
							Transport.send(message);
		
				LOGGER.info("Email sent successfully!");
					} catch (MessagingException e) {
				LOGGER.error("Error sending email: " + e.getMessage());
			}
		}
}