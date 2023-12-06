package com.Attendance.Process_UserSwipes_Microservice.DaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipe;
import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipeInOutTimings;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserDetailsEntity;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserSwipesEntity;

class ProcessSwipeRecordsImplTest {

	@Mock
	private SessionFactory sessionFactory;

	@Mock
	private Session session;

	@InjectMocks
	private ProcessSwipeRecordsImpl processSwipeRecords;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
	}

	@Test
	void testGenerateAttendance() {
		List<UserSwipeInOutTimings> expectedAttendance = new ArrayList<>();
		UserSwipeInOutTimings timings = new UserSwipeInOutTimings();
		timings.setUserId(1);
		timings.setSwipeIn(LocalDateTime.now().minusYears(1));
		timings.setSwipeIn(LocalDateTime.now());
		expectedAttendance.add(timings);

		UserSwipeInOutTimings timings1 = new UserSwipeInOutTimings();
		timings1.setUserId(2);
		timings1.setSwipeIn(LocalDateTime.now().minusYears(1));
		timings1.setSwipeIn(LocalDateTime.now());
		expectedAttendance.add(timings1);

		List<Object[]> response = new ArrayList<Object[]>();
		UserDetailsEntity user = new UserDetailsEntity();
		user.setUserId(Integer.valueOf(1));
		response.add(new Object[] { user, LocalDateTime.now().minusYears(1), LocalDateTime.now() });
		UserDetailsEntity user1 = new UserDetailsEntity();
		user1.setUserId(Integer.valueOf(2));
		response.add(new Object[] { user1, LocalDateTime.now().minusYears(1), LocalDateTime.now() });

		when(session.createQuery(anyString())).thenReturn(mock(org.hibernate.query.Query.class));
		when(session.createQuery(anyString()).list()).thenReturn(response);

		List<UserSwipeInOutTimings> result = processSwipeRecords.generateAttendance();

		assertEquals(expectedAttendance.size(), result.size(),
				"Should return UserSwipeInOutTimings list with 2 objects");
	}

	@Test
	void testGetAllUsers() {
		List<UserDetailsEntity> userList= new ArrayList<>();
		UserDetailsEntity userDetails = new UserDetailsEntity();
		userDetails.setUserId(1);
		userDetails.setContactCode("Niket");
		
		when(session.createQuery(anyString())).thenReturn(mock(org.hibernate.query.Query.class));
		when(session.createQuery(anyString()).list()).thenReturn(userList);
		
		List<UserDetailsEntity> user1=processSwipeRecords.getAllusers();
		
		assertEquals(userList.size(), user1.size(),
				"Should return UserSwipeInOutTimings list with 1 object");
	}
	
	@Test
	void testSaveUser() {
		doNothing().when(session).saveOrUpdate(UserSwipesEntity.class);
		UserSwipesEntity userSwipe = new UserSwipesEntity();
		userSwipe.setSwipeRecordId(1);
		userSwipe.setIsSwipeIn(true);
		userSwipe.setIsSwipeIn(true);
		userSwipe.setTimeStamp(LocalDateTime.now());
		
		UserSwipe userSwipe1 = new UserSwipe();
		userSwipe1.setId(2);
		userSwipe.setIsSwipeIn(true);
		userSwipe1.setTime(LocalDateTime.now());
		
		Integer response = processSwipeRecords.saveUser(userSwipe1);
		assertEquals(1,response, "Save user should return an integer");
	}
}
