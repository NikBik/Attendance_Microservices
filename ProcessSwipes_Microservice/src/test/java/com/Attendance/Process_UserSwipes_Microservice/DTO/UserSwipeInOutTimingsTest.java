package com.Attendance.Process_UserSwipes_Microservice.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class UserSwipeInOutTimingsTest {

    @Test
    void testUserSwipeInOutTimings() {
        UserSwipeInOutTimings swipeTimings = new UserSwipeInOutTimings();

        swipeTimings.setUserId(123);
        LocalDateTime swipeInTime = LocalDateTime.of(2023, 11, 30, 9, 0); 
        LocalDateTime swipeOutTime = LocalDateTime.of(2023, 11, 30, 17, 0); 
        swipeTimings.setSwipeIn(swipeInTime);
        swipeTimings.setSwipeOut(swipeOutTime);


        int userId = swipeTimings.getUserId();
        LocalDateTime retrievedSwipeIn = swipeTimings.getSwipeIn();
        LocalDateTime retrievedSwipeOut = swipeTimings.getSwipeOut();

        assertEquals(123, userId);
        assertEquals(swipeInTime, retrievedSwipeIn);
        assertEquals(swipeOutTime, retrievedSwipeOut);
    }
}
