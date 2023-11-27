package com.Attendance.Process_UserSwipes_Microservice.Controller;

import com.Attendance.Process_UserSwipes_Microservice.Controller.ProcessSwipeRecordsController;
import com.Attendance.Process_UserSwipes_Microservice.Dao.ProcessSwipeRecords;
import com.Attendance.Process_UserSwipes_Microservice.Entity.UserDetailsEntity;
import com.Attendance.Process_UserSwipes_Microservice.DTO.UserSwipe;
import com.Attendance.Process_UserSwipes_Microservice.Scheduler.AttendanceCalculationScheduler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

        Integer result = processSwipeRecordsController.generateAttendance();

        assertEquals(1, result, "Should return 1 after manually generating attendance");
        verify(attendanceCalculationScheduler, times(1)).attendanceCaclulationScheduler();
    }
}
