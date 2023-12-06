package com.Attendance.UserSwipes.Controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Attendance.UserSwipes.DTO.UserSwipe;
import com.Attendance.UserSwipes.ExceptionHandler.RateLimiterException;
import com.Attendance.UserSwipes.ExceptionHandler.ServicesDownException;
import com.Attendance.UserSwipes.Interfaces.SendSwipeDetails;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/swipesService")
public class SwipesController {

	Logger LOGGER = LoggerFactory.getLogger(SwipesController.class);

	@Autowired
	private SendSwipeDetails sendSwipeDetails;

	@Value("${test.centralized.config.server}")
	private String property;

	@Value("${test.centralized.config.server.ms.specific}")
	private String propertyMSSpecific;

	@Value("${test.centralized.config.server.coomon}")
	private String propertyCommonFromServer;
	
	private static final String SWIPE_PROCESSED_SUCCESSFULLY= "Your swipe was successfully processed. Please keep ID for reference:";

	@PostMapping("/recordSwipe")
	@Retry(name = "swipeService", fallbackMethod = "fallbackMethod")
	public String reordSwipesInOut(@RequestBody @Validated UserSwipe userSwipe) {
		userSwipe.setTime(LocalDateTime.now());
		LOGGER.info("Object Received: "+userSwipe.toString());
		LOGGER.info("Calling Process Swipes Service.");
		String response = sendSwipeDetails.sendSwipeDetails(userSwipe);
		return SWIPE_PROCESSED_SUCCESSFULLY+response;
	}

	@GetMapping("/ping")
	@RateLimiter(name = "ping", fallbackMethod = "rateLimitingFallbackMethod")
	public String pingServer() {
		return property + ",  MSSPECFICPPROPERTY:" + propertyMSSpecific + ",   COMMONMSPROPERTY:"
				+ propertyCommonFromServer;
	}

	public String fallbackMethod(Exception e) {
		LOGGER.error(e.getMessage() + " " + e.getCause() + " " + e.getStackTrace());
		throw new ServicesDownException(e.getMessage());
	}

	public String rateLimitingFallbackMethod(Exception e) {
		LOGGER.error(e.getMessage() + " " + e.getCause() + " " + e.getStackTrace());
		throw new RateLimiterException(e.getMessage());
	}
}
