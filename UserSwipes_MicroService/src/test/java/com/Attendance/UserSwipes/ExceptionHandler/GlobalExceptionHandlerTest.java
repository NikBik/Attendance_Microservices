package com.Attendance.UserSwipes.ExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.Attendance.UserSwipes.DTO.ErrorMessage;

class GlobalExceptionHandlerTest {

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleValidationErrors() {
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("objectName", "fieldName", "error message"));
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleValidationErrors(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testServicesDownError() {
        String errorMessage = "Services are unavailable at the moment. Please try again in sometime.";
        Exception ex = new Exception(errorMessage);
        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.servicesDownError(ex);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody().getMessage());
    }

    @Test
    void testRateLimiterError() {
        String errorMessage = "Rate limiter message";
        Exception ex = new Exception(errorMessage);
        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.rateLimiterError(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody().getMessage());
    }

    @Test
    void testHandleErrors() {
        String errorMessage = "Some error message";
        Exception ex = new Exception(errorMessage);
        ResponseEntity<ErrorMessage> responseEntity = globalExceptionHandler.handleErrors(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody().getMessage());
    }
}
