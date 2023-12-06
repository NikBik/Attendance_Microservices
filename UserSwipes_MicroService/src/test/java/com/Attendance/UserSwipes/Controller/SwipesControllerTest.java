package com.Attendance.UserSwipes.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.Attendance.UserSwipes.ExceptionHandler.ServicesDownException;
import com.Attendance.UserSwipes.Interfaces.SendSwipeDetails;

public class SwipesControllerTest {
	@Mock
	private SendSwipeDetails sendSwipeDetails;

	@InjectMocks
	private SwipesController swipesController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testRecordSwipesInOut() {
		UserSwipe userSwipe = new UserSwipe();
		userSwipe.setContactCode("Test");
		userSwipe.setFirstName("Test");
		userSwipe.setIsSwipeIn(true);
		userSwipe.setLastName("Test");

		String expectedResponse = "Your swipe was successfully processed. Please keep ID for reference:1";

		when(sendSwipeDetails.sendSwipeDetails(any(UserSwipe.class))).thenReturn("1");

		String response = swipesController.reordSwipesInOut(userSwipe);

		assertEquals(expectedResponse, response);
		verify(sendSwipeDetails, times(1)).sendSwipeDetails(any(UserSwipe.class));
	}

	@Test
	void testPingServer() {

		String response = swipesController.pingServer();
		assertNotEquals("", response);
	}

	@Test
    void testFallbackMethod() {
		UserSwipe u = new UserSwipe();
        when(sendSwipeDetails.sendSwipeDetails(u)).thenThrow(new ServicesDownException("Simulated error"));

        assertThrows(ServicesDownException.class, () -> swipesController.reordSwipesInOut(u));
    }
}
