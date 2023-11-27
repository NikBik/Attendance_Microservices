package com.Attendance.UserSwipes.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Attendance.UserSwipes.DTO.UserSwipe;
import com.Attendance.UserSwipes.ProcessSwipeRecordsProxy.ProcessSwipeRecordsProxy;

class SendSwipeDetailsImplTest {

    @Mock
    private ProcessSwipeRecordsProxy processSwipeRecordsProxy;

    @InjectMocks
    private SendSwipeDetailsImpl sendSwipeDetails = new SendSwipeDetailsImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendSwipeDetails() {
        UserSwipe userSwipe = new UserSwipe();
        userSwipe.setContactCode("John");
        userSwipe.setFirstName("Niket");
        userSwipe.setIsSwipeIn(true);
        Integer op=1;

        when(processSwipeRecordsProxy.saveUserAndSwipeDetails(any(UserSwipe.class))).thenReturn(op);

        String result = sendSwipeDetails.sendSwipeDetails(userSwipe);

        verify(processSwipeRecordsProxy, times(1)).saveUserAndSwipeDetails(userSwipe);
        assertEquals(op.toString(), result);
    }
}
