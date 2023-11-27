package com.Attendance.Process_UserSwipes_Microservice.DaoImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipe;
import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipeInOutTimings;
import com.Attendance.Process_UserSwipes_Microservice.Dao.ProcessSwipeRecords;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserDetailsEntity;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserSwipesEntity;

@Component
public class ProcessSwipeRecordsImpl implements ProcessSwipeRecords {

	@Autowired
	private SessionFactory sessionFactory;

	public List<UserDetailsEntity> getAllusers() {
		Session session = sessionFactory.getCurrentSession();
		
		List<UserDetailsEntity> userDetailsList = session.createQuery("from UserDetailsEntity order by userId").list();		
		return userDetailsList;
	}

	public Integer saveUser(UserSwipe userSwipeDetails) {

		UserDetailsEntity user = fetchUser(userSwipeDetails);
		if (null == user) {
			user = new UserDetailsEntity();
			user.setContactCode(userSwipeDetails.getContactCode());
			user.setLastName(userSwipeDetails.getLastName());
			user.setFirstName(userSwipeDetails.getFirstName());
		}
		UserSwipesEntity userswipe = new UserSwipesEntity();
		userswipe.setIsSwipeIn(userSwipeDetails.getIsSwipeIn());
		userswipe.setTimeStamp(userSwipeDetails.getTime());
		userswipe.setUserId(user);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userswipe);
		return userswipe.getSwipeRecordId();
	}

	private UserDetailsEntity fetchUser(UserSwipe user) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserDetailsEntity.class);
		criteria.add(Restrictions.eq("contactCode", user.getContactCode()));
		return (UserDetailsEntity) criteria.uniqueResult();
	}

	public List<UserSwipeInOutTimings> generateAttendance() {
		Session session = sessionFactory.getCurrentSession();
		List<UserSwipeInOutTimings> userDetailsList = new ArrayList<UserSwipeInOutTimings>() ;
		List<Object[]> userAttendance = session.createQuery(
				"select min(u.userId) ,min(u.timeStamp) ,max(u.timeStamp) from "
				+ "UserSwipesEntity u where CAST(u.timeStamp as date)=cast(CURDATE() as date) group by "
				+ "u.userId order by u.userId")
				.list();
		for (Object[] arr:userAttendance) {
			UserSwipeInOutTimings userSwipeInOutTimings= new UserSwipeInOutTimings();
			UserDetailsEntity u1= (UserDetailsEntity)arr[0];
			userSwipeInOutTimings.setUserId(u1.getUserId());
			userSwipeInOutTimings.setSwipeIn((LocalDateTime)arr[1]);
			userSwipeInOutTimings.setSwipeOut((LocalDateTime)arr[2]);			
			userDetailsList.add(userSwipeInOutTimings);
		}
		return userDetailsList;
		
	}

}
