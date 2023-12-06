package com.Attendance.Process_UserSwipes_Microservice.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipe;
import com.Attendance.Process_UserSwipes_Microservice.Dao.ProcessSwipeRecords;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserDetailsEntity;
import com.Attendance.Process_UserSwipes_Microservice.Scheduler.AttendanceCalculationScheduler;

class ProcessSwipeRecordsControllerTest {

    @Mock
    private ProcessSwipeRecords processSwipeRecords;

    @Mock
    private AttendanceCalculationScheduler attendanceCalculationScheduler;

    @InjectMocks
    private ProcessSwipeRecordsController processSwipeRecordsController;

    public ProcessSwipeRecordsControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<UserDetailsEntity> users = Arrays.asList(new UserDetailsEntity(), new UserDetailsEntity());
        when(processSwipeRecords.getAllusers()).thenReturn(users);

        List<UserDetailsEntity> result = processSwipeRecordsController.getAllUsers();

        assertEquals(2, result.size(), "Should return a list of two UserDetailsEntity objects");
    }

    @Test
    void testSaveUserAndSwipeDetails() {
        UserSwipe userSwipe = new UserSwipe();
        when(processSwipeRecords.saveUser(userSwipe)).thenReturn(1);

        Integer result = processSwipeRecordsController.saveUserAndSwipeDetails(userSwipe);

        assertEquals(1, result, "Should return 1 after saving the user swipe details");
    }

    @Test
    void testGenerateAttendance() {
        doNothing().when(attendanceCalculationScheduler).attendanceCaclulationScheduler();

        String result = processSwipeRecordsController.generateAttendance();

        assertEquals("Attendance data have been successfully published.", result, "Should return 1 after manually generating attendance");
        verify(attendanceCalculationScheduler, times(1)).attendanceCaclulationScheduler();
    }
}
