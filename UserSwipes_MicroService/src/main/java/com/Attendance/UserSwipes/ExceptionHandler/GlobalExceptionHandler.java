package com.Attendance.UserSwipes.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Attendance.UserSwipes.DTO.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ServicesDownException.class)
	public ResponseEntity<ErrorMessage> servicesDownError(Exception ex) {
		ErrorMessage message = new ErrorMessage(503, new Date(), ex.getMessage(),
				"Services are unavailable at the moment. Please try again in sometime.");
		return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);

	}

	@ExceptionHandler(RateLimiterException.class)
	public ResponseEntity<ErrorMessage> rateLimiterError(Exception ex) {
		ErrorMessage message = new ErrorMessage(500, new Date(), ex.getMessage(),
				"Requests to resource have been rate limited. Please wait for sometime.");
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleErrors(Exception ex) {
		ErrorMessage message = new ErrorMessage(500, new Date(), ex.getMessage(),
				"Error occured while processing Request");
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

	}

	private Map<String, List<String>> getErrorsMap(List<String> errors) {
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("errors", errors);
		return errorResponse;
	}
}
