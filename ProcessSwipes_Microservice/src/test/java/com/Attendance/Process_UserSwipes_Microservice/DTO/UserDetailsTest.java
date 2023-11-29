package com.Attendance.Process_UserSwipes_Microservice.DTO;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UserDetailsTest {

    @Test
    void userDetailsGetterAndSetterTest() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);
        userDetails.setFirstName("John");
        userDetails.setLastName("Doe");
        userDetails.setContactCode("12345");
        userDetails.setStatus("Active");

        assertThat(userDetails.getUserId()).isEqualTo(1);
        assertThat(userDetails.getFirstName()).isEqualTo("John");
        assertThat(userDetails.getLastName()).isEqualTo("Doe");
        assertThat(userDetails.getContactCode()).isEqualTo("12345");
        assertThat(userDetails.getStatus()).isEqualTo("Active");
    }
}
