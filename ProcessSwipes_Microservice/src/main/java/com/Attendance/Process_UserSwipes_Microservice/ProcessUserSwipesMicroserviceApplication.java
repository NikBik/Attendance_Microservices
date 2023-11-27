package com.Attendance.Process_UserSwipes_Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@ComponentScan({"com.Attendance*"})
@EnableScheduling
public class ProcessUserSwipesMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProcessUserSwipesMicroserviceApplication.class, args);
	}
}
