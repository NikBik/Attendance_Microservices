package com.Attendance.KafkaListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.Attendance*"})
public class KafkaListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaListenerApplication.class, args);
	}

}
