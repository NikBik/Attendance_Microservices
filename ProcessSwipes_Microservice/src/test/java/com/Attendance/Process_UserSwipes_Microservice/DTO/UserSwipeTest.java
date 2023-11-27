package com.Attendance.Process_UserSwipes_Microservice.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

public class UserSwipeTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    void userSwipeValidationTest() {
        UserSwipe userSwipe = new UserSwipe();
        userSwipe.setFirstName("J"); 

        LocalDateTime time = LocalDateTime.now();
        userSwipe.setTime(time);

        Set<javax.validation.ConstraintViolation<UserSwipe>> violations = validator.validate(userSwipe);

        assertEquals(4, violations.size()); 
    }

    @Test
    void userSwipeValidationWithValidDataTest() {
        UserSwipe userSwipe = new UserSwipe();
        userSwipe.setId(Integer.valueOf(1));
        userSwipe.setFirstName("John");
        userSwipe.setLastName("Doe");
        userSwipe.setContactCode("12345");
        userSwipe.setIsSwipeIn(true);
        LocalDateTime time = LocalDateTime.now();
        userSwipe.setTime(time);
        
        String t = userSwipe.getFirstName();
        String code = userSwipe.getContactCode();
        Integer id = userSwipe.getId();
        Boolean swipeIn = userSwipe.getIsSwipeIn();
        String name = userSwipe.getLastName();
        time=userSwipe.getTime();
        String s = "UserSwipe [id=" + id + ", firstName=" + t + ", lastName=" + name + ", isSwipeIn="
				+ swipeIn + ", time=" + time + "]";
        assertEquals(s, userSwipe.toString());
        Set<javax.validation.ConstraintViolation<UserSwipe>> violations = validator.validate(userSwipe);

        assertEquals(0, violations.size()); // Expecting no violations for valid data
    }
}
