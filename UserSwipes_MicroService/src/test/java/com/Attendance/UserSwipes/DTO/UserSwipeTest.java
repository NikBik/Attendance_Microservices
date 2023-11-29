package com.Attendance.UserSwipes.DTO;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserSwipeTest {

    @Test
    void testUserSwipeValidation() {
        UserSwipe userSwipe = new UserSwipe();
        userSwipe.setFirstName("John");
        userSwipe.setLastName("Doe");
        userSwipe.setContactCode("123");
        userSwipe.setIsSwipeIn(true);
        userSwipe.setTime(LocalDateTime.now());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserSwipe>> violations = validator.validate(userSwipe);

        assertTrue(violations.isEmpty(), "Validation should pass for a valid UserSwipe object");
    }

    @Test
    void testUserSwipeValidationFailure() {
        UserSwipe userSwipe = new UserSwipe();
        userSwipe.setFirstName("J"); 
        userSwipe.setLastName("Doe");
        userSwipe.setContactCode("123");
        userSwipe.setIsSwipeIn(true);
        userSwipe.setTime(LocalDateTime.now());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserSwipe>> violations = validator.validate(userSwipe);

        assertFalse(violations.isEmpty(), "Validation should fail for an invalid UserSwipe object");
    }

    @Test
    void testUserSwipeToString() {
        UserSwipe userSwipe = new UserSwipe();
        userSwipe.setId(1);
        userSwipe.setFirstName("John");
        userSwipe.setLastName("Doe");
        userSwipe.setContactCode("123");
        userSwipe.setIsSwipeIn(true);
        userSwipe.setTime(LocalDateTime.of(2023, 11, 24, 12, 0));

        String expectedString = "UserSwipe [id=1, firstName=John, lastName=Doe, contactCode=123, isSwipeIn=true, time=2023-11-24T12:00]";
        assertEquals(expectedString, userSwipe.toString(), "String representation should match expected format");
    }
    
    @Test
    void testUserSwipegetUserSwipe() {
        UserSwipe userSwipe = new UserSwipe();
        userSwipe.setId(1);
        userSwipe.setFirstName("John");
        userSwipe.setLastName("Doe");
        userSwipe.setContactCode("123");
        userSwipe.setIsSwipeIn(true);
        userSwipe.setTime(LocalDateTime.of(2023, 11, 24, 12, 0));

        assertEquals(1, userSwipe.getId());
        assertEquals("John", userSwipe.getFirstName());
        assertEquals("Doe", userSwipe.getLastName());
        assertEquals("123", userSwipe.getContactCode());
        assertEquals(true, userSwipe.getIsSwipeIn());
        assertEquals(LocalDateTime.of(2023, 11, 24, 12, 0), userSwipe.getTime());
        
    }

}
