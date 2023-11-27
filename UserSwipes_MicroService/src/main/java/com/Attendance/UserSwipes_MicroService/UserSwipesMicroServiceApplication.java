package com.Attendance.UserSwipes_MicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.Attendance.UserSwipes.*"})
@EnableFeignClients(basePackages = {"com.Attendance.UserSwipes.*"})
@EnableEurekaClient
public class UserSwipesMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSwipesMicroServiceApplication.class, args);
	}

}
