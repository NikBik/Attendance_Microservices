package com.Attendance.UserSwipes.DTO;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorMessageTest {

    @Test
    void testErrorMessage() {
        int statusCode = 404;
        Date timestamp = new Date();
        String message = "Not Found";
        String description = "The requested resource was not found.";

        ErrorMessage errorMessage = new ErrorMessage(statusCode, timestamp, message, description);

        assertEquals(statusCode, errorMessage.getStatusCode(), "Status code should match");
        assertEquals(timestamp, errorMessage.getTimestamp(), "Timestamp should match");
        assertEquals(message, errorMessage.getMessage(), "Message should match");
        assertEquals(description, errorMessage.getDescription(), "Description should match");
    }
    
}
