package com.Attendance.UserSwipes.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RateLimiterExceptionTest {

	@Test
    void testRateLimiterException() {
        String errorMessage = "Rate limiter error message";

        RateLimiterException rateLimiterException = assertThrows(RateLimiterException.class, () -> {
            throw new RateLimiterException(errorMessage);
        });

        assertEquals(errorMessage, rateLimiterException.getMessage());
    }

}
