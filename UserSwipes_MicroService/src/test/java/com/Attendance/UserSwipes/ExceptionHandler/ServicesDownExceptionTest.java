package com.Attendance.UserSwipes.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ServicesDownExceptionTest {

	@Test
    void testServicesDownException() {
        String errorMessage = "Services down error message";

        ServicesDownException servicesDownException = assertThrows(ServicesDownException.class, () -> {
            throw new ServicesDownException(errorMessage);
        });

        assertEquals(errorMessage, servicesDownException.getMessage());
    }

}
