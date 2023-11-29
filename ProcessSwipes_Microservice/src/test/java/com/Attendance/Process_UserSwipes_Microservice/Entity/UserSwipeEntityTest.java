package com.Attendance.Process_UserSwipes_Microservice.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class UserSwipeEntityTest {

    @Test
    void testUserSwipesEntity() {
        UserSwipesEntity userSwipe = new UserSwipesEntity();
        
        LocalDateTime timeStamp = LocalDateTime.now();
        UserDetailsEntity userDetails = new UserDetailsEntity();
        userDetails.setUserId(1);
        userSwipe.setUserId(userDetails);
        userSwipe.setSwipeRecordId(1);
        userSwipe.setTimeStamp(timeStamp);
        userSwipe.setIsSwipeIn(true);

        assertEquals(timeStamp, userSwipe.getTimeStamp());
        assertEquals(true, userSwipe.getIsSwipeIn());
        assertNotNull(userSwipe.getUserId());
        assertEquals(1, userSwipe.getUserId().getUserId());
    }
}
