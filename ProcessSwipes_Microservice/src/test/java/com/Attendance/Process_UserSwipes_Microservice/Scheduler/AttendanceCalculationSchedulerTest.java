package com.Attendance.Process_UserSwipes_Microservice.Scheduler;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;

import com.Attendance.Process_UserSwipes_Microservice.DTO.UserDetails;
import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipeInOutTimings;
import com.Attendance.Process_UserSwipes_Microservice.Dao.ProcessSwipeRecords;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserDetailsEntity;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserSwipesEntity;

class AttendanceCalculationSchedulerTest {

    @Mock
    private ProcessSwipeRecords processSwipeRecords;

    @Mock
    private KafkaTemplate<String, UserDetails> kafkaTemplate;

    @InjectMocks
    private AttendanceCalculationScheduler scheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAttendanceCalculationScheduler() {
        List<UserDetailsEntity> users = new ArrayList<>();
        UserDetailsEntity user=new UserDetailsEntity();
        user.setUserId(12);
        List<UserSwipesEntity> swipes= new ArrayList<UserSwipesEntity>();
        UserSwipesEntity sw= new UserSwipesEntity();
        sw.setIsSwipeIn(true);
        sw.setSwipeRecordId(1);
        sw.setTimeStamp(LocalDateTime.now());
        sw.setUserId(user);
        user.setUserSwipes(null);
        
        swipes.add(sw);
        users.add(user);
        
        UserDetailsEntity user1= new UserDetailsEntity();
        BeanUtils.copyProperties(user, user1);
        user1.setUserId(23);
        users.add(user1);
        
        UserDetailsEntity user2= new UserDetailsEntity();
        BeanUtils.copyProperties(user, user2);
        user2.setUserId(21);
        users.add(user2);
        
        UserDetailsEntity user3= new UserDetailsEntity();
        BeanUtils.copyProperties(user, user3);
        user3.setUserId(211);
        users.add(user3);

        List<UserSwipeInOutTimings> attendanceDetails = new ArrayList<>();
        UserSwipeInOutTimings tim= new UserSwipeInOutTimings();
        tim.setUserId(12);
        tim.setSwipeIn(LocalDateTime.now());
        tim.setSwipeOut(LocalDateTime.now().plusHours(5));
        attendanceDetails.add(tim);
        
        UserSwipeInOutTimings tim1= new UserSwipeInOutTimings();
        BeanUtils.copyProperties(tim, tim1);
        tim1.setSwipeOut(tim1.getSwipeOut().minusHours(3));
        tim1.setUserId(21);
        attendanceDetails.add(tim1);
        
        UserSwipeInOutTimings tim2= new UserSwipeInOutTimings();
        BeanUtils.copyProperties(tim, tim2);
        tim2.setSwipeOut(tim2.getSwipeOut().plusHours(5));
        tim2.setUserId(211);
        attendanceDetails.add(tim2);
        
        when(processSwipeRecords.getAllusers()).thenReturn(users);
        when(processSwipeRecords.generateAttendance()).thenReturn(attendanceDetails);
        
        scheduler.attendanceCaclulationScheduler();

        verify(processSwipeRecords, atLeastOnce()).getAllusers();
        verify(processSwipeRecords, atLeastOnce()).generateAttendance();
    }
}
